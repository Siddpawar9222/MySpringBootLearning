package com.example.Spring_Kafka_1.service;

import com.example.Spring_Kafka_1.PostRepository;
import com.example.Spring_Kafka_1.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService {


    private final PostRepository postRepository;

    private final NotificationService notificationService;

    private final KafkaProducerService kafkaProducerService;

    public void likePostWithoutKafka(Long postId) throws InterruptedException {

       // Get post by id
       Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));

        // Increment likes
        post.setLikes(post.getLikes() + 1);

        // Save the updated post
        postRepository.save(post);

        // Send a notification (this is a synchronous call)
        notificationService.sendNotification(postId);
    }

    public void likePostWithKafka(Long postId) throws InterruptedException{
        kafkaProducerService.sendMessage(String.valueOf(postId));
    }


}
