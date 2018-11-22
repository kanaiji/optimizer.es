/*package web.application.com.configuration.rabbitmq;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.app.map.listener.MapConsumerMessageListener;

@Configuration
public class RabbitConsumerConfig {

	@Bean
	public SimpleMessageListenerContainer messageContainer(@Qualifier("connectionFactory") ConnectionFactory connectionFactory,
			@Qualifier("map_fanout_queque_name") Queue queue) {
		Queue[] q = new Queue[queues.split(",").length];
		for (int i = 0; i < queues.split(",").length; i++) {
			q[i] = new Queue(queues.split(",")[i]);
		}
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setQueues(queue);
		container.setExposeListenerChannel(true);
		//同时存在的最大消费者
		container.setMaxConcurrentConsumers(1);
		container.setConcurrentConsumers(1);
		//开启手动确认
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		container.setMessageListener(new MapConsumerMessageListener());
		return container;
	}
	
	
}
*/