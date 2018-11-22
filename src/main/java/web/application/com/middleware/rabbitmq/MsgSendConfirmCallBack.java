/*package web.application.com.middleware.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback {


    private Logger logger = LoggerFactory.getLogger(MsgSendConfirmCallBack.class);


    @Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		StringBuilder buf = new StringBuilder(1024); 
		buf.append("RabbitMQ-INFO|ConfirmCallBackListener-confirm()|Params:ack=").append(ack).append(" &cause=").append(cause).append(" &messageId=").append(correlationData.getId()) ;
		logger.info(buf.toString());
		
	}



}
*/