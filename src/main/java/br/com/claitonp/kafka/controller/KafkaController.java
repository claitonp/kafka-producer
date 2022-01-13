package br.com.claitonp.kafka.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.claitonp.kafka.model.User;
import br.com.claitonp.kafka.producer.TopicProducer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
	
    private final TopicProducer topicProducer;
    
    @PostMapping(value = "/user")
    public void send(@RequestBody User user){
        topicProducer.send(user);
    }
}
