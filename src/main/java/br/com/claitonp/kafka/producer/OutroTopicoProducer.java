package br.com.claitonp.kafka.producer;

import java.time.LocalDateTime;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import br.com.claitonp.kafka.model.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OutroTopicoProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    
    @Value("${kafka.topic.outrotopico}")
    private String outroTopico;
    
    @Bean
    public NewTopic outroTopico() {
        return TopicBuilder.name(outroTopico)
        	      .partitions(3)
        	      .replicas(1)
        	      .compact()
                  .config(TopicConfig.DELETE_RETENTION_MS_CONFIG, "100")
                  .config(TopicConfig.SEGMENT_MS_CONFIG, "100")
                  .config(TopicConfig.MIN_CLEANABLE_DIRTY_RATIO_CONFIG, "0.01")
        	      .build();
    }

    
    public void sendLogin(Login login){
    	login.setDh(LocalDateTime.now());
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(outroTopico, login.getLogin(), login);
        
        future.addCallback(new ProducerCallback());
    }

}