package web.application.com.common.config;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * rabbitmq 配置文件
 */

public class RabbitMqConfig {

	private static final Log LOG = LogFactory.getLog(RabbitMqConfig.class);

	static Properties props = new Properties();
	
	/**
	 * 只加载一次.
	 */
	static {
		try {
			LOG.info("=== load mq.properties and init sys param");
			InputStream proFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/rabbitmq.properties");
			props.load(proFile);
		} catch (Exception e) {
			LOG.error("=== load and init mq exception:" + e);
		}
	}


	/**
     * 函数功能说明 ：读取配置项
     * @return void
     * @throws
     */
    public static String readConfig(String key) {
        return (String) props.get(key);
    }
}
