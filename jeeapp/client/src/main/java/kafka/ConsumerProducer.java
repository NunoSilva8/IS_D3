package kafka;

import entities.Client;
import entities.Currency;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.*;

public class ConsumerProducer {


    public static void main(String[] args) throws Exception {
        String consumerTopicName = "db-info-client";
        String creditsTopic = "credits";
        String paymentsTopic = "payments";

        Consumer<String, String> consumer = initializeConsumer();
        Producer<String, String> producer = initializeProducer();

        Boolean keepOnGoing = true;
        Long amount;
        List<Currency> currencies = new ArrayList<>();
        Currency selectedCurrency;
        Random rand;
        List<String> typeOfOperation = new ArrayList<>(List.of("Credit", "Payment"));
        String key;
        List<Client> clients = new ArrayList<>();

        consumer.subscribe(Collections.singletonList(consumerTopicName));

        do{
            //CONSUMER
            ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.key() + " => " + record.value());
                //TODO: transformar stream em entidades Client
            }

            //PRODUCER
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

    }


    private static Consumer<String, String> initializeConsumer(){

        // create instance for properties to access producer configs
        Properties props = new Properties();

        //Assign localhost id
        props.put("bootstrap.servers", "localhost:9092");

        //Set acknowledgements for producer requests.
        props.put("acks", "all");

        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);

        //Specify buffer size in config
        props.put("batch.size", 16384);

        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);

        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleConsumer");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        return new KafkaConsumer<>(props);

    }

    private static Producer<String, String> initializeProducer(){

        // create instance for properties to access producer configs
        Properties props = new Properties();

        //Assign localhost id
        props.put("bootstrap.servers", "localhost:9092");

        //Set acknowledgements for producer requests.
        props.put("acks", "all");

        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);

        //Specify buffer size in config
        props.put("batch.size", 16384);

        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);

        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        return new KafkaProducer<>(props);
    }

    public static String creditToStream(String currency, Long amount, Long clientId) { //TODO: verificar se pode ser static
        return "{deadline: " + new Date(System.currentTimeMillis()) + //TODO: retirar?
                ", currency: " + currency +
                ", amount: " + amount +
                ", clientId: " + clientId.toString() +
                '}';
    }

    public static String paymentToStream(String currency, Long amount,Long clientId) { //TODO: verificar se pode ser static
        return "{payDate: " + new Date(System.currentTimeMillis()) + //TODO: retirar?
                ", currency: " + currency +
                ", amount: " + amount +
                ", clientId: " + clientId.toString() +
                '}';
    }
}
