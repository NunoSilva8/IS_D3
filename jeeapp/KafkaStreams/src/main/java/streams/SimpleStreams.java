package streams;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.json.JSONException;
import org.json.JSONObject;


public class SimpleStreams {
    public static void main(String[] args) throws InterruptedException, IOException {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "exercises-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        StreamsBuilder builder = new StreamsBuilder();

        //CURRENCIES
        JSONObject currencies = new JSONObject();
        currencies.put("EUR", 1.0);
        currencies.put("USD", 0.89);
        currencies.put("US_DOLLAR", 0.89);

        //CLIENTES
        JSONObject clientes = new JSONObject();
        clientes.put("1", new JSONObject("{\"id\":1,\"name\":\"Client Name 1\",\"balance\":0.0,\"manager_id\":2,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("2", new JSONObject("{\"id\":2,\"name\":\"Client Name 2\",\"balance\":0.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("90", new JSONObject("{\"id\":90,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("91", new JSONObject("{\"id\":91,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("92", new JSONObject("{\"id\":92,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("93", new JSONObject("{\"id\":93,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("94", new JSONObject("{\"id\":94,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));



        //CREDITOS
        KStream<String, String> creditsInStream = builder.stream("credits");

        //PAGAMENTOS
        KStream<String, String> paymentsInStream = builder.stream("payments");


        /*
        //Client Total Credits + Balance Updates
        KTable<String, String> totalCredits = creditsInStream.groupByKey()
            .reduce((oldvalue,newvalue) -> {
                JSONObject JSON_old = new JSONObject(oldvalue);
                JSONObject JSON_new = new JSONObject(newvalue);

                //Converter amount para EUR
                String old_currency_name = (String) JSON_old.get("currency");
                Double old_currency_rate = (Double) currencies.getDouble(old_currency_name);
                Double old_amount_converted = (Double) JSON_old.getDouble("amount") * old_currency_rate;

                String new_currency_name = (String) JSON_new.get("currency");
                Double new_currency_rate = (Double) currencies.getDouble(new_currency_name);
                Double new_amount_converted = (Double) JSON_new.getDouble("amount") * new_currency_rate;

                JSON_new.put("amount", old_amount_converted + new_amount_converted);
                JSON_new.put("transac_amount", new_amount_converted);
                JSON_new.put("currency", "EUR");
                return JSON_new.toString();
            })
            .mapValues((value) -> {
                JSONObject old_value = new JSONObject(value);

                if (!old_value.getString("currency").equals("EUR")){
                    String old_currency_name = (String) old_value.get("currency");
                    Double old_currency_rate = (Double) currencies.getDouble(old_currency_name);
                    old_value.put("amount", old_value.getDouble("amount") * old_currency_rate);
                }

                Integer id = old_value.getInt("id");
                Double sum_amount = old_value.getDouble("amount");
                Double amount = null;
                try {
                    amount = old_value.getDouble("transac_amount");
                }catch (JSONException e){
                    amount = old_value.getDouble("amount");
                }

                JSONObject cliente = clientes.getJSONObject(String.valueOf(id));

                cliente.put("credit_total", sum_amount);
                cliente.put("balance", cliente.getDouble("balance") + amount);

                clientes.put(String.valueOf(id), cliente);
                return CLIENT_SCHEMA(cliente);
            });
        totalCredits.toStream().to("results", Produced.with(Serdes.String(), Serdes.String()));


        //Client Total Payments + Balance Updates
        KTable<String, String> totalPayments = paymentsInStream.groupByKey()
                .reduce((oldvalue,newvalue) -> {
                    JSONObject JSON_old = new JSONObject(oldvalue);
                    JSONObject JSON_new = new JSONObject(newvalue);

                    //Converter amount para EUR
                    String old_currency_name = (String) JSON_old.get("currency");
                    Double old_currency_rate = (Double) currencies.getDouble(old_currency_name);
                    Double old_amount_converted = (Double) JSON_old.getDouble("amount") * old_currency_rate;

                    String new_currency_name = (String) JSON_new.get("currency");
                    Double new_currency_rate = (Double) currencies.getDouble(new_currency_name);
                    Double new_amount_converted = (Double) JSON_new.getDouble("amount") * new_currency_rate;

                    JSON_new.put("amount", old_amount_converted + new_amount_converted);
                    JSON_new.put("transac_amount", new_amount_converted);
                    JSON_new.put("currency", "EUR");
                    return JSON_new.toString();
                })
                .mapValues((value) -> {
                    JSONObject old_value = new JSONObject(value);

                    if (!old_value.getString("currency").equals("EUR")){
                        String old_currency_name = (String) old_value.get("currency");
                        Double old_currency_rate = (Double) currencies.getDouble(old_currency_name);
                        old_value.put("amount", old_value.getDouble("amount") * old_currency_rate);
                    }

                    Integer id = old_value.getInt("id");
                    Double sum_amount = old_value.getDouble("amount");
                    Double amount = null;
                    try {
                        amount = old_value.getDouble("transac_amount");
                    }catch (JSONException e){
                        amount = old_value.getDouble("amount");
                    }

                    JSONObject cliente = clientes.getJSONObject(String.valueOf(id));

                    cliente.put("payment_total", sum_amount);
                    cliente.put("balance", cliente.getDouble("balance") - amount);

                    clientes.put(String.valueOf(id), cliente);
                    return CLIENT_SCHEMA(cliente);
                });
        totalPayments.toStream().to("results", Produced.with(Serdes.String(), Serdes.String()));
        */

        //Tentativa com OuterJoined Streams
        ValueJoiner<String, String, String> valueJoiner = (credito, pagamento) -> {
            JSONObject obj = null;
            //Credito
            if (credito != null){
                obj = new JSONObject(credito);
                obj.put("operation", "credit");
            } else { //Pagamento
                obj = new JSONObject(pagamento);
                obj.put("operation", "payment");
            }
            return obj.toString();
        };

        //Calcular Balance
        KTable<String, String> Movements = creditsInStream.outerJoin(paymentsInStream, valueJoiner, JoinWindows.of(Duration.ofSeconds(10))).groupByKey()
                .reduce((oldvalue, newvalue) -> {
                    JSONObject JSON_old = new JSONObject(oldvalue);
                    JSONObject JSON_new = new JSONObject(newvalue);

                    //Converter amount para EUR
                    String old_currency_name = (String) JSON_old.get("currency");
                    Double old_currency_rate = (Double) currencies.getDouble(old_currency_name);
                    Double old_amount_converted = (Double) JSON_old.getDouble("amount") * old_currency_rate;

                    String new_currency_name = (String) JSON_new.get("currency");
                    Double new_currency_rate = (Double) currencies.getDouble(new_currency_name);
                    Double new_amount_converted = (Double) JSON_new.getDouble("amount") * new_currency_rate;

                    if (JSON_new.getString("operation").equals("credit")){
                        JSON_new.put("amount", old_amount_converted + new_amount_converted);
                    } else {
                        JSON_new.put("amount", old_amount_converted - new_amount_converted);
                    }
                    JSON_new.put("currency", "EUR");
                    return JSON_new.toString();
                });
        Movements.toStream().peek((k,v) -> System.out.println(v));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
    }

    public static String CLIENT_SCHEMA(JSONObject Payload) {
        JSONObject JSON = new JSONObject("{\"schema\":{\"type\":\"struct\",\"fields\":[{\"type\":\"int64\",\"optional\":false,\"field\":\"id\"},{\"type\":\"string\",\"optional\":false,\"field\":\"name\"},{\"type\":\"float\",\"optional\":false,\"field\":\"balance\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"manager_id\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"payment_total\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"credit_total\"}],\"optional\":false}}");
        JSON.put("payload", Payload);
        return JSON.toString();
    }
}

