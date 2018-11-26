package web.application.com.common.config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * kafka  partition offset 记录
 */
public class KafkaOffsetConfig {

	private static final Log LOG = LogFactory.getLog(KafkaOffsetConfig.class);

	/*public static long  dev_delta_sctid_ibm_forecast_roadmap_delta_0 = 0;
	public static long 	dev_delta_sctid_ibm_forecast_roadmap_delta_1 = 0;
	public static long 	dev_delta_sctid_ibm_forecast_roadmap_delta_2 = 0;
	public static long 	dev_delta_sctid_ibm_forecast_roadmap_delta_3 = 0;
	public static long 	dev_delta_sctid_ibm_forecast_roadmap_delta_4 = 0;
	public static long 	dev_delta_sctid_ibm_forecast_roadmap_delta_5 = 0;

	public static long 	dev_delta_sctid_ibm_forecast_budget_delta_0 = 0;
	public static long 	dev_delta_sctid_ibm_forecast_budget_delta_1 = 0;
	public static long 	dev_delta_sctid_ibm_forecast_budget_delta_2 = 0;
	public static long 	dev_delta_sctid_ibm_forecast_budget_delta_3 = 0;
	public static long 	dev_delta_sctid_ibm_forecast_budget_delta_4 = 0;
	public static long 	dev_delta_sctid_ibm_forecast_budget_delta_5 = 0;*/
	

	private static Properties props = new Properties();
	
	private static String file_url = "";
	
	
	/**
	 * 只加载一次.
	 */
	static {
		
		InputStream proFile = null;
		try {
			//通过该类的类装载器获取文件的路径
			file_url = KafkaOffsetConfig.class.getClassLoader().getResource("config/kafka_offset.properties").getPath();
			
			LOG.info("=== load kafka_offset.properties and init sys param");
			proFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/kafka_offset.properties");
			props.load(proFile);
			
			/*dev_delta_sctid_ibm_forecast_roadmap_delta_0 = Long.valueOf(props.getProperty("dev_delta_sctid_ibm_forecast_roadmap_delta_0"));
			LOG.info("dev_delta_sctid_ibm_forecast_roadmap_delta_0 = " + dev_delta_sctid_ibm_forecast_roadmap_delta_0);
			
			dev_delta_sctid_ibm_forecast_roadmap_delta_1 = Long.valueOf(props.getProperty("dev_delta_sctid_ibm_forecast_roadmap_delta_1"));
			LOG.info("dev_delta_sctid_ibm_forecast_roadmap_delta_1 = " + dev_delta_sctid_ibm_forecast_roadmap_delta_1);
			
			dev_delta_sctid_ibm_forecast_roadmap_delta_2 = Long.valueOf(props.getProperty("dev_delta_sctid_ibm_forecast_roadmap_delta_2"));
			LOG.info("dev_delta_sctid_ibm_forecast_roadmap_delta_2 = " + dev_delta_sctid_ibm_forecast_roadmap_delta_2);
			
			dev_delta_sctid_ibm_forecast_roadmap_delta_3 = Long.valueOf(props.getProperty("dev_delta_sctid_ibm_forecast_roadmap_delta_3"));
			LOG.info("dev_delta_sctid_ibm_forecast_roadmap_delta_3 = " + dev_delta_sctid_ibm_forecast_roadmap_delta_3);
			
			dev_delta_sctid_ibm_forecast_roadmap_delta_4 = Long.valueOf(props.getProperty("dev_delta_sctid_ibm_forecast_roadmap_delta_4"));
			LOG.info("dev_delta_sctid_ibm_forecast_roadmap_delta_4 = " + dev_delta_sctid_ibm_forecast_roadmap_delta_4);
			
			dev_delta_sctid_ibm_forecast_roadmap_delta_5 = Long.valueOf(props.getProperty("dev_delta_sctid_ibm_forecast_roadmap_delta_5"));
			LOG.info("dev_delta_sctid_ibm_forecast_roadmap_delta_5 = " + dev_delta_sctid_ibm_forecast_roadmap_delta_5);

			
			
			dev_delta_sctid_ibm_forecast_budget_delta_0 = Long.valueOf(props.getProperty("dev_delta_sctid_ibm_forecast_budget_delta_0"));
			LOG.info("dev_delta_sctid_ibm_forecast_budget_delta_0 = " + dev_delta_sctid_ibm_forecast_budget_delta_0);
			
			dev_delta_sctid_ibm_forecast_budget_delta_1 = Long.valueOf(props.getProperty("dev_delta_sctid_ibm_forecast_budget_delta_1"));
			LOG.info("dev_delta_sctid_ibm_forecast_budget_delta_1 = " + dev_delta_sctid_ibm_forecast_budget_delta_1);
			
			dev_delta_sctid_ibm_forecast_budget_delta_2 = Long.valueOf(props.getProperty("dev_delta_sctid_ibm_forecast_budget_delta_2"));
			LOG.info("dev_delta_sctid_ibm_forecast_budget_delta_2 = " + dev_delta_sctid_ibm_forecast_budget_delta_2);
			
			dev_delta_sctid_ibm_forecast_budget_delta_3 = Long.valueOf(props.getProperty("dev_delta_sctid_ibm_forecast_budget_delta_3"));
			LOG.info("dev_delta_sctid_ibm_forecast_budget_delta_3 = " + dev_delta_sctid_ibm_forecast_budget_delta_3);
			
			dev_delta_sctid_ibm_forecast_budget_delta_4 = Long.valueOf(props.getProperty("dev_delta_sctid_ibm_forecast_budget_delta_4"));
			LOG.info("dev_delta_sctid_ibm_forecast_budget_delta_4 = " + dev_delta_sctid_ibm_forecast_budget_delta_4);
			
			dev_delta_sctid_ibm_forecast_budget_delta_5 = Long.valueOf(props.getProperty("dev_delta_sctid_ibm_forecast_budget_delta_5"));
			LOG.info("dev_delta_sctid_ibm_forecast_budget_delta_5 = " + dev_delta_sctid_ibm_forecast_budget_delta_5);*/
			
		} catch (Exception e) {
			LOG.error("=== load and init kafka_offset.properties exception:", e);
		}finally {
			try {
				proFile.close();
			} catch (IOException e) {
				LOG.error("inputsream close happend exception:", e);
				e.printStackTrace();
			}
		}
	}
	
	public static void setProperty(String key, String value) {
		//强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
        OutputStream fos = null;
		try {
			fos = new FileOutputStream(file_url);
			props.setProperty(key, value);
	        //以适合使用 load 方法加载到 Properties 表中的格式，
	        //将此 Properties 表中的属性列表（键和元素对）写入输出流
	        props.store(fos, "kafka partition offet update");
		} catch (Exception e) {
			LOG.error("update kafka_offset.properties exception:", e);
			e.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				LOG.error("outputsream close happend exception:", e);
			}
		}
       
	}
	
	
	/**
	 * 获取一个offset
	 * @param key
	 * @return
	 */
	public static long getProperties(String key) {
		long offset = Long.valueOf(props.getProperty(key));
		LOG.info("key = " +  key + ", offset=" + offset);
		return offset;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
