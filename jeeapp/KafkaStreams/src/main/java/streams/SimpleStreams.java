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
        clientes.put("95", new JSONObject("{\"id\":95,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("96", new JSONObject("{\"id\":96,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("97", new JSONObject("{\"id\":97,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("98", new JSONObject("{\"id\":98,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("99", new JSONObject("{\"id\":99,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("100", new JSONObject("{\"id\":100,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("101", new JSONObject("{\"id\":101,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("102", new JSONObject("{\"id\":102,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("103", new JSONObject("{\"id\":103,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("104", new JSONObject("{\"id\":104,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("105", new JSONObject("{\"id\":105,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("106", new JSONObject("{\"id\":106,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("107", new JSONObject("{\"id\":107,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("108", new JSONObject("{\"id\":108,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("109", new JSONObject("{\"id\":109,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));
        clientes.put("110", new JSONObject("{\"id\":110,\"name\":\"Client Name 3\",\"balance\":10.0,\"manager_id\":1,\"payment_total\":0,\"credit_total\":0}"));




        //STREAMS
        KStream<String, String> creditsInStream = builder.stream("credits");
        KStream<String, String> paymentsInStream = builder.stream("payments");

        //Calcular Total Balance, Credits, Payments
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
        KTable<String, String> Movements = creditsInStream.outerJoin(paymentsInStream, valueJoiner, JoinWindows.of(Duration.ofSeconds(10))).groupByKey()
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
                    return CLIENT_SCHEMA(obj);
                });
        Movements.toStream().peek((k,v) -> System.out.println(v));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.cleanUp();
        streams.start();
    }

    public static String CLIENT_SCHEMA(JSONObject Payload) {
        JSONObject JSON = new JSONObject("{\"schema\":{\"type\":\"struct\",\"fields\":[{\"type\":\"int64\",\"optional\":false,\"field\":\"id\"},{\"type\":\"string\",\"optional\":false,\"field\":\"name\"},{\"type\":\"double\",\"optional\":false,\"field\":\"balance\"},{\"type\":\"int64\",\"optional\":false,\"field\":\"manager_id\"},{\"type\":\"double\",\"optional\":false,\"field\":\"payment_total\"},{\"type\":\"double\",\"optional\":false,\"field\":\"credit_total\"},{\"type\":\"int8\",\"optional\":false,\"field\":\"payments_last_two_months\"},{\"type\":\"double\",\"optional\":false,\"field\":\"balance_last_month\"}],\"optional\":false}}");
        JSON.put("payload", Payload);
        return JSON.toString();
    }
}

