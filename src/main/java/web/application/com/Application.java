package web.application.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import web.application.com.common.config.RabbitMqConfig;
//import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
//@EnableCaching(proxyTargetClass = true) // 开启缓存功能
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, RabbitAutoConfiguration.class})
//@ComponentScan(basePackages= {"com.api.rest", "com.middleware", "com.wsjc.common.core"})
//@ImportResource("classpath:spring/spring-context.xml")
public class Application extends SpringBootServletInitializer{
	// 入口
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	}

	// Java EE应用服务器配置，
	// 如果要使用tomcat来加载jsp的话就必须继承SpringBootServletInitializer类并且重写其中configure方法
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

}
