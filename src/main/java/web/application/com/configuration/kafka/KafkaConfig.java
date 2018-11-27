package web.application.com.configuration.kafka;

import java.util.Properties;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * 数据配置，进行初始化操作
 * 
 * @author sdc
 *
 */
@Configuration
@Service
public class KafkaConfig {
	
	private  final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);
	
	@Value("${kafka.consumer.bootstrap.servers}")
	private  String kafka_consumer_bootstrap_servers;
	
	@Value("${kafka.consumer.group.id}")
	private  String kafka_consumer_group_id;
	
	@Value("${kafka.consumer.enable.auto.commit}")
	private  String kafka_consumer_enable_auto_commit;
	
//	#smallest last
	@Value("${kafka.consumer.auto.offset.reset}")
	private  String kafka_consumer_auto_offset_reset;
	
	@Value("${kafka.consumer.max.poll.interval.ms}")
	private  String kafka_consumer_max_poll_interval_ms;
	
	@Value("${kafka.consumer.max.poll.records}")
	private  String kafka_consumer_max_poll_records;
	
	

	public  Properties getConfig() {
		Properties props = new Properties();
		/*
		 * System.out.println("Url is "+url);
		 * System.out.println("groupId is "+groupId+1);
		 */
		
		props.put("bootstrap.servers", kafka_consumer_bootstrap_servers);
		props.put("group.id", kafka_consumer_group_id);
		props.put("enable.auto.commit", kafka_consumer_enable_auto_commit);
		props.put("auto.offset.reset", kafka_consumer_auto_offset_reset);
//	    props.put("auto.offset.reset", "smallest");
//		props.put("auto.commit.interval.ms", "1000");
//	    props.put("session.timeout.ms", "30000");
		props.put("max.poll.interval.ms", kafka_consumer_max_poll_interval_ms);
		props.put("max.poll.records", kafka_consumer_max_poll_records);
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());
		
		
		/*props.put("bootstrap.servers", "devkz01.rtp.raleigh.ibm.com:5001");
		props.put("group.id", "optimizer-es2");
		props.put("enable.auto.commit", "false");
		props.put("auto.offset.reset", "earliest");
//	    props.put("auto.offset.reset", "smallest");
//		props.put("auto.commit.interval.ms", "1000");
//	    props.put("session.timeout.ms", "30000");
		props.put("max.poll.interval.ms", 300000);
		props.put("max.poll.records", 20000);
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());*/
		return props;
	}

}
