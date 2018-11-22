package web.application.com.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import web.application.com.common.entity.ApiCommonResultVo;
import web.application.com.middleware.kafka.KafkaProvider;
import web.application.com.mvc.service.KafkaMessageService;

/**
 * 
 * @author Tony
 *
 */
//@RestController
@Controller
@RequestMapping("/optimizer.es/")
public class TestController {

	private Logger log = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private KafkaMessageService kafkaMessageService;

	@Autowired
	private KafkaProvider kafkaProvider;

	/**
	 * test db2 conneciton info
	 * 
	 * @param ReceiveQueueVo
	 * @return
	 */
	@RequestMapping("test")
	@ResponseBody
	public ApiCommonResultVo test() {

		JSONObject data = new JSONObject();

		try {
			kafkaMessageService.castKafkaMsgSendEs(data);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return new ApiCommonResultVo(-1, "系统异常", e.getMessage());
		}

		return new ApiCommonResultVo(0, "success", "");
		// 调用service

	}
	
	
	
	
	/**
	 * test db2 conneciton info
	 * 
	 * @param ReceiveQueueVo
	 * @return
	 */
	@RequestMapping("send")
	@ResponseBody
	public ApiCommonResultVo send() {

		try {
			JSONObject data = new JSONObject();
			data.put("name", "阿贵");
			data.put("age", "20");
			data.put("sex", "男");
			kafkaProvider.sendMsg(data.toString());
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return new ApiCommonResultVo(-1, "系统异常", e.getMessage());
		}

		return new ApiCommonResultVo(0, "success", "");
		// 调用service

	}
	
	

}
