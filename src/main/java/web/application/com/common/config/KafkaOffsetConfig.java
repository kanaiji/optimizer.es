package web.application.com.common.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import web.application.com.common.uitls.StringUtil;

/**
 * kafka  partition offset 记录
 */
@Service
public class KafkaOffsetConfig {

	private Logger log = LoggerFactory.getLogger(KafkaOffsetConfig.class);

	
	private static Map<String, Long> offsetInit = Maps.newHashMap();
	
	
	//C:\\Users\\JingWangZou\\Desktop\\kafka_offset.properties
	public String file_url = "";
	
	
	public void setFile_Url(String url) {
		log.info("设置file_url 参数：" + url);
		file_url = url;
	}
	
	
	public void initOffSet() {
		InputStream in = null;
		try {
			//通过该类的类装载器获取文件的路径
//			file_url = KafkaOffsetConfig.class.getClassLoader().getResource("config/kafka_offset.properties").getPath();
			log.info("=== load local kafka_offset.properties and init sys param");
			if(!StringUtil.isEmpty(file_url)) {
				log.info("=== load local kafka_offset.properties and init sys param");
				List<File> listF = getFileList(file_url);
				for(File file: listF) {
					
					String name = file.getName();
					String key=name.substring(0, name.lastIndexOf("."));
					
					in = new FileInputStream(file);
					byte[] osByte = new byte[(int) file.length()];
					in.read(osByte);
					
					String os = new String(osByte, "utf-8");
					Long offset = Long.valueOf(os);
					offsetInit.put(key, offset);
					
					in.close();
				}
				
			}
			log.info("=== load init complet:" + offsetInit.toString());
			
		} catch (Exception e) {
			log.error("=== load and init kafka offset txt exception:", e);
		}
	}
	
	
	public void setProperty(String key, String value) {
		//强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
		log.info("存储offset key="+ key + ", offset=" + value);
        OutputStream fos = null;
        String path = file_url + "/" + key + ".txt";
		try {
			fos = new FileOutputStream(path);
			fos.write(value.getBytes("utf-8"));
			log.info("存储offset key="+ key + ", 存储完成！");
		} catch (Exception e) {
			log.error("update kafka_offset.properties exception:", e);
			e.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				log.error("outputsream close happend exception:", e);
			}
		}
       
	}
	
	
	/**
	 * 获取一个offset
	 * @param key
	 * @return
	 */
	public long getProperties(String key) {
		
		Long offset = offsetInit.get(key);
		if(null == offset) {
			log.info("该key="+key+"未初始化offset, 那么从头开始。");
			return 0;
		}
		log.info("offset key="+ key + ", offset=" +offset);
		return offset;
	}
	
	
	
	
	
	/**
	 * 获取文件下的所有offset 设置
	 * @param strPath
	 * @return
	 */
	public static List<File> getFileList(String strPath) {
		
		List<File> filelist = Lists.newArrayList();
		
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (fileName.endsWith("txt")) { // 判断文件名是否以.txt结尾
                    filelist.add(files[i]);
                } else {
                    continue;
                }
            }

        }
        return filelist;
    }
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		
		
		String path  = "C:\\offset";
		InputStream in = null;
		List<File> listF = getFileList(path);
		for(File file: listF) {
			
			String name = file.getName();
			String key=name.substring(0, name.lastIndexOf("."));
			
			in = new FileInputStream(file);
			byte[] osByte = new byte[(int) file.length()];
			in.read(osByte);
			
			String os = new String(osByte, "utf-8");
			Long offset = Long.valueOf(os);
			offsetInit.put(key, offset);
			
			in.close();
		}
		
		System.out.println(offsetInit);
		
		String key = "dev.delta.sctid.ibm_forecast_budget_delta_5";
		 OutputStream fos = null;
        path = path + "/" + key + ".txt";
		try {
			fos = new FileOutputStream(path);
			fos.write("256".getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
