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
public class TopicProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(User user){
    	user.setDh(LocalDateTime.now());
        log.info("Payload enviado: {}", user);
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topicName, user.getLogin(), user);
        
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("Sent message=[" + user + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + user + "] due to : " + ex.getMessage());
            }
        });
    }
    
    @Value("${topic.name.producer}")
    private String topicName;

    @Bean
    public NewTopic adviceTopic() {
//        return new NewTopic(topicName, 3, (short) 1);
        return TopicBuilder.name(topicName)
        	      .partitions(3)
        	      .replicas(1)
        	      .compact()
                  .config(TopicConfig.DELETE_RETENTION_MS_CONFIG, "100")
                  .config(TopicConfig.SEGMENT_MS_CONFIG, "100")
                  .config(TopicConfig.MIN_CLEANABLE_DIRTY_RATIO_CONFIG, "0.01")
        	      .build();
    }

}