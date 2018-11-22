package web.application.com.common.config;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * jedis设置型配置
 */

public class RedisConfig {

	private static final Log LOG = LogFactory.getLog(RedisConfig.class);

	static Properties props = new Properties();
	
	/**
	 * 只加载一次.
	 */
	static {
		try {
			LOG.info("=== load reids.properties and init sys param");
			InputStream proFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/jedis.properties");
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
