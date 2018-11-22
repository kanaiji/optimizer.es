/*
package web.application.com.middleware.rabbitmq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.app.map.core.MapTask;
import com.app.map.init.Init;
import com.rabbitmq.client.Channel;
import com.wsjc.manager.entity.WjDataLog;

*//**
 * <b>功能说明: TODO... 无注释
 * 每条消息最多执行两次
 *//*
@Component
public class MapConsumerMessageListener implements ChannelAwareMessageListener{
	
	private static Logger logger = LoggerFactory.getLogger(MapConsumerMessageListener.class);
	
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		// wjdatalog id
		String id = "";
		try {
			// 每次消费消息的个数 1 
			int mCount = 1 ; 
			// 如果设置消费者每次从队列中获取指定的条数channel.basicQos(1);，此时如果没有应答的话，消费者将不再继续获取
			// 应答包括 ：（1）确认应答：channel.basicAck(); 
			//（2）拒绝应答： channel.basicReject（）；requeue=true,表示将消息重新放入到队列中，false：表示直接从队列中删除，此时和basicAck(long deliveryTag, false)的效果一样
			channel.basicQos(mCount);

			WjDataLog wjDataLog = JSON.parseObject(new String(message.getBody()), WjDataLog.class);
	        if (wjDataLog == null) {
	        	logger.info("app-map|consumer|message serializable exception, wjDataLog is null");
	            return;
	        }
	        MessageProperties messageProperties = message.getMessageProperties();
            //deliveryTag是消息传送的次数
			long deliveryTag = messageProperties.getDeliveryTag();
			        
	        Init.tasks.put(new MapTask(wjDataLog, deliveryTag, channel)); // 添加到通知队列(第一次通知)
			
		} catch (Exception e) {
			
			// requeue：true 重新入队列，false：直接丢弃，相当于告诉队列可以直接删除掉
			if(message.getMessageProperties().getRedelivered()){
				channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);  
				logger.error("app-map|consumer|bad|DeliveryTag:{}|the message is fail 2 times, so we need delete the message, because it is bad message..{}",message.getMessageProperties().getDeliveryTag(), id, e);
			}else{
				channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);   // 再次回到队列
				//发生异常: 执行拒绝应答channel.basicReject()，将消息重新放入队列...
				logger.error("app-map|consumer|again|DeliveryTag:{}|find exception, the message return map-queue, wjDataLog id: {}", message.getMessageProperties().getDeliveryTag(), id, e);
			}

		}	
	}
		
}

*/