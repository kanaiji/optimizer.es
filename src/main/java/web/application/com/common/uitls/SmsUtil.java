package web.application.com.common.uitls;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 类功能说明：阿里大鱼短信接口
 */
public class SmsUtil {

	private static final Log LOG = LogFactory.getLog(SmsUtil.class);

	/*************************** 大鱼基本信息 **********************************/

	// 产品名称:云通信短信API产品,开发者无需替换
	static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	static final String domain = "dysmsapi.aliyuncs.com";
	static final String accessKeyId = "LTAIR4cBkHQmyyyL";
	static final String accessKeySecret = "TldITfe1Jx0bb7CiWgKZ85Ih5bc3MU";

	//
	public static void main(String[] args) {

		String phone = "13998641323" ; // "18041168758"
		String code = Tools.getRandomNum()+"" ;
		
		try {
			//大鱼
//			sendCodeDyu(phone , code);
			   
			// 不合格数据推送
//			sendNoPassData(phone, "‘时间不合格’ ‘温度不合格’", "2017-02-23 12:33:55"); // 2017年06月26日 10时52分53秒
			
			// 登录验证码
			sendLoginCode(phone, code);
			
			// 修改密码验证码
//			sendUpdatePass(phone, "135zz");
			
			// 修改密码的验证码
//			sendUpdatePassCode(phone, code);

			//MNS
//			sendCodeMns(phone , code);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	public static SendSmsResponse sendCodeDyu(String moblie, String content) throws Exception {

		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		LOG.info("阿里大鱼加载配置信息------");
		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(moblie);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName("智慧卫监");
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode("payTime"); // 正式模版
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		request.setTemplateParam("{\"content\":\""+content+"\"}");
		LOG.info("阿里大鱼设置请求参数------content :"+ content  );
		// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");

		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("yourOutId");

		// hint 此处可能会抛出异常，注意catch
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		LOG.info("阿里大鱼发送完成------");
		return sendSmsResponse;

	}
	
	
	/**
	 * 不合格数据，短信推送
	 * @param moblie
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static void sendNoPassData(String moblie, String test_time , String info ) throws Exception {
		
		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		LOG.info("阿里大鱼加载配置信息------");
		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(moblie);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName("智慧卫监");
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode("SMS_130913338"); // 正式模版
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		request.setTemplateParam("{\"test_time\":\""+test_time+"\",\"info\":\""+info+"\"}");
		LOG.info("阿里大鱼设置请求参数------content :"+ info + "  " + test_time  );
		// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");
	
		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("yourOutId");
		try {
			// hint 此处可能会抛出异常，注意catch
//			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			acsClient.getAcsResponse(request);
			LOG.info("阿里大鱼发送完成------");
		} catch (Exception e) {
			LOG.info("阿里大鱼发送异常------"+e.getMessage());
			// hint 此处可能会抛出异常，注意catch
			acsClient.getAcsResponse(request);
		}
		

	}
	
	
	/**
	 * 修改密码，短信推送
	 * @param moblie
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static void sendUpdatePass(String moblie, String password  ) throws Exception {
		
		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		LOG.info("阿里大鱼加载配置信息------");
		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(moblie);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName("智慧卫监");
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode("SMS_130913533"); // 正式模版
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		request.setTemplateParam("{\"password\":\""+password+"\"}");
		LOG.info("阿里大鱼设置请求参数------password :"+ password  );
		// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");
	
		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("yourOutId");
		try {
			// hint 此处可能会抛出异常，注意catch
//			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			acsClient.getAcsResponse(request);
			LOG.info("阿里大鱼发送完成------");
		} catch (Exception e) {
			LOG.info("阿里大鱼发送异常------"+e.getMessage());
			// hint 此处可能会抛出异常，注意catch
			acsClient.getAcsResponse(request);
		}
		

	}
	
	
	/**
	 * 修改密码的验证码，短信推送
	 * @param moblie
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static void sendUpdatePassCode(String moblie, String code  ) throws Exception {
		
		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		LOG.info("阿里大鱼加载配置信息------");
		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(moblie);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName("智慧卫监");
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode("SMS_131760094"); // 正式模版
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		request.setTemplateParam("{\"code\":\""+code+"\"}");
		LOG.info("阿里大鱼设置请求参数------code :"+ code  );
		// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");
	
		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("yourOutId");
		try {
			// hint 此处可能会抛出异常，注意catch
//			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			acsClient.getAcsResponse(request);
			LOG.info("阿里大鱼发送完成------");
		} catch (Exception e) {
			LOG.info("阿里大鱼发送异常------"+e.getMessage());
			// hint 此处可能会抛出异常，注意catch
			acsClient.getAcsResponse(request);
		}

	}
	
	
	/**
	 * 登录的验证码，短信推送
	 * @param moblie
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static void sendLoginCode(String moblie, String code  ) throws Exception {
		
		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		LOG.info("阿里大鱼加载配置信息------");
		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(moblie);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName("智慧卫监");
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode("SMS_131820116"); // 正式模版
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		request.setTemplateParam("{\"code\":\""+code+"\"}");
		LOG.info("阿里大鱼设置请求参数------code :"+ code  );
		// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");
	
		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId("yourOutId");
		try {
			// hint 此处可能会抛出异常，注意catch
//			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			acsClient.getAcsResponse(request);
			LOG.info("阿里大鱼发送完成------");
		} catch (Exception e) {
			LOG.info("阿里大鱼发送异常------"+e.getMessage());
			// hint 此处可能会抛出异常，注意catch
			acsClient.getAcsResponse(request);
		}

	}
	
	

	
}
