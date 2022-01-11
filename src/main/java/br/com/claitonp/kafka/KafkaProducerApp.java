package br.com.claitonp.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class KafkaProducerApp {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(KafkaProducerApp.class, args);
    }

}
