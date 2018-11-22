//package web.application.com.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//@Configuration
//public class ThreadPoolConfig {
//
//	@Bean(name="threadPool")
////	<!-- 配置线程池 -->
//	public ThreadPoolTaskExecutor threadPool() {
//		
//		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
////		<!-- 线程池维护线程的最少数量 -->
//		threadPoolTaskExecutor.setCorePoolSize(2);
////		<!-- 线程池维护线程所允许的空闲时间 -->
//		threadPoolTaskExecutor.setKeepAliveSeconds(1000);
////		<!-- 线程池维护线程的最大数量 -->
//		threadPoolTaskExecutor.setMaxPoolSize(7);
////		<!-- 线程池所使用的缓冲队列 -->
//		threadPoolTaskExecutor.setQueueCapacity(6);
//		return threadPoolTaskExecutor;
//	}
//	
//	
//}
