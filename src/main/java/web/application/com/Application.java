package web.application.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import web.application.com.middleware.kafka.Init;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={ElasticsearchAutoConfiguration.class, ElasticsearchDataAutoConfiguration.class})
public class Application {
	
	// 入口
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		System.out.println("----------------启动kafka消费者----------------");
		Init.start();
		
	}
    
}