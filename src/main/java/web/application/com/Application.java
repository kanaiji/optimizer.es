package web.application.com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import web.application.com.common.config.KafkaOffsetConfig;
import web.application.com.common.uitls.SpringContextUtil;
import web.application.com.middleware.kafka.Init;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={ElasticsearchAutoConfiguration.class, ElasticsearchDataAutoConfiguration.class})
public class Application {
	
	private static Logger log = LoggerFactory.getLogger(Application.class);
	
	// 入口
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
		
		boolean isRecordOffSet = false;
		int leths = args.length;
		if(leths == 0) {
			log.info("启动未设置offset路径...则使用项目中自带的offset.properties 文件。");
		}else {
			String offSetPath = args[0];
			log.info("启动设置参数 offSetPath：" + offSetPath);
			SpringContextUtil springContextUtil = new SpringContextUtil();
			KafkaOffsetConfig KafkaOffsetConfig = (KafkaOffsetConfig) springContextUtil.getBean("kafkaOffsetConfig");
			KafkaOffsetConfig.setFile_Url(offSetPath);
			isRecordOffSet = true;
		}
		
		
		System.out.println("----------------启动kafka消费者----------------");
		Init.start(isRecordOffSet);
	}
    
}