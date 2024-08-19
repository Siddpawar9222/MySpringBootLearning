package com.example.Spring_Kafka_1.controller;

import com.example.Spring_Kafka_1.service.KafkaProducerService;
import com.example.Spring_Kafka_1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaProducerService producerService;
    private  final PostService postService ;


//    @PostMapping("/publish")
//    public ResponseEntity<String> publishMessage(@RequestParam("message") String message) {
//        producerService.sendMessage("my-topic", message);
//        return ResponseEntity.ok("Message published to Kafka topic");
//    }



    @PostMapping("/likePostWithoutKafka/{postId}")
    public String likePostWithoutKafka(@PathVariable Long postId) {
        try {
            postService.likePostWithoutKafka(postId);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Failed to like post!";
        }
        return "Post liked!";
    }

    @PostMapping("/likePostWithKafka/{postId}")
    public String likePostWithKafka(@PathVariable Long postId) {
        try {
            postService.likePostWithKafka(postId);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Failed to like post!";
        }
        return "Post liked!";
    }
}