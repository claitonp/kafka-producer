package br.com.claitonp.kafka.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.claitonp.kafka.model.Login;
import br.com.claitonp.kafka.model.User;
import br.com.claitonp.kafka.producer.MeuTopicoProducer;
import br.com.claitonp.kafka.producer.OutroTopicoProducer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
	
    private final MeuTopicoProducer meuTopicoProducer;
    private final OutroTopicoProducer outroTopicoProducer;
    
    @PostMapping(value = "/user")
    public void sendUser(@RequestBody User user){
    	meuTopicoProducer.sendUser(user);
    }

    @PostMapping(value = "/login")
    public void sendLogin(@RequestBody Login login){
    	outroTopicoProducer.sendLogin(login);
    }
}
