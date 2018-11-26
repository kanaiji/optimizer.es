package web.application.com.middleware.kafka;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.application.com.common.uitls.SpringContextUtil;

/**
 * 功能说明：处理业务
 */
//@WebServlet(name="init-app", urlPatterns= {"/init/*"}, loadOnStartup=2)
public class Init {
	

	private static Logger log = LoggerFactory.getLogger(Init.class);
	
	
	public static ExecutorService pool_budget_delta = Executors.newFixedThreadPool(6);
	
	public static ExecutorService pool_roadmap_delta = Executors.newFixedThreadPool(6);
	

	public static void start() {
		
		SpringContextUtil springContextUtil = new SpringContextUtil();
		try {
			KafkaConsumerLinster kafkaConsumerLinster = (KafkaConsumerLinster) springContextUtil.getBean("kafkaConsumerLinster");
					
			log.warn("optimizer.es start lister subscribe_budget_delta...successful");
			kafkaConsumerLinster.subscribe_budget_delta();
			log.warn("subscribe_budget_delta...successful");
			
			log.warn("optimizer.es start lister subscribe_roadmap_delta...successful");
			kafkaConsumerLinster.subscribe_roadmap_delta();
			log.warn("subscribe_roadmap_delta...successful");

		} catch (Exception e) {
			log.error("connection|application start error:", e);
		}finally{
			//提前GC回收。
			springContextUtil = null;
		}
	}
	
	

}
