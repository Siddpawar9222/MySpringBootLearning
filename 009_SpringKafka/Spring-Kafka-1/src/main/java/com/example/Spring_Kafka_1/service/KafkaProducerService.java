package com.example.Spring_Kafka_1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private String topic = "my-topic";


//    public void sendMessage(String topic, String message)
//    {
//        kafkaTemplate.send(topic, message);
//    }

    public void sendMessage(String message) {
        this.kafkaTemplate.send(this.topic, message);
    }


}