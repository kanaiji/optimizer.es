package web.application.com.middleware.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import web.application.com.mvc.controller.TestController;

@Component
public class KafkaProvider {

	private Logger log = LoggerFactory.getLogger(TestController.class);
	
	
	private final String topic = "optimizer.es";
	

	@Autowired
	private KafkaTemplate<?, String> kafkaTemplate;

	public void sendMsg(String jsonMsg) {

		try {
			kafkaTemplate.send(topic, jsonMsg);
		} catch (Exception e) {
			log.error("kafka 消费者异常：", e);
		}

	}
}
