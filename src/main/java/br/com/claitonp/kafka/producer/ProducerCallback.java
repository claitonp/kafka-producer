package br.com.claitonp.kafka.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProducerCallback implements ListenableFutureCallback<SendResult<String, Object>> {
	

	@Override
	public void onSuccess(SendResult<String, Object> result) {
		ProducerRecord<String, Object> record = result.getProducerRecord();
		RecordMetadata recordMetadata = result.getRecordMetadata();
		log.info("callback sucess key: {} metadata: {} value: {}",  record.key(), recordMetadata, record.value());
	}

	@Override
	public void onFailure(Throwable ex) {
		log.info("callback failure: {}",  ex.getMessage());
	}
}