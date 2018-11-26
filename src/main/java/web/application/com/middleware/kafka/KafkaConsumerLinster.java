package web.application.com.middleware.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.application.com.common.constans.CommonConst;
import web.application.com.mvc.service.KafkaMessageService;

@Service("kafkaConsumerLinster")
public class KafkaConsumerLinster {

	private static Logger log = LoggerFactory.getLogger(KafkaConsumer.class);


	@Autowired
	private KafkaMessageService kafkaMessageService ;


	public static Properties getConfig() {
		Properties props = new Properties();
		/*
		 * System.out.println("Url is "+url);
		 * System.out.println("groupId is "+groupId+1);
		 */
		props.put("bootstrap.servers", "devkz01.rtp.raleigh.ibm.com:5001");
		props.put("group.id", "optimizer-es1");
		props.put("enable.auto.commit", "false");
		props.put("auto.offset.reset", "earliest");
//	    props.put("auto.offset.reset", "smallest");
//		props.put("auto.commit.interval.ms", "1000");
//	    props.put("session.timeout.ms", "30000");
		props.put("max.poll.interval.ms", 300000);
		props.put("max.poll.records", 20000);
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());
		return props;
	}
	
	
	public void subscribe_budget_delta() {
		for (int i = 0; i < 6; i++) {
//		for (int i = 0; i < 1; i++) {
			final int index = i;
			Init.pool_budget_delta.execute(() -> {
				System.out.println("creating the " + 1 + " thread");
				KafkaConsumer<String, String> consumer = null;
				try {
					consumer = new KafkaConsumer<>(getConfig());
					
					//指定主题
//					List<String> topics = new ArrayList<>();
//					topics.add(topic_budget_delta);
//					topics.add(topic_roadmap_delta);
//					consumer.subscribe(topics);
					
//					 consumer.seekToBeginning(consumer.assignment().toArray(),index);
					
					//指定主题的分区
					consumer.assign(Arrays.asList(new TopicPartition(CommonConst.TOPIC_BUDGET_DELTA, index)));
//					List<TopicPartition> partitions = new ArrayList<TopicPartition>();
//					partitions.add(new TopicPartition(topic_budget_delta, index));
//					partitions.add(new TopicPartition(topic_roadmap_delta, index));
//					consumer.assign(partitions);
					while (true) {
						ConsumerRecords<String, String> records = consumer.poll(1000);
						System.out.println(Thread.currentThread() + " topic :" + CommonConst.TOPIC_BUDGET_DELTA + " Lewis kafka records count = " + records.count() + ", Partition=" + index);
					    for (ConsumerRecord<String, String> record : records) {
					    	String key = record.key();
					    	String value = record.value();
					    	String offset = String.valueOf(record.offset());
					    	String partition = String.valueOf(record.partition());
//					    	log.info("Lewis kafka message info --> topic:" + CommonConst.TOPIC_BUDGET_DELTA + ", key=" + key + ",value=" + value + ", offset="+offset+", partition="+partition);
					    	System.out.println("Lewis kafka message info --> topic:" + CommonConst.TOPIC_BUDGET_DELTA + ", key=" + key + ",value=" + value + ", offset="+offset+", partition="+partition);
					    	kafkaMessageService.castKafkaMsgSendEs(CommonConst.ES_INDEX_BUDGET_DELTA, CommonConst.TOPIC_BUDGET_DELTA, partition, key, value, offset);
//					    	break;
					    }
					    Thread.sleep(30000);
					}
				} catch (Exception e) {
					log.error("Exception when consuming messages {}", e);
				} finally {
					if (consumer != null) {
						consumer.close();
					}
					Init.pool_budget_delta.shutdown();
				}
			});
		}
		Init.pool_budget_delta.shutdown();

	}
	
	
	
	public void subscribe_roadmap_delta() {
		for (int i = 0; i < 6; i++) {
//		for (int i = 0; i < 1; i++) {
			final int index = i;
			Init.pool_roadmap_delta.execute(() -> {
				System.out.println("creating pool_roadmap_delta the " + 1 + " thread");
				KafkaConsumer<String, String> consumer = null;
				try {
					consumer = new KafkaConsumer<>(getConfig());
					
					//指定主题
//					List<String> topics = new ArrayList<>();
//					topics.add(topic_budget_delta);
//					topics.add(topic_roadmap_delta);
//					consumer.subscribe(topics);
//					 consumer.seekToBeginning(consumer.assignment().toArray(),index);
					
					//指定主题的分区
					consumer.assign(Arrays.asList(new TopicPartition(CommonConst.TOPIC_ROADMAP_DELTA, index)));
//					List<TopicPartition> partitions = new ArrayList<TopicPartition>();
//					partitions.add(new TopicPartition(topic_budget_delta, index));
//					partitions.add(new TopicPartition(topic_roadmap_delta, index));
//					consumer.assign(partitions);
					while (true) {
						ConsumerRecords<String, String> records = consumer.poll(1000);
						System.out.println(Thread.currentThread() + " topic :" + CommonConst.TOPIC_ROADMAP_DELTA +" Lewis kafka message size = " + records.count() + ", Partition=" + index);
					    /*for (ConsumerRecord<String, String> record : records) {
					    	System.out.println("Lewis ----> " + record.offset() + ": " + record.value());
					    	break;
					    }*/
						for (ConsumerRecord<String, String> record : records) {
					    	String key = record.key();
					    	String value = record.value();
					    	String offset = String.valueOf(record.offset());
					    	String partition = String.valueOf(record.partition());
//					    	log.info("Lewis kafka message info --> topic:" + CommonConst.TOPIC_BUDGET_DELTA + ", key=" + key + ",value=" + value + ", offset="+offset+", partition="+partition);
					    	System.out.println("Lewis kafka message info --> topic:" + CommonConst.TOPIC_ROADMAP_DELTA + ", key=" + key + ",value=" + value + ", offset="+offset+", partition="+partition);
					    	kafkaMessageService.castKafkaMsgSendEs(CommonConst.ES_INDEX_ROADMAP_DELTA, CommonConst.TOPIC_ROADMAP_DELTA, partition, key, value, offset);
//					    	break;
					    }
					    Thread.sleep(10000);
					}
				} catch (Exception e) {
					log.error("Exception when consuming messages {}", e);
				} finally {
					if (consumer != null) {
						consumer.close();
					}
					Init.pool_roadmap_delta.shutdown();
				}
			});
		}

		Init.pool_roadmap_delta.shutdown();

	}
	
	
	
	
	
	

}
