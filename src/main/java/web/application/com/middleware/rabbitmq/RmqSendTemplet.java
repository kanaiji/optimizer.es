/*package web.application.com.middleware.rabbitmq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.application.com.common.config.RabbitMqConfig;



@Service("rmqSendTemplet")
public class RmqSendTemplet {

	private Logger logger = LoggerFactory.getLogger(RmqSendTemplet.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;
	
	public void sendDefaultData(String message, String dataId) {
        try {
        	CorrelationData correlationData = new CorrelationData(dataId);
        	MessageProperties messageProperties = new MessageProperties();
        	messageProperties.setUserId(System.currentTimeMillis()+"");
            logger.info(new StringBuilder(128).append("RabbitMQ-INFO|RmqSendTemplet-sendDefaultData()|dataId:").append(dataId).toString());
            rabbitTemplate.send(RabbitMqConfig.readConfig("pc.default.fanout.exchange.name"),"",new Message(message.getBytes(), new MessageProperties()), correlationData);
        } catch (Exception e) {
        	logger.error("RabbitMQ-ERROR|RmqSendTemplet-sendDefaultData()|发送异常", e);
        }
	}
	

	*//**
     * 检测数据异步：推送
     *//*
	public void sendPushData(String message, String dataId) {
        try {
        	CorrelationData correlationData = new CorrelationData(dataId);
        	MessageProperties messageProperties = new MessageProperties();
        	messageProperties.setUserId(System.currentTimeMillis()+"");
            logger.info(new StringBuilder(128).append("RabbitMQ-INFO|RmqSendTemplet-sendPushData()|dataId:").append(dataId).toString());
            rabbitTemplate.send(RabbitMqConfig.readConfig("pc.push.direct.exchange.name"),RabbitMqConfig.readConfig("push.route.key"),new Message(message.getBytes(), new MessageProperties()), correlationData);
        } catch (Exception e) {
        	logger.error("RabbitMQ-ERROR|RmqSendTemplet-sendPushData()|发送异常", e);
        }
	}
	
	
	
}
*/