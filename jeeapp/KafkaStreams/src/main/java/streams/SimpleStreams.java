package streams;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.json.JSONException;
import org.json.JSONObject;



public class SimpleStreams {
    public static void main(String[] args) throws InterruptedException, IOException {
        //STREAMS PROPS
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "exercises-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        StreamsBuilder builder = new StreamsBuilder();

        //STREAMS
        KStream<String, String> creditsInStream = builder.stream("credits");
        KStream<String, String> paymentsInStream = builder.stream("payments");

        //CURRENCIES
        JSONObject currencies = new JSONObject();

        //CURRENCY PROPS
        Properties propsConsumer = new Properties();
        propsConsumer.put("bootstrap.servers", "localhost:9092");
        propsConsumer.put("acks", "all");
        propsConsumer.put("retries", 0);
        propsConsumer.put("batch.size", 16384);
        propsConsumer.put("linger.ms", 1);
        propsConsumer.put("buffer.memory", 33554432);
        propsConsumer.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaStreamsCurrencyConsumer");
        propsConsumer.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        propsConsumer.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        Consumer<String, String> consumer = new KafkaConsumer<>(propsConsumer);
        consumer.subscribe(Collections.singletonList("db-info-currency"));
        ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
        for (ConsumerRecord<String, String> record : records) {
            JSONObject coin = new JSONObject(record.value());
            coin = coin.getJSONObject("payload");
            currencies.put(coin.getString("name"), coin.getDouble("to_euro"));
        }
        consumer.close();

        ValueJoiner<String, String, String> valueJoiner = (credito, pagamento) -> {
            JSONObject obj = null;
            //Credito
            if (credito != null){
                obj = new JSONObject(credito);
                obj.put("operation", "credit");
            } else { //Pagamento
                obj = new JSONObject(pagamento);
                obj.put("operation", "payment");
                obj.put("amount", obj.getDouble("amount")*-1);
            }

            //Converter para EUR
            String currency_name = obj.getString("currency");
            Double currency_rate = currencies.getDouble(currency_name);
            obj.put("amount", obj.getDouble("amount") * currency_rate);
            obj.put("total_credits", obj.getDouble("total_credits") * currency_rate);
            obj.put("total_payments", obj.getDouble("total_payments") * currency_rate);
            obj.put("currency", "EUR");

            return obj.toString();
        };
        KGroupedStream<String, String> credito_pagamento = creditsInStream.outerJoin(paymentsInStream, valueJoiner, JoinWindows.of(TimeUnit.MILLISECONDS.toMillis(50))).groupByKey();

        //Calcular Windowed Balance
        KTable<Windowed<String>, String> windowedMovements = credito_pagamento
                .windowedBy(TimeWindows.of(TimeUnit.DAYS.toMillis(30*1)))
                .reduce((oldvalue, newvalue) -> {
                    JSONObject JSON_old = new JSONObject(oldvalue);
                    JSONObject JSON_new = new JSONObject(newvalue);

                    Double amount = JSON_new.getDouble("amount");

                    JSON_new.put("amount", JSON_old.getDouble("amount") + amount);
                    return JSON_new.toString();
                })
                .mapValues((value) -> {
                    JSONObject obj = new JSONObject(value);
                    obj.put("balance_last_month", obj.getDouble("amount"));
                    obj.remove("amount");
                    obj.remove("manager_id");
                    obj.remove("total_payments");
                    obj.remove("total_credits");
                    obj.remove("operation");
                    obj.remove("currency");
                    obj.remove("id");
                    return obj.toString();
                });
        KTable<String, String> windowedMovementsTable = windowedMovements.toStream((k,v) -> k.key()).toTable();

        //Calcular Windowed Payments
        KTable<Windowed<String>, String> windowedPayments = credito_pagamento
                .windowedBy(TimeWindows.of(TimeUnit.DAYS.toMillis(30*2)))
                .reduce((oldval, newval) -> {
                    JSONObject oldv = new JSONObject(oldval);
                    JSONObject newv = new JSONObject(newval);

                    if (!oldv.has("payments_last_two_months")){
                        if (oldv.get("operation").equals("payment"))
                            oldv.put("payments_last_two_months", 1);
                        else
                            oldv.put("payments_last_two_months", 0);
                    }
                    if (newv.getString("operation").equals("payment"))
                        newv.put("payments_last_two_months", oldv.getInt("payments_last_two_months") + 1);
                    return newv.toString();
                })
                .mapValues((key, value) -> {
                    JSONObject obj = new JSONObject();
                    JSONObject val = new JSONObject(value);

                    if (!val.has("payments_last_two_months")){
                        if (val.get("operation").equals("payment"))
                            val.put("payments_last_two_months", 1);
                        else
                            val.put("payments_last_two_months", 0);
                    }

                    if (val.getInt("payments_last_two_months") > 0)
                        obj.put("payments_last_two_months", 1);
                    else
                        obj.put("payments_last_two_month", 0);
                    return obj.toString();
                });
        KTable<String, String> windowedPaymentsTable = windowedPayments.toStream((k,v) -> k.key()).toTable();

        //Calcular Total Balance, Credits, Payments
        KTable<String, String> Movements = credito_pagamento
                .reduce((oldvalue, newvalue) -> {
                    JSONObject JSON_old = new JSONObject(oldvalue);
                    JSONObject JSON_new = new JSONObject(newvalue);

                    Double amount = JSON_new.getDouble("amount");

                    if (JSON_new.getString("operation").equals("credit")) {
                        JSON_new.put("total_credits", JSON_old.getDouble("total_credits") + amount);
                        JSON_new.put("total_payments", JSON_old.getDouble("total_payments"));
                    } else {
                        JSON_new.put("total_payments", JSON_old.getDouble("total_payments") + (amount * -1));
                        JSON_new.put("total_credits", JSON_old.getDouble("total_credits"));
                    }
                    JSON_new.put("amount", JSON_old.getDouble("amount") + amount);
                    return JSON_new.toString();
                })
                .mapValues((value) -> {
                    JSONObject obj = new JSONObject(value);
                    obj.put("balance", obj.getDouble("amount"));
                    obj.remove("amount");
                    obj.put("payment_total", obj.getDouble("total_payments"));
                    obj.remove("total_payments");
                    obj.put("credit_total", obj.getDouble("total_credits"));
                    obj.remove("total_credits");
                    obj.remove("operation");
                    obj.remove("currency");
                    return obj.toString();
                });

        //Update Manager Revenue
        KTable<String, String> managerRevenue = paymentsInStream
                .selectKey((key, value) -> {
                    JSONObject obj = new JSONObject(value);
                    return String.valueOf(obj.getInt("manager_id"));})
                .groupByKey()
                .reduce((oldvalue, newvalue) -> {
                    JSONObject obj = new JSONObject(newvalue);
                    JSONObject oldobj = new JSONObject(oldvalue);
                    JSONObject end = new JSONObject();

                    //Converter para EUR
                    if (oldobj.has("total_payments")){
                        String old_currency_name = obj.getString("currency");
                        Double old_currency_rate = currencies.getDouble(old_currency_name);
                        oldobj.put("amount", oldobj.getDouble("amount") * old_currency_rate);
                    }
                    String currency_name = obj.getString("currency");
                    Double currency_rate = currencies.getDouble(currency_name);
                    obj.put("amount", obj.getDouble("amount") * currency_rate);

                    end.put("id", obj.getInt("manager_id"));
                    end.put("amount", oldobj.getDouble("amount") + obj.getDouble("amount"));

                    return end.toString();})
                .mapValues((value) -> {
                    JSONObject obj = new JSONObject(value);
                    JSONObject end = new JSONObject();
                    end.put("revenue", obj.getDouble("amount"));
                    if (obj.has("manager_id")) {
                        end.put("id", obj.getInt("manager_id"));
                    } else {
                        end.put("id",obj.getInt("id"));
                    }
                    return end.toString();});

        //JOIN TABLES - Esta a duplicar entradas em vez de dar merge. Porem a ultima e sempre a mais atualizada.
        KTable<String, String> Client = Movements.leftJoin(windowedMovementsTable, (left, right) -> {
            JSONObject l = new JSONObject(left);
            try {
                JSONObject r = new JSONObject(right);
                l.put("balance_last_month", r.getDouble("balance_last_month"));
            } catch (JSONException e){
                l.put("balance_last_month", 0);
            } catch (NullPointerException e){
                l.put("balance_last_month", 0);
            }
            return l.toString();
        });
        Client = Client.leftJoin(windowedPaymentsTable, (left, right) -> {
            JSONObject l = new JSONObject(left);
            try {
                JSONObject r = new JSONObject(right);
                l.put("payments_last_two_months", r.getInt("payments_last_two_months"));
            } catch (JSONException e){
                l.put("payments_last_two_months", 0);
            } catch (NullPointerException e){
                l.put("payments_last_two_months", 0);
            }
            return l.toString();
        });

        //SEND STREAMS
        Client.toStream().peek((k,v) -> System.out.println("FINAL: "+k+"|"+v));
        managerRevenue.toStream().peek((k,v) -> System.out.println("FINAL: "+k+"|"+v));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.cleanUp();
        streams.start();

        consumer = new KafkaConsumer<>(propsConsumer);
        consumer.subscribe(Collections.singletonList("db-info-currency"));
        do {
            records = consumer.poll(1000L);
            for (ConsumerRecord<String, String> record : records) {
                JSONObject coin = new JSONObject(record.value());
                coin = coin.getJSONObject("payload");
                currencies.put(coin.getString("name"), coin.getDouble("to_euro"));
            }
        }while (true);
    }
    

    public static String CLIENT_SCHEMA(JSONObject Payload) {
        JSONObject JSON = new JSONObject("{\"schema\":{\"type\":\"struct\",\"fields\":[{\"type\":\"int64\",\"optional\":false,\"field\":\"id\"},{\"type\":\"string\",\"optional\":false,\"field\":\"name\"},{\"type\":\"double\",\"optional\":false,\"field\":\"balance\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"manager_id\"},{\"type\":\"double\",\"optional\":false,\"field\":\"payment_total\"},{\"type\":\"double\",\"optional\":false,\"field\":\"credit_total\"},{\"type\":\"int8\",\"optional\":false,\"field\":\"payments_last_two_months\"},{\"type\":\"double\",\"optional\":false,\"field\":\"balance_last_month\"}],\"optional\":false}}");
        JSON.put("payload", Payload);
        return JSON.toString();
    }
}

