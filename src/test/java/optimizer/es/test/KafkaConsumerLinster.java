package optimizer.es.test;
//package web.application.com.middleware.kafka;
//
//import java.util.Arrays;
//import java.util.Properties;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.TopicPartition;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//
//import web.application.com.mvc.service.KafkaMessageService;
//
//@Component
//public class KafkaConsumerLinster {
//
//	private static Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
//
//	private static ExecutorService pool_budget_delta = Executors.newFixedThreadPool(6);
//	
//	private static ExecutorService pool_roadmap_delta = Executors.newFixedThreadPool(6);
//
//	@Autowired
//	private KafkaMessageService kafkaMessageService ;
//
//
//	public static Properties getConfig() {
//		Properties props = new Properties();
//		/*
//		 * System.out.println("Url is "+url);
//		 * System.out.println("groupId is "+groupId+1);
//		 */
//		props.put("bootstrap.servers", "devkz01.rtp.raleigh.ibm.com:5001");
//		props.put("group.id", "optimizer-es1");
//		props.put("enable.auto.commit", "false");
//		props.put("auto.offset.reset", "earliest");
////	    props.put("auto.offset.reset", "smallest");
////		props.put("auto.commit.interval.ms", "1000");
////	    props.put("session.timeout.ms", "30000");
//		props.put("max.poll.interval.ms", 300000);
//		props.put("max.poll.records", 20000);
//		props.put("key.deserializer", StringDeserializer.class.getName());
//		props.put("value.deserializer", StringDeserializer.class.getName());
//		return props;
//	}
//	
//	private final static String topic_budget_delta = "dev.delta.sctid.ibm_forecast_budget_delta";
//	private final static String topic_roadmap_delta = "dev.delta.sctid.ibm_forecast_roadmap_delta";
//	
//	
//	public static void subscribe_budget_delta() {
//		for (int i = 0; i < 6; i++) {
////		for (int i = 0; i < 1; i++) {
//			final int index = i;
//			pool_budget_delta.execute(() -> {
//				System.out.println("creating the " + 1 + " thread");
//				KafkaConsumer<String, String> consumer = null;
//				try {
//					consumer = new KafkaConsumer<>(getConfig());
//					
//					//指定主题
////					List<String> topics = new ArrayList<>();
////					topics.add(topic_budget_delta);
////					topics.add(topic_roadmap_delta);
////					consumer.subscribe(topics);
////					consumer.seekToBeginning(consumer.assignment().toArray(),index);
//					
//					//指定主题的分区
//					consumer.assign(Arrays.asList(new TopicPartition(topic_budget_delta, index)));
////					List<TopicPartition> partitions = new ArrayList<TopicPartition>();
////					partitions.add(new TopicPartition(topic_budget_delta, index));
////					partitions.add(new TopicPartition(topic_roadmap_delta, index));
////					consumer.assign(partitions);
//					while (true) {
//						ConsumerRecords<String, String> records = consumer.poll(1000);
//						System.out.println("Lewis kafka records size = " + records.count());
//					    for (ConsumerRecord<String, String> record : records) {
//					    	String key = record.key();
//					    	String value = record.value();
//					    	long offset = record.offset();
//					    	System.out.println("topic " + topic_budget_delta + " key :" + key + " offset :" + offset);
//					    	System.out.println("topic " + topic_budget_delta + " value :" + value );
//					    	
////					    	JSONObject jsonMsg = JSON.parseObject(value);
////					    	kafkaMessageService.castKafkaMsgSendEs(jsonMsg);
//					    	break;
//					    }
//					    Thread.sleep(30000);
//					}
//				} catch (Exception e) {
//					log.error("Exception when consuming messages {}", e);
//				} finally {
//					if (consumer != null) {
//						consumer.close();
//					}
//					pool_budget_delta.shutdown();
//				}
//			});
//		}
//		pool_budget_delta.shutdown();
//
//	}
//	
//	
//	//好用版本，可以消费到消息。
//	public static void subscribe_roadmap_delta() {
////		for (int i = 0; i < 6; i++) {
//		for (int i = 0; i < 1; i++) {
////			final int index = i;
//			pool_roadmap_delta.execute(() -> {
//				System.out.println("creating pool_roadmap_delta the " + 1 + " thread");
//				KafkaConsumer<String, String> consumer = null;
//				try {
//					consumer = new KafkaConsumer<>(getConfig());
//					
//					//指定主题
////					List<String> topics = new ArrayList<>();
////					topics.add(topic_budget_delta);
////					topics.add(topic_roadmap_delta);
////					consumer.subscribe(topics);
////					 consumer.seekToBeginning(consumer.assignment().toArray(),index);
//					
//					//指定主题的分区
//					consumer.assign(Arrays.asList(new TopicPartition(topic_roadmap_delta, 0)));
////					List<TopicPartition> partitions = new ArrayList<TopicPartition>();
////					partitions.add(new TopicPartition(topic_budget_delta, index));
////					partitions.add(new TopicPartition(topic_roadmap_delta, index));
////					consumer.assign(partitions);
//					while (true) {
//						ConsumerRecords<String, String> records = consumer.poll(1000);
//						System.out.println("Lewis kafka records size = " + records.count());
//					    for (ConsumerRecord<String, String> record : records) {
//					    	System.out.println("Lewis ----> " + record.offset() + ": " + record.value());
//					    	break;
//					    }
//					    Thread.sleep(30000);
//					}
//				} catch (Exception e) {
//					log.error("Exception when consuming messages {}", e);
//				} finally {
//					if (consumer != null) {
//						consumer.close();
//					}
//					pool_roadmap_delta.shutdown();
//				}
//			});
//		}
//
//		pool_roadmap_delta.shutdown();
//
//	}
//	
//	
//	
//	
//	
//	
//
//}
