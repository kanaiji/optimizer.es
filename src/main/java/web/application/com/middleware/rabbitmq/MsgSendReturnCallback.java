/*package web.application.com.middleware.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class MsgSendReturnCallback implements RabbitTemplate.ReturnCallback {

    private Logger logger = LoggerFactory.getLogger(MsgSendReturnCallback.class);

    @Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey){
		MessageProperties mp = message.getMessageProperties() ;
        String datatype = mp.getType() ;
        JSONObject json = JSON.parseObject(new String(message.getBody()));
		StringBuilder buf = new StringBuilder(1024); 
		buf.append("RabbitMQ-INFO|ReturnCallBackListener-returnedMessage()|Params:replyCode=").append(replyCode).append(" &replyText=");
		buf.append(replyText).append(" &exchange=").append(exchange).append(" &routingKey=").append(routingKey).append("&dataType=").append(datatype).append("&messageId=").append(json.get("id")) ;
		logger.info(buf.toString());
		
		*//**
		 * 记录数据库mq发送失败日志
		 *//*
	}


}
*/