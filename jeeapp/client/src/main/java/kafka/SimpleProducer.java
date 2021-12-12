package kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONObject;

import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;


public class SimpleProducer {
    public static void main(String[] args) throws Exception {
        String creditsTopic = "credits";
        String paymentsTopic = "payments";
        JSONObject currencies = new JSONObject();
        JSONObject clientes = new JSONObject();
        Random rand = new Random();

        //PRODUCER PROPS
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        
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


        do{
            //SET CURRENCY LIST
            Consumer<String, String> CurrencyConsumer = new KafkaConsumer<>(propsConsumerCurrency);
            CurrencyConsumer.subscribe(Collections.singletonList("db-info-currency"));
            ConsumerRecords<String, String> CurrencyRecords = CurrencyConsumer.poll(1000L);
            for (ConsumerRecord<String, String> record : CurrencyRecords) {
                JSONObject coin = new JSONObject(record.value());
                coin = coin.getJSONObject("payload");

                currencies.put(coin.getString("name"), coin.getDouble("to_euro"));
            }
            CurrencyConsumer.close();

            //SET CLIENT LIST
            Consumer<String, String> ClientConsumer = new KafkaConsumer<>(propsConsumerClient);
            ClientConsumer.subscribe(Collections.singletonList("db-info-client"));
            ConsumerRecords<String, String> ClientRecords = ClientConsumer.poll(1000L);
            for (ConsumerRecord<String, String> record : ClientRecords) {
                JSONObject client = new JSONObject(record.value());
                client = client.getJSONObject("payload");

                clientes.put(String.valueOf(client.getInt("id")), client);
            }
            ClientConsumer.close();

            //RANDOM AMOUNT
            Double amount = rand.nextInt(10000)/(Double)100.0;
            
            //RANDOM CLIENT
            Iterator<?> clientKeysIt = clientes.keys();
            Integer i = 0;
            Integer index = rand.nextInt(clientes.length());
            String clientKey = "0";
            while(clientKeysIt.hasNext())
            {
                clientKey = (String)clientKeysIt.next();
                if (i == index){
                    break;
                }
                i++;
            }
            JSONObject cliente = clientes.getJSONObject(clientKey);

            //RANDOM CURRENCY
            Iterator<?> currencyKeysIt = currencies.keys();
            i = 0;
            index = rand.nextInt(currencies.length());
            String currency = "0";
            while(currencyKeysIt.hasNext())
            {
                currency = (String)currencyKeysIt.next();
                if (i == index){
                    break;
                }
                i++;
            }

            //PRODUCER
            Boolean operationBoolean = rand.nextBoolean();
            Producer<String, String> producer = new KafkaProducer<>(props);
            if (operationBoolean){
                //ENVIAR CREDITO
                producer.send(new ProducerRecord<String, String>(creditsTopic, clientKey, creditToStream(currency, amount, cliente)));
                System.out.println("Enviei Credito, " + creditToStream(currency, amount, cliente));
            } else {
                //ENVIAR PAGAMENTO
                producer.send(new ProducerRecord<String, String>(paymentsTopic, clientKey, paymentToStream(currency, amount, cliente)));
                System.out.println("Enviei Pagamento, " + paymentToStream(currency, amount, cliente));
            }
            producer.close();
        } while (true);
    }

    public static String creditToStream(String currency, Double amount, JSONObject client) {
        return "{currency: " + currency +
                ", amount: " + amount +
                ", id: " + client.getInt("id") +
                ", total_credits: " + amount +
                ", total_payments: " + 0 +
                ", manager_id: " + client.getInt("manager_id") +
                ", name: " + client.getString("name") +
                '}';
    }

    public static String paymentToStream(String currency, Double amount, JSONObject client) {
        return "{currency: " + currency +
                ", amount: " + amount +
                ", id: " + client.getInt("id") +
                ", total_credits: " + 0 +
                ", total_payments: " + amount +
                ", manager_id: " + client.getInt("manager_id") +
                ", name: " + client.getString("name") +
                '}';
    }
}