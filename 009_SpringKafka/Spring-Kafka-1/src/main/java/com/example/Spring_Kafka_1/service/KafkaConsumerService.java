package com.example.Spring_Kafka_1.service;

import com.example.Spring_Kafka_1.PostRepository;
import com.example.Spring_Kafka_1.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final PostRepository postRepository;
    private final NotificationService notificationService;

//    @KafkaListener(topics = "my-topic", groupId = "my-group")
//    public void listen(String message) {
//        System.out.println("Received Message: " + message);
//    }

    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void listen(String message) throws InterruptedException {
        this.saveLikes(message);
    }

    private void saveLikes(String message) throws InterruptedException {
        Long id = Long.valueOf(message);

        // Get post by id
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));

        // Increment likes
        post.setLikes(post.getLikes() + 1);

        // Save the updated post
        postRepository.save(post);

        // Send a notification (this is a synchronous call)
        notificationService.sendNotification(id);
    }
}