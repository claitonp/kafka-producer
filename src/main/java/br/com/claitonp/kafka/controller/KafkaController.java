package br.com.claitonp.kafka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.claitonp.kafka.producer.TopicProducer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
	
    private final TopicProducer topicProducer;
    
    @GetMapping(value = "/send/{numero}")
    public void send(@PathVariable String numero){
        topicProducer.send("Mensagem " + numero);
    }
}
