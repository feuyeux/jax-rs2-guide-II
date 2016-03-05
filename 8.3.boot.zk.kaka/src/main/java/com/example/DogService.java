package com.example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Service
public class DogService {
    static final Log logger = LogFactory.getLog(DogService.class);

    @Value("${kafkaServerList}")
    private String kafkaServerList;

    @Value("${topic}")
    private String topic;

    @Value("${key}")
    private String key;

    private Boolean isAsync;

    private KafkaProducer<String, String> producer;

    @PostConstruct
    public void init() {
        isAsync = true;
        final Properties props = new Properties();
        props.put("bootstrap.servers", kafkaServerList);
        props.put("client.id", "boost.zk.kafka");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    public void process(String value) {
        if (isAsync) {
            producer.send(new ProducerRecord<>(topic, value), new KafCallBack(value));
        } else {
            try {
                producer.send(new ProducerRecord<>(topic, value)).get();
            } catch (InterruptedException | ExecutionException e) {
                logger.error("fail to send message to kafka", e);
            } catch (Exception e) {
                logger.error("unknown error while sending message to kafka", e);
            }
        }
    }

    private class KafCallBack implements Callback {
        private String message;
        private Log logger;

        public KafCallBack(String message) {
            this.message = message;
            this.logger = DogService.logger;
        }

        public void onCompletion(RecordMetadata metadata, Exception exception) {
            if (metadata != null) {
                logger.info("message(" + ", " + message + ") sent to partition(" + metadata.partition() + "), " + "offset(" + metadata.offset() + ")");
            } else {
                logger.error("", exception);
            }
        }
    }
}
