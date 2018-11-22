package web.application.com.middleware.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import web.application.com.mvc.controller.TestController;
import web.application.com.mvc.service.KafkaMessageService;

@Component
public class KafkaConsumer {

	private Logger log = LoggerFactory.getLogger(TestController.class);
	
//	 @Autowired
//     private KafkaTemplate kafkaTemplate;
	 
	@Autowired
	private KafkaMessageService kafkaMessageService;

	@KafkaListener(topics = { "dev.delta.sctid.ibm_forecast_roadmap_delta" })
	public void processMessage(String content) {
		
		try {
			System.out.println("消息被消费" + content);

			JSONObject data = JSON.parseObject(content);

			kafkaMessageService.castKafkaMsgSendEs(data);
			
		} catch (Exception e) {
			log.error("kafka 消费者异常：" , e);
		}

	}
}
