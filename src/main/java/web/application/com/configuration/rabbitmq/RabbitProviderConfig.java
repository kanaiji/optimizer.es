/*package web.application.com.configuration.rabbitmq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import web.application.com.common.config.RabbitMqConfig;
import web.application.com.middleware.rabbitmq.MsgSendConfirmCallBack;
import web.application.com.middleware.rabbitmq.MsgSendReturnCallback;


@Configuration
@Service("rabbitConfig")
public class RabbitProviderConfig {

    private Logger logger = LoggerFactory.getLogger(RabbitProviderConfig.class);

//    @Value("${spring.rabbitmq.host}")
    private String mqRabbitHost;

//    @Value("${spring.rabbitmq.port}")
    private String mqRabbitPort;

//    @Value("${spring.rabbitmq.username}")
    private String mqRabbitUserName;

//    @Value("${spring.rabbitmq.password}")
    private String mqRabbitPassword;

//    @Value("${spring.rabbitmq.virtual-host}")
    private String mqRabbitVirtualHost;

//    @Value("${spring.rabbitmq.publisher-confirms}")
    private String mqRabbitPublisherConfirms;
    
    
    public RabbitProviderConfig(){
    
    	mqRabbitHost = RabbitMqConfig.readConfig("spring.rabbitmq.host");
    	mqRabbitPort = RabbitMqConfig.readConfig("spring.rabbitmq.port");
    	mqRabbitUserName = RabbitMqConfig.readConfig("spring.rabbitmq.username");
    	mqRabbitPassword = RabbitMqConfig.readConfig("spring.rabbitmq.password");
    	mqRabbitVirtualHost = RabbitMqConfig.readConfig("spring.rabbitmq.virtual-host");
    	mqRabbitPublisherConfirms = RabbitMqConfig.readConfig("spring.rabbitmq.publisher-confirms");
        
    }
    
    @Bean("connectionFactory")
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(this.mqRabbitHost,Integer.valueOf(this.mqRabbitPort));
        connectionFactory.setUsername(this.mqRabbitUserName);
        connectionFactory.setPassword(this.mqRabbitPassword);
        connectionFactory.setVirtualHost(this.mqRabbitVirtualHost);
        connectionFactory.setPublisherConfirms(Boolean.valueOf(this.mqRabbitPublisherConfirms));
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setConfirmCallback(new MsgSendConfirmCallBack());//消息确认
        template.setReturnCallback(new MsgSendReturnCallback());//确认后回调
        return template;
    }



    *//******************* RMQ 队列初始化 *************************************//*
    
    #queue fanout
    public final static String data_fanout_queque_name = RabbitMqConfig.readConfig("data.fanout.queque.name");
    public final static String map_fanout_queque_name = RabbitMqConfig.readConfig("map.fanout.queque.name");
    public final static String push_direct_queque_name = RabbitMqConfig.readConfig("push.direct.queque.name");

    
    *//******************* rmq 路由初始化 *************************************//*
    
    #excheng fanout
    public final static String pc_default_fanout_exchange_name = RabbitMqConfig.readConfig("pc.default.fanout.exchange.name");
    #excheng direct
    public final static String pc_push_direct_exchange_name = RabbitMqConfig.readConfig("pc.push.direct.exchange.name");

    
    @Bean("data_fanout_queque_name")
    public Queue data_fanout_queque_name() {
        logger.info("RMQ-PRODUCTER|info|config-init|data_fanout_queque_name()..queueName: {}", data_fanout_queque_name);
        return new Queue(data_fanout_queque_name, true);
    }

    @Bean("map_fanout_queque_name")
    public Queue map_fanout_queque_name() {
        logger.info("RMQ-PRODUCTER|info|config-init|map_fanout_queque_name()..queueName: {}", map_fanout_queque_name);
        return new Queue(map_fanout_queque_name, true);
    }

    @Bean("push_direct_queque_name")
    public Queue push_direct_queque_name() {
        logger.info("RMQ-PRODUCTER|info|config-init|push_direct_queque_name()..queueName: {}", push_direct_queque_name);
        return new Queue(push_direct_queque_name, true);
    }


    @Bean
    DirectExchange pc_push_direct_exchange_name() {
        logger.info("RMQ-PRODUCTER|info|config-init|pc_push_direct_exchange_name()..route: {}", pc_push_direct_exchange_name);
        return new DirectExchange(pc_push_direct_exchange_name);
    }


    @Bean
    FanoutExchange pc_default_fanout_exchange_name() {
        logger.info("RMQ-PRODUCTER|info|config-init|pc_default_fanout_exchange_name()..route: {}", pc_default_fanout_exchange_name);
        return new FanoutExchange(pc_default_fanout_exchange_name);
    }


    *//******************* rmq 路由队列绑定 *********************************//*

    public final static String default_route_key = RabbitMqConfig.readConfig("default.route.key");

    public final static String push_route_key = RabbitMqConfig.readConfig("push.route.key");



    @Bean
    public Binding bindingMap() {
        logger.info("RMQ-PRODUCTER|info|config-init|default_route_key()..FountExchange");
        return BindingBuilder.bind(map_fanout_queque_name()).to(pc_default_fanout_exchange_name());
    }
    @Bean
    public Binding bindingData() {
    	logger.info("RMQ-PRODUCTER|info|config-init|default_route_key()..FountExchange");
    	return BindingBuilder.bind(data_fanout_queque_name()).to(pc_default_fanout_exchange_name());
    }


    @Bean
    public Binding bindingPush() {
        logger.info("RMQ-PRODUCTER|info|config-init|bindingResult()..DirectExchange");
        return BindingBuilder.bind(push_direct_queque_name()).to(pc_push_direct_exchange_name()).with(push_route_key);
    }

    
    
    
    

}
*/