package streams;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.json.JSONException;
import org.json.JSONObject;


public class SimpleStreams {
    public static void main(String[] args) throws InterruptedException, IOException {
        String creditsIn = "credits";
        String creditsOut = "results";

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "exercises-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> inStream = builder.stream(creditsIn);

        inStream.mapValues((value) -> {
            JSONObject obj = null;
            String amount = null;
            String dead_line = null;
            String currency_name = null;
            String client_id = null;

            try {
                obj = new JSONObject(value);
                amount = (String) obj.get("amount");
                dead_line = (String) obj.get("dead_line");
                currency_name = (String) obj.get("currency_name");
                client_id = (String) obj.get("client_id");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return "{\"schema\":" +
                "{\"type\":\"struct\"," +
                "\"fields\":[{\"type\":\"float\",\"optional\":false,\"field\":\"amount\"}," +
                            "{\"type\":\"date\",\"optional\":false,\"field\":\"dead_line\"}," +
                            "{\"type\":\"string\",\"optional\":false,\"field\":\"currency_name\"}," +
                            "{\"type\":\"int\",\"optional\":false,\"field\":\"client_id\"}]," +
                "\"optional\":false}," +
                "\"payload\":{\"amount\":" + amount + "," +
                             "\"dead_line\":" + dead_line + "," +
                             "\"currency_name\":" + currency_name + "," +
                             "\"client_id\":" + client_id + "}}";
            }).to(creditsOut);

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

        System.out.println("Reading stream from topic " + creditsIn);

    }
}