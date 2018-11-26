package optimizer.es.test;
//package forecasting.node.kafka.consumer.entity.topic;
//
//import akka.actor.ActorRef;
//import akka.actor.ActorSystem;
//import com.google.inject.Inject;
//import com.google.inject.Singleton;
//import com.google.inject.name.Named;
//import forecasting.node.kafka.consumer.workunit.IbmForecastRoadmapActor;
//import org.apache.kafka.clients.consumer.*;
//import org.apache.kafka.common.TopicPartition;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import play.Logger;
//
//
//import java.util.*;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//@Singleton
//public class ForecastDealListTopic implements Topic {
//
//    private final Logger.ALogger logger = Logger.of(this.getClass());
//
//    @Inject
//    @Named("forecast_deal_list_topic")
//    private String topicName;
//
//    @Inject
//    @Named("kafka.server.url")
//    private String url;
//
//
//    @Inject
//    @Named("enable.auto.commit")
//    private boolean commitStrategy;
//
//    @Inject
//    @Named("forecast_deal_list_groupId")
//    private String groupId;
//
//
//    private ExecutorService pool;
//
//
//    @Inject
//    private ActorSystem system;
//
//    ActorRef ibm_forecast_roadmap_actor;
//
//    @Inject
//    public ForecastDealListTopic(@Named("ibm-forecast-roadmap-actor") ActorRef ibm_forecast_roadmap_actor){
//       this.ibm_forecast_roadmap_actor = ibm_forecast_roadmap_actor;
//       pool = Executors.newFixedThreadPool(6);
//    }
//
//
//    @Override
//    public Properties getConfig() {
//        Properties props = new Properties();
//       /* System.out.println("Url is "+url);
//        System.out.println("groupId is "+groupId+1);*/
//        props.put("bootstrap.servers", url);
//        props.put("group.id", groupId);
//        props.put("enable.auto.commit",commitStrategy);
//        props.put("auto.offset.reset", "earliest");
//        //props.put("auto.offset.reset", "smallest");
//        props.put("max.poll.interval.ms",300000);
//        props.put("max.poll.records",20000);
//        props.put("key.deserializer", StringDeserializer.class.getName());
//        props.put("value.deserializer", StringDeserializer.class.getName());
//        return props;
//    }
//
//   /* @Override
//    public void subscribe() {
//        pool.execute(() -> {
//            KafkaConsumer<String, String> consumer = null;
//            consumer = new KafkaConsumer<String, String>(getConfig());
//            consumer.subscribe(Arrays.asList(topicName));
//            int i = 0;
//            while(i<1){
//                ConsumerRecords<String, String> records = consumer.poll(1);
//                System.out.println("Load from Topic "+records.count());
//                for (ConsumerRecord<String, String> record : records) {
//                    System.out.println("Now is loading " + record.toString());
//                   *//* IbmForecastRoadMap r = new IbmForecastRoadMap();
//                    r.setId("123");
//                    r.setDateEntered(new Timestamp(new Date().getTime()));
//                    r.setDateModified(new Timestamp(new Date().getTime()));
//                    r.setDeleted(1);
//                    r.setFactType(1);
//                    r.setFdimCmrId("123");
//                    r.setFdimMiscId("123");
//                    r.setFdimProductId("32");
//                    r.setFdimRoadmapId("23332");
//                    r.setFdimProductId("3232");
//                    r.setManagerAmountUsd(BigDecimal.valueOf(2312332));*//*
//                    ActorRef actor = system.actorOf(IbmForecastRoadmapActor.getProps(),"IBM_FORECAST_ROADMAP_partition"+Math.random());
//                    IbmForecastRoadmapActor.IbmForecastRoadmapEvent message = new IbmForecastRoadmapActor.IbmForecastRoadmapEvent(record);
//                    actor.tell(message,null);
//                    System.out.println("result ");
//                }
//            }
//        });
//
//    }
//*/
//   @Override
//    public void subscribe() {
//        for(int i=0;i<6;i++) {
//            final int index = i;
//            pool.execute(() -> {
//                System.out.println("creating the "+1+" thread");
//                KafkaConsumer<String, String> consumer = null;
//                try {
//                    consumer = new KafkaConsumer<>(getConfig());
//                    List<String> topics = new ArrayList<>();
//                    topics.add(topicName);
//                    //consumer.subscribe(topics);
//                    //consumer.seekToBeginning(consumer.assignment().toArray(),index);
//                    consumer.assign(Arrays.asList(new TopicPartition(topicName,index)));
//                    int j = 0;
//                    while (j < 100) {
//                        ConsumerRecords<String, String> records = consumer.poll(1000*20);
//                         //logger.info("Thread {} is loading  {} records",index,records.count());
//                        //for (ConsumerRecord<String, String> record : records) {
//                            IbmForecastRoadmapActor.IbmForecastRoadmapEvent message = new IbmForecastRoadmapActor.IbmForecastRoadmapEvent(records);
//                            ibm_forecast_roadmap_actor.tell(message,null);
//                    }
//                } catch (Exception e) {
//                    logger.error("Exception when consuming messages {}",e);
//                } finally {
//                    if (consumer != null) {
//                        consumer.close();
//                    }
//                    pool.shutdown();
//                }
//            });
//        }
//
//        pool.shutdown();
//
//    }
//
///*    @Override
//    public void subscribe() {
//        for(int i=0;i<partition_count;i++) {
//            final int index = i;
//            pool.execute(() -> {
//                System.out.println("creating the "+index+" thread");
//                KafkaConsumer<String, String> consumer = null;
//                try {
//                    consumer = new KafkaConsumer<>(getConfig());
//                    consumer.assign(Arrays.asList(new TopicPartition(topicName, index)));
//                    int j = 0;
//                    while (j < 100) {
//                        System.out.println("Loading from Topic ");
//                        ConsumerRecords<String, String> records = consumer.poll(1);
//                        System.out.println("Load from Topic "+records.count());
//                         for (ConsumerRecord<String, String> record : records) {
//                             System.out.println("Thread " + index + " is loading " + record.toString());
//                             IbmForecastRoadMap r = new IbmForecastRoadMap();
//                             r.setId("123");
//                             r.setDateEntered(new Timestamp(new Date().getTime()));
//                             r.setDateModified(new Timestamp(new Date().getTime()));
//                             r.setDeleted(1);
//                             r.setFactType(1);
//                             r.setFdimCmrId("123");
//                             r.setFdimMiscId("123");
//                             r.setFdimProductId("32");
//                             r.setFdimRoadmapId("23332");
//                             r.setFdimProductId("3232");
//                             r.setManagerAmountUsd(BigDecimal.valueOf(2312332));
//                             ActorRef actor = system.actorOf(IbmForecastRoadmapActor.getProps(),"IBM_FORECAST_ROADMAP_partition_"+index+"_"+Math.random());
//                             IbmForecastRoadmapActor.IbmForecastRoadmapEvent message = new IbmForecastRoadmapActor.IbmForecastRoadmapEvent(record);
//                             actor.tell(message,null);
//                             System.out.println("result ");
//                         }
//                         //j++;
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (consumer != null) {
//                        consumer.close();
//                    }
//                    //pool.shutdown();
//                }
//            });
//        }
//
//        pool.shutdown();
//
//    }*/
//
//
//    @Override
//    public boolean processMessage() {
//
//        return false;
//    }
//
//    @Override
//    public void notifyRelatedService() {
//
//    }
//
//    @Override
//    public void init() {
//        subscribe();
//    }
//
//}
