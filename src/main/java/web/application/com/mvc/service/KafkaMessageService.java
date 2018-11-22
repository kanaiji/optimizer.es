package web.application.com.mvc.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;


/**
 * ES服务端
 * 
 * @author sdc
 *
 */
public interface KafkaMessageService {
	
	/**
	 * 转化添加
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public void castKafkaMsgSendEs(JSONObject data) throws Exception;
    
}
