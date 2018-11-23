package web.application.com.middleware.kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import web.application.com.mvc.service.KafkaMessageService;

//@Component
public class KafkaConsumerLinster {

	private static Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

	private static ExecutorService pool = Executors.newFixedThreadPool(6);

//	 @Autowired
//     private KafkaTemplate kafkaTemplate;

//	@Autowired
	private static KafkaMessageService kafkaMessageService = new KafkaMessageService();

	/*
	 * @KafkaListener(topics = { "dev.delta.sctid.ibm_forecast_roadmap_delta" })
	 * public void processMessage(String content) {
	 * 
	 * try { System.out.println("消息被消费 :" + content);
	 * 
	 * JSONObject data = JSON.parseObject(content);
	 * 
	 * kafkaMessageService.castKafkaMsgSendEs(data);
	 * 
	 * } catch (Exception e) { log.error("kafka 消费者异常：" , e); }
	 * 
	 * }
	 */


	/*@SuppressWarnings("resource")
	public static void processMessage() {

		try {
			Properties props = new Properties();
			props.put("bootstrap.servers", "devkz01.rtp.raleigh.ibm.com:5001");
			props.put("group.id", "optimizer-es");
			props.put("enable.auto.commit", "true");
			props.put("auto.commit.interval.ms", "1000");
			props.put("session.timeout.ms", "30000");
			props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

			String topic_budget_delta = "dev.delta.sctid.ibm_forecast_budget_delta";
			String topic_roadmap_delta = "dev.delta.sctid.ibm_forecast_roadmap_delta";
			consumer.subscribe(Arrays.asList(topic_budget_delta, topic_roadmap_delta));
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(100);
				for (ConsumerRecord<String, String> record : records)
					System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(),
							record.value());
			}

//			kafkaMessageService.castKafkaMsgSendEs(data);

		} catch (Exception e) {
			log.error("kafka 消费者异常：", e);
		}

	}*/

	public static Properties getConfig() {
		Properties props = new Properties();
		/*
		 * System.out.println("Url is "+url);
		 * System.out.println("groupId is "+groupId+1);
		 */
		props.put("bootstrap.servers", "devkz01.rtp.raleigh.ibm.com:5001");
		props.put("group.id", "optimizer-es");
		props.put("enable.auto.commit", "true");
		props.put("auto.offset.reset", "earliest");
//	    props.put("auto.offset.reset", "smallest");
		props.put("auto.commit.interval.ms", "1000");
	    props.put("session.timeout.ms", "30000");
//		props.put("max.poll.interval.ms", 300000);
//		props.put("max.poll.records", 20000);
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());
		return props;
	}
	
	private final static String topic_budget_delta = "dev.delta.sctid.ibm_forecast_budget_delta";
	private final static String topic_roadmap_delta = "dev.delta.sctid.ibm_forecast_roadmap_delta";
	
	
	public static void subscribe() {
//		for (int i = 0; i < 6; i++) {
		for (int i = 0; i < 1; i++) {
			final int index = i;
			pool.execute(() -> {
				System.out.println("creating the " + 1 + " thread");
				KafkaConsumer<String, String> consumer = null;
				try {
					consumer = new KafkaConsumer<>(getConfig());
					List<String> topics = new ArrayList<>();
					topics.add(topic_budget_delta);
					topics.add(topic_roadmap_delta);
					consumer.subscribe(topics);
//					 consumer.seekToBeginning(consumer.assignment().toArray(),index);
//					consumer.assign(Arrays.asList(new TopicPartition(topic_budget_delta, index)));
					int j = 0;
					while (j < 100) {
						ConsumerRecords<String, String> records = consumer.poll(1000);
						System.out.println("Lewis kafka message size = " + records.count());
					    for (ConsumerRecord<String, String> record : records)
					      System.out.println("Lewis ----> " + record.offset() + ": " + record.value());
					}
				} catch (Exception e) {
					log.error("Exception when consuming messages {}", e);
				} finally {
					if (consumer != null) {
						consumer.close();
					}
					pool.shutdown();
				}
			});
		}

		pool.shutdown();

	}

}
