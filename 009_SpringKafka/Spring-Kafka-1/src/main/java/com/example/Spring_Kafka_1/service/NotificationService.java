package com.example.Spring_Kafka_1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {
    public void sendNotification(Long postId) throws InterruptedException {
        Thread.sleep(3000); // 3 seconds delay
         log.info("Message sent successfully: " + postId);
    }
}
