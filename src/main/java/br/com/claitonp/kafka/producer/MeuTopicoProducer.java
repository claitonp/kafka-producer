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

import br.com.claitonp.kafka.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MeuTopicoProducer {


	private final KafkaTemplate<String, Object> kafkaTemplate;
        
    @Value("${kafka.topic.meutopico}")
    private String meuTopico;
    
    @Bean
    public NewTopic meuTopico() {
        return TopicBuilder.name(meuTopico)
        	      .partitions(3)
        	      .replicas(1)
        	      .compact()
                  .config(TopicConfig.DELETE_RETENTION_MS_CONFIG, "100")
                  .config(TopicConfig.SEGMENT_MS_CONFIG, "100")
                  .config(TopicConfig.MIN_CLEANABLE_DIRTY_RATIO_CONFIG, "0.01")
        	      .build();
    }  


    public void sendUser(User user){
    	user.setDh(LocalDateTime.now());
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(meuTopico, user.getId(), user);
        
        future.addCallback(new ProducerCallback());
    }

}