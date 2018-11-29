package web.application.com.middleware.kafka;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.application.com.common.config.KafkaOffsetConfig;
import web.application.com.common.constans.CommonConst;
import web.application.com.configuration.kafka.KafkaConfig;
import web.application.com.mvc.service.KafkaMessageService;


@Service("kafkaConsumerLinster")
public class KafkaConsumerLinster {

	private static Logger log = LoggerFactory.getLogger(KafkaConsumer.class);


	@Autowired
	private KafkaMessageService kafkaMessageService ;
	
	
	@Autowired
	public KafkaConfig kafkaConfig;
	
	//默认不记录
	public boolean isRecordOffSet = false;
		
	public void setIsRecordOffSet(boolean isRecordOffSet) {
		this.isRecordOffSet = isRecordOffSet;
	}
	

	public void subscribe_budget_delta(ExecutorService pool_budget_delta) {
		for (int i = 0; i < 6; i++) {
//		for (int i = 0; i < 1; i++) {
			final int index = i;
			pool_budget_delta.execute(() -> {
				System.out.println("creating the " + 1 + " thread");
				KafkaConsumer<String, String> consumer = null;
				try {
					consumer = new KafkaConsumer<>(kafkaConfig.getConfig());
					
					//指定主题
//					List<String> topics = new ArrayList<>();
//					topics.add(topic_budget_delta);
//					topics.add(topic_roadmap_delta);
//					consumer.subscribe(topics);
					
//					 consumer.seekToBeginning(consumer.assignment().toArray(),index);
					
					//指定主题的分区
					TopicPartition topicPartition = new TopicPartition(CommonConst.TOPIC_BUDGET_DELTA, index);
					consumer.assign(Arrays.asList(topicPartition));
//					List<TopicPartition> partitions = new ArrayList<TopicPartition>();
//					partitions.add(new TopicPartition(topic_budget_delta, index));
//					partitions.add(new TopicPartition(topic_roadmap_delta, index));
//					consumer.assign(partitions);
//                  指定从topic的分区的某个offset开始消费
//			        consumer.seekToBeginning(Arrays.asList(p));
					
					//判断是否需要从 设置的offset 开始 读取
					if(isRecordOffSet) {
						String offset_key = CommonConst.TOPIC_BUDGET_DELTA + "_" + index;
						long start_offset = KafkaOffsetConfig.getProperties(offset_key);
						log.info(Thread.currentThread() +  "offset_key = " +  offset_key + ", start_offset=" + start_offset);
						System.out.println(Thread.currentThread() +  "offset_key = " +  offset_key + ", start_offset=" + start_offset);
						consumer.seek(topicPartition, start_offset);
						log.info(Thread.currentThread() + " -->" + offset_key +" isn't 0 , so 接着该offset 继续！ ");
					}
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
					    	//刷新offset 值
					    	if(isRecordOffSet) {
								log.info(Thread.currentThread() + ",app start set offsetpath , so need record offset ....");
								String offset_key = CommonConst.TOPIC_BUDGET_DELTA + "_" + index;
								KafkaOffsetConfig.setProperty(offset_key, offset);
								log.info(Thread.currentThread() + " offset record comple...offset_key=" + offset_key );
							}
					    }
					    Thread.sleep(10000);
					}
				} catch (Exception e) {
					log.error("Exception when consuming messages {}", e);
				} finally {
					if (consumer != null) {
						consumer.close();
					}
					pool_budget_delta.shutdown();
				}
			});
		}
		pool_budget_delta.shutdown();

	}
	
	
	
	public void subscribe_roadmap_delta(ExecutorService pool_roadmap_delta) {
		for (int i = 0; i < 6; i++) {
//		for (int i = 0; i < 1; i++) {
			final int index = i;
			pool_roadmap_delta.execute(() -> {
				System.out.println("creating pool_roadmap_delta the " + 1 + " thread");
				KafkaConsumer<String, String> consumer = null;
				try {
					consumer = new KafkaConsumer<>(kafkaConfig.getConfig());
					
					//指定主题
//					List<String> topics = new ArrayList<>();
//					topics.add(topic_budget_delta);
//					topics.add(topic_roadmap_delta);
//					consumer.subscribe(topics);
//					 consumer.seekToBeginning(consumer.assignment().toArray(),index);
					
					//指定主题的分区
					TopicPartition topicPartition = new TopicPartition(CommonConst.TOPIC_ROADMAP_DELTA, index);
					consumer.assign(Arrays.asList(topicPartition));
//					List<TopicPartition> partitions = new ArrayList<TopicPartition>();
//					partitions.add(new TopicPartition(topic_budget_delta, index));
//					partitions.add(new TopicPartition(topic_roadmap_delta, index));
//					consumer.assign(partitions);
					
					//判断是否需要从 设置的offset 开始 读取
					if(isRecordOffSet) {
						String offset_key = CommonConst.TOPIC_BUDGET_DELTA + "_" + index;
						long start_offset = KafkaOffsetConfig.getProperties(offset_key);
						log.info(Thread.currentThread() +  "offset_key = " +  offset_key + ", start_offset=" + start_offset);
						System.out.println(Thread.currentThread() +  "offset_key = " +  offset_key + ", start_offset=" + start_offset);
						consumer.seek(topicPartition, start_offset);
						log.info(Thread.currentThread() + " -->" + offset_key +" isn't 0 , so 接着该offset 继续！ ");
					}
			        
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
					    	
					    	//刷新offset 值
					    	if(isRecordOffSet) {
								log.info(Thread.currentThread() + ",app start set offsetpath , so need record offset ....");
								String offset_key = CommonConst.TOPIC_BUDGET_DELTA + "_" + index;
								KafkaOffsetConfig.setProperty(offset_key, offset);
								log.info(Thread.currentThread() + " offset record comple...offset_key=" + offset_key );
							}
					    }
					    Thread.sleep(10000);
					}
				} catch (Exception e) {
					log.error("Exception when consuming messages {}", e);
				} finally {
					if (consumer != null) {
						consumer.close();
					}
					pool_roadmap_delta.shutdown();
				}
			});
		}

		pool_roadmap_delta.shutdown();

	}



	/*private Properties getConfig() {
		
		Properties props = new Properties();
		props.put("bootstrap.servers", "devkz01.rtp.raleigh.ibm.com:5001");
		props.put("group.id", "optimizer-es2");
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
	}*/
	
	
	
	
	
	

}
