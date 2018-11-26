package web.application.com.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import web.application.com.common.constans.CommonConst;
import web.application.com.middleware.elasticsearch.EslaticSearchTemplet;

@Service
public class KafkaMessageService {

	
	@Autowired
	private EslaticSearchTemplet eslaticSearchTemplet;
	
	
	public void castKafkaMsgSendEs(String index, String topic, String partition, String key, String value, String offset) throws Exception {
		
		JSONObject data = new JSONObject();
		data.put("topic", topic);
		data.put("partition", partition);
		data.put("offset", offset);
		data.put("key", key);
		data.put("value", value);
		
		eslaticSearchTemplet.addTargetDataALL(data, index, CommonConst.ES_INDEX_TYPE_JSON, "");

	}

}
