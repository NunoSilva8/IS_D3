package kafka;

import entities.Currency;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONObject;

import java.util.*;

public class SimpleProducer {
    public static void main(String[] args) throws Exception {
        //PRODUCER PROPS
        String creditsTopic = "credits";
        String paymentsTopic = "payments";
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        
        //CURRENCY CONSUMER PROPS
        Properties propsConsumerCurrency = new Properties();
        propsConsumerCurrency.put("bootstrap.servers", "localhost:9092");
        propsConsumerCurrency.put("acks", "all");
        propsConsumerCurrency.put("retries", 0);
        propsConsumerCurrency.put("batch.size", 16384);
        propsConsumerCurrency.put("linger.ms", 1);
        propsConsumerCurrency.put("buffer.memory", 33554432);
        propsConsumerCurrency.put(ConsumerConfig.GROUP_ID_CONFIG, "ClientCurrencyConsumer");
        propsConsumerCurrency.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        propsConsumerCurrency.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        Consumer<String, String> CurrencyConsumer = new KafkaConsumer<>(propsConsumerCurrency);
        CurrencyConsumer.subscribe(Collections.singletonList("db-info-currency"));

        //CLIENTS CONSUMER PROPS
        Properties propsConsumerClient = new Properties();
        propsConsumerClient.put("bootstrap.servers", "localhost:9092");
        propsConsumerClient.put("acks", "all");
        propsConsumerClient.put("retries", 0);
        propsConsumerClient.put("batch.size", 16384);
        propsConsumerClient.put("linger.ms", 1);
        propsConsumerClient.put("buffer.memory", 33554432);
        propsConsumerClient.put(ConsumerConfig.GROUP_ID_CONFIG, "ClientCurrencyConsumer");
        propsConsumerClient.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        propsConsumerClient.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        Consumer<String, String> ClientConsumer = new KafkaConsumer<>(propsConsumerClient);
        ClientConsumer.subscribe(Collections.singletonList("db-info-client"));

        JSONObject currencies = new JSONObject();
        JSONObject clients = new JSONObject();
        do{
            //SET CURRENCY LIST
            ConsumerRecords<String, String> CurrencyRecords = CurrencyConsumer.poll(1000L);
            System.out.println("CURRENCY: "+CurrencyRecords.count());
            for (ConsumerRecord<String, String> record : CurrencyRecords) {
                JSONObject coin = new JSONObject(record.value());
                coin = coin.getJSONObject("payload");
                currencies.put(coin.getString("name"), coin.getDouble("to_euro"));
                System.out.println(coin);
            }

            //SET CLIENT LIST
            ConsumerRecords<String, String> ClientRecords = ClientConsumer.poll(1000L);
            System.out.println("CLIENTS: "+ClientRecords.count());
            for (ConsumerRecord<String, String> record : ClientRecords) {
                JSONObject client = new JSONObject(record.value());
                client = client.getJSONObject("payload");
                clients.put(String.valueOf(client.getInt("id")), client);
                System.out.println(client);
            }

        } while (true);
        /*
        Double amount;
        List<Currency> currencies = new ArrayList<>();
        Currency selectedCurrency;
        Random rand;
        List<String> typeOfOperation = new ArrayList<>(List.of("Credit", "Payment"));
        String key = "230";

        producer.send(new ProducerRecord<String, String>(creditsTopic, key, creditToStream("CNY", 10.0, Integer.parseInt(key))));

        do{
            amount = (long)(Math.random() * 10000L);
            rand = new Random();
            selectedCurrency = currencies.get(rand.nextInt(currencies.size()));
            key = typeOfOperation.get(rand.nextInt(typeOfOperation.size()));

            if(key.equals("Credit")){
                producer.send(new ProducerRecord<String, String>(creditsTopic, key, creditToStream(selectedCurrency.getName(), amount, 1L))); //TODO: hardcoded
                System.out.println("Sending message to topic " + creditsTopic);
            }
            else{
                producer.send(new ProducerRecord<String, String>(paymentsTopic, key, paymentToStream(selectedCurrency.getName(), amount,1L))); //TODO: hardcoded
                System.out.println("Sending message to topic " + paymentsTopic);
            }
        }while(keepOnGoing);



        producer.close();*/
    }

    public static String creditToStream(String currency, Double amount, Integer clientID) {
        return "{currency: " + currency +
                ", amount: " + amount +
                ", id: " + clientID +
                ", total_credits: " + amount +
                ", total_payments: " + 0 +
                ", manager_id: " + 13 +
                '}';
    }

    public static String paymentToStream(String currency, Double amount,Integer clientID) {
        return "{currency: " + currency +
                ", amount: " + amount +
                ", id: " + clientID +
                ", total_credits: " + 0 +
                ", total_payments: " + amount +
                ", manager_id: " + 13 +
                '}';
    }
}