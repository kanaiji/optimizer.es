package web.application.com.common.config;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 系统设置型配置
 */

public class SystemConfig {

	private static final Log LOG = LogFactory.getLog(SystemConfig.class);

	/** 支付网关请求地址 **/
	public static String GATEWAY_URL = "";

	/** 微信支付APPID **/
	public static String WEIXIN_PAY_APP_ID = "";
	/** 微信支付APP_SECRET **/
	public static String WEIXIN_PAY_APP_SECRET = "";
	/** 微信支付REDIRECT_URL **/
	public static String WEIXIN_PAY_REDIRECT_URL = "";

	/** 微信公众号APPID **/
	public static String WEIXIN_GZH_APP_ID = "";
	/** 微信公众号APP_SECRET **/
	public static String WEIXIN_GZH_APP_SECRET = "";
	/** 微信公众号REDIRECT_URL **/
	public static String WEIXIN_GZH_REDIRECT_URL = "";
	/** 微信公众号推送模版id **/
	public static String WEIXIN_GZH_TEMPLATEID = "";

	/** FTP字符编码 **/
	public static String FTP_CHARSET = "utf-8";
	/** FTP主机地址 **/
	public static String FTP_HOST = "";
	/** FTP端口号 **/
	public static int FTP_PORT = 0;
	/** FTP用户名 **/
	public static String FTP_USERNAME = "";
	/** FTP密码 **/
	public static String FTP_PASSWORD = "";
	/** FTP根目录 **/
	public static String FTP_PROJECT = "";
	/** FTP访问地址 **/
	public static String FTP_DEST_PATH = "";
	
	/** SFTP字符编码 **/
	public static String SFTP_CHARSET = "utf-8";
	/** SFTP主机地址 **/
	public static String SFTP_HOST = "";
	/** SFTP端口号 **/
	public static int SFTP_PORT = 0;
	/** SFTP用户名 **/
	public static String SFTP_USERNAME = "";
	/** SFTP密码 **/
	public static String SFTP_PASSWORD = "";
	/** SFTP根目录 **/
	public static String SFTP_PROJECT = "";
	/** SFTP访问地址 **/
	public static String SFTP_DEST_PATH = "";

	/** 二维码字符编码(默认utf-8) **/
	public static String QRCODE_CHARSET = "utf-8";
	/** 二维码图片格式(默认jpg) **/
	public static String QRCODE_FORMAT = "jpg";
	/** 二维码内容(请求路径) **/
	public static String QRCODE_CONTENT = "";
	/** 二维码尺寸(默认300) **/
	public static int QRCODE_SIZE = 300;
	/** 二维码LOGO宽度(默认100) **/
	public static int QRCODE_LOGO_WIDTH = 100;
	/** 二维码LOGO高度(默认100) **/
	public static int QRCODE_LOGO_HEIGHT = 100;
	/** 二维码LOGO路径 **/
	public static String QRCODE_LOGO_PATH = "";

	/** 支付结果异步通知（后台通知）地址 **/
	public static String PAY_RESULT_NOTIFY_URL = "";
	/** 支付结果同步通知（前台通知）地址 */
	public static String PAY_RESULT_RETURN_URL = "";
	/** 代付支付结算结果通知地址 */
	public static String PROXY_PAY_RESULT_NOTIFY_URL = "";

	/** 支付宝  商户Pid **/
	public static String PAY_ALI_P_ID = "";
	
	/** 支付宝 WAP 支付 appid **/
	public static String PAY_ALI_APP_ID = "";
	
	/** 支付宝 WAP 支付 **/
	public static String PAY_ALI_WAP_URL = "";

	/** 支付完成跳转地址 **/
	public static String PAY_COMPLETE_URL = "";

	/** 支付完成跳转地址 **/
	public static String PAY_RESULT_ORDER_QUERY = "";

	/** 对账文件存放目录 */
	public static String RECONCILIATION_FILE_DIR = "";

	/** 对账文件下载目录 */
	public static String RECONCILIATION_DOWNLOAD_DIR = "";
	
	/** 对账文件上传目录 */
	public static String RECONCILIATION_UPLOAD_DIR = "";

	/** jwt 私钥 */
	public static String JWT_RSA_PRIVATEKEY = "";

	/** jwt 公钥 */
	public static String JWT_RSA_PUBLICKEY = "";

	/** 阿里大鱼请求地址 */
	public static String ALIBABA_MSG_URL = "";
	/** 阿里大鱼key */
	public static String ALIBABA_APP_KEY = "";
	/** 阿里大鱼secret */
	public static String ALIBABA_APP_SECRET = "";
	/** 阿里大鱼template */
	public static String ALIBABA_MSG_TEMPLATE_ID = "";

	/** 阿里云短信 状态 */
	public static String ALIYUN_SMS_STATUS = "";
	/** 阿里云短信key */
	public static String ALIYUN_SMS_KEY = "";
	/** 阿里云短信secret */
	public static String ALIYUN_SMS_SECRET = "";
	/** 阿里云短信template */
	public static String ALIYUN_SMS_TEMPLATE_ID = "";
	/** 阿里云短信签名 */
	public static String ALIYUN_SMS_SIGNNAME = "";

	/** 系统环境 */
	public static String SYSTEM_CONDITIONS = "";

	/** 系统所属 */
	public static String SYSTEM_OWNER = "bigpay";
	
	/** Redis主节点的IP地址（主备模式） */
	public static String ID_GENERATOR_REDIS_MASTER_IP = "127.0.0.1";
	/** Redis主节点的端口 */
	public static int ID_GENERATOR_REDIS_MASTER_PORT = 6379;
	/** Redis主节点的请求密码 */
	public static String ID_GENERATOR_REDIS_MASTER_PASSWORD = "";
	/** Redis主节点的Lua脚本Sha码 */
	public static String ID_GENERATOR_REDIS_MASTER_LUASHA = "";

	/** Redis备用节点的IP地址（主备模式） */
	public static String ID_GENERATOR_REDIS_BACKUP_IP = "127.0.0.1";
	/** Redis备用节点的端口 */
	public static int ID_GENERATOR_REDIS_BACKUP_PORT = 6380;
	/** Redis备用节点的请求密码 */
	public static String ID_GENERATOR_REDIS_BACKUP_PASSWORD = "";
	/** Redis备用节点的Lua脚本Sha码 */
	public static String ID_GENERATOR_REDIS_BACKUP_LUASHA = "";

	public static String SYSTEM_JSP_STYLE_VERSION = "";
	
	
	/**
	 * 只加载一次.
	 */
	static {
		try {
			LOG.info("=== load system_config.properties and init sys param");
			InputStream proFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/system.properties");
			Properties props = new Properties();
			props.load(proFile);
			init(props);
		} catch (Exception e) {
			LOG.error("=== load and init system_config.properties exception:" + e);
		}
	}

	/**
	 * 根据配置文件初始化静态变量值.
	 * 
	 * @param props
	 */
	private static void init(Properties props) {
		String gateway_url = props.getProperty("gateway.url");
		if (StringUtils.isNotBlank(gateway_url)) {
			GATEWAY_URL = gateway_url;
		} else {
			throw new RuntimeException("支付网关请求地址为空");
		}

		String weixin_pay_app_id = props.getProperty("weixin.pay.app.id");
		if (StringUtils.isNotBlank(weixin_pay_app_id)) {
			WEIXIN_PAY_APP_ID = weixin_pay_app_id;
		} else {
			throw new RuntimeException("微信支付APPID为空");
		}

		String weixin_pay_app_secret = props.getProperty("weixin.pay.app.secret");
		if (StringUtils.isNotBlank(weixin_pay_app_secret)) {
			WEIXIN_PAY_APP_SECRET = weixin_pay_app_secret;
		} else {
			throw new RuntimeException("微信支付APP_SECRET为空");
		}

		String weixin_pay_redirect_url = props.getProperty("weixin.pay.redirect.url");
		if (StringUtils.isNotBlank(weixin_pay_redirect_url)) {
			WEIXIN_PAY_REDIRECT_URL = weixin_pay_redirect_url;
		} else {
			throw new RuntimeException("微信支付REDIRECT_URL为空");
		}

		String weixin_gzh_app_id = props.getProperty("weixin.gzh.app.id");
		if (StringUtils.isNotBlank(weixin_gzh_app_id)) {
			WEIXIN_GZH_APP_ID = weixin_gzh_app_id;
		} else {
			throw new RuntimeException("微信公众号APPID为空");
		}

		String weixin_gzh_app_secret = props.getProperty("weixin.gzh.app.secret");
		if (StringUtils.isNotBlank(weixin_gzh_app_secret)) {
			WEIXIN_GZH_APP_SECRET = weixin_gzh_app_secret;
		} else {
			throw new RuntimeException("微信公众号APP_SECRET为空");
		}

		String weixin_gzh_redirect_url = props.getProperty("weixin.gzh.redirect.url");
		if (StringUtils.isNotBlank(weixin_gzh_redirect_url)) {
			WEIXIN_GZH_REDIRECT_URL = weixin_gzh_redirect_url;
		} else {
			throw new RuntimeException("微信公众号REDIRECT_URL为空");
		}

		String weixin_gzh_templateId = props.getProperty("weixin.gzh.templateId");
		if (StringUtils.isNotBlank(weixin_gzh_templateId)) {
			WEIXIN_GZH_TEMPLATEID = weixin_gzh_templateId;
		} else {
			throw new RuntimeException("微信公众号推送模版id为空");
		}

		String ftp_charset = props.getProperty("ftp.charset");
		if (StringUtils.isNotBlank(ftp_charset)) {
			FTP_CHARSET = ftp_charset;
		} else {
			throw new RuntimeException("FTP字符编码为空");
		}

		String ftp_host = props.getProperty("ftp.host");
		if (StringUtils.isNotBlank(ftp_host)) {
			FTP_HOST = ftp_host;
		} else {
			throw new RuntimeException("FTP主机地址为空");
		}

		String ftp_port = props.getProperty("ftp.port");
		if (StringUtils.isNotBlank(ftp_port)) {
			FTP_PORT = Integer.valueOf(ftp_port).intValue();
		} else {
			throw new RuntimeException("FTP端口号为空");
		}

		String ftp_username = props.getProperty("ftp.username");
		if (StringUtils.isNotBlank(ftp_username)) {
			FTP_USERNAME = ftp_username;
		} else {
			throw new RuntimeException("FTP用户名为空");
		}

		String ftp_password = props.getProperty("ftp.password");
		if (StringUtils.isNotBlank(ftp_password)) {
			FTP_PASSWORD = ftp_password;
		} else {
			throw new RuntimeException("FTP密码为空");
		}

		String ftp_project = props.getProperty("ftp.project");
		if (StringUtils.isNotBlank(ftp_project)) {
			FTP_PROJECT = ftp_project;
		} else {
			throw new RuntimeException("FTP根目录为空");
		}

		String ftp_dest_path = props.getProperty("ftp.dest.path");
		if (StringUtils.isNotBlank(ftp_dest_path)) {
			FTP_DEST_PATH = ftp_dest_path;
		} else {
			throw new RuntimeException("FTP访问地址为空");
		}
		
		String sftp_charset = props.getProperty("sftp.charset");
		if (StringUtils.isNotBlank(sftp_charset)) {
			SFTP_CHARSET = sftp_charset;
		} else {
			throw new RuntimeException("SFTP字符编码为空");
		}

		String sftp_host = props.getProperty("sftp.host");
		if (StringUtils.isNotBlank(sftp_host)) {
			SFTP_HOST = sftp_host;
		} else {
			throw new RuntimeException("SFTP主机地址为空");
		}

		String sftp_port = props.getProperty("sftp.port");
		if (StringUtils.isNotBlank(sftp_port)) {
			SFTP_PORT = Integer.valueOf(sftp_port).intValue();
		} else {
			throw new RuntimeException("SFTP端口号为空");
		}

		String sftp_username = props.getProperty("sftp.username");
		if (StringUtils.isNotBlank(sftp_username)) {
			SFTP_USERNAME = sftp_username;
		} else {
			throw new RuntimeException("SFTP用户名为空");
		}

		String sftp_password = props.getProperty("sftp.password");
		if (StringUtils.isNotBlank(sftp_password)) {
			SFTP_PASSWORD = sftp_password;
		} else {
			throw new RuntimeException("SFTP密码为空");
		}

		String sftp_project = props.getProperty("sftp.project");
		if (StringUtils.isNotBlank(sftp_project)) {
			SFTP_PROJECT = sftp_project;
		} else {
			throw new RuntimeException("SFTP根目录为空");
		}

		String sftp_dest_path = props.getProperty("sftp.dest.path");
		if (StringUtils.isNotBlank(sftp_dest_path)) {
			SFTP_DEST_PATH = sftp_dest_path;
		} else {
			throw new RuntimeException("SFTP访问地址为空");
		}
		

		String qrcode_charset = props.getProperty("qrcode.charset");
		if (StringUtils.isNotBlank(qrcode_charset)) {
			QRCODE_CHARSET = qrcode_charset;
		} else {
			throw new RuntimeException("二维码字符编码为空");
		}

		String qrcode_format = props.getProperty("qrcode.format");
		if (StringUtils.isNotBlank(qrcode_format)) {
			QRCODE_FORMAT = qrcode_format;
		} else {
			throw new RuntimeException("二维码图片格式为空");
		}

		String qrcode_content = props.getProperty("qrcode.content");
		if (StringUtils.isNotBlank(qrcode_content)) {
			QRCODE_CONTENT = qrcode_content;
		} else {
			throw new RuntimeException("二维码内容(请求路径)为空");
		}

		String qrcode_size = props.getProperty("qrcode.size");
		if (StringUtils.isNotBlank(qrcode_size)) {
			QRCODE_SIZE = Integer.valueOf(qrcode_size).intValue();
		} else {
			throw new RuntimeException("二维码尺寸为空");
		}

		String qrcode_logo_width = props.getProperty("qrcode.logo.width");
		if (StringUtils.isNotBlank(qrcode_logo_width)) {
			QRCODE_LOGO_WIDTH = Integer.valueOf(qrcode_logo_width).intValue();
		} else {
			throw new RuntimeException("二维码LOGO宽度为空");
		}

		String qrcode_logo_height = props.getProperty("qrcode.logo.height");
		if (StringUtils.isNotBlank(qrcode_logo_height)) {
			QRCODE_LOGO_HEIGHT = Integer.valueOf(qrcode_logo_height).intValue();
		} else {
			throw new RuntimeException("二维码LOGO高度为空");
		}

		String qrcode_logo_path = props.getProperty("qrcode.logo.path");
		if (StringUtils.isNotBlank(qrcode_logo_path)) {
			QRCODE_LOGO_PATH = qrcode_logo_path;
		} else {
			throw new RuntimeException("二维码内容(请求路径)为空");
		}

		String pay_result_notify_url = props.getProperty("pay.result.notify.url");
		if (StringUtils.isNotBlank(pay_result_notify_url)) {
			PAY_RESULT_NOTIFY_URL = pay_result_notify_url;
		} else {
			throw new RuntimeException("支付结果异步通知（后台通知）地址为空");
		}

		String pay_ali_p_id = props.getProperty("pay.ali.p.id");
		if (StringUtils.isNotBlank(pay_ali_p_id)) {
			PAY_ALI_P_ID = pay_ali_p_id;
		} else {
			throw new RuntimeException("支付宝商户PID为空");
		}
		
		String pay_ali_app_id = props.getProperty("pay.ali.app.id");
		if (StringUtils.isNotBlank(pay_ali_app_id)) {
			PAY_ALI_APP_ID = pay_ali_app_id;
		} else {
			throw new RuntimeException("支付宝 wap支付appid为空");
		}
		
		String pay_ali_wap_url = props.getProperty("pay.ali.wap.url");
		if (StringUtils.isNotBlank(pay_ali_wap_url)) {
			PAY_ALI_WAP_URL = pay_ali_wap_url;
		} else {
			throw new RuntimeException("支付宝 wap支付请求地址为空");
		}

		String pay_complete_url = props.getProperty("pay.complete.url");
		if (StringUtils.isNotBlank(pay_complete_url)) {
			PAY_COMPLETE_URL = pay_complete_url;
		} else {
			throw new RuntimeException("支付完成跳转地址为空");
		}

		String pay_result_order_query = props.getProperty("pay.result.order.query");
		if (StringUtils.isNotBlank(pay_result_order_query)) {
			PAY_RESULT_ORDER_QUERY = pay_result_order_query;
		} else {
			throw new RuntimeException("支付结果查询地址为空");
		}

		String pay_result_return_url = props.getProperty("pay.result.return.url");
		LOG.info("设置页面通知地址:{}" + pay_result_return_url);
		if (StringUtils.isNotBlank(pay_result_return_url)) {
			PAY_RESULT_RETURN_URL = pay_result_return_url;
		} else {
			throw new RuntimeException("支付结果同步通知（前台通知）地址为空");
		}

		String proxy_pay_result_notify_url = props.getProperty("proxy.pay.result.notify.url");
		if (StringUtils.isNotBlank(proxy_pay_result_notify_url)) {
			PROXY_PAY_RESULT_NOTIFY_URL = proxy_pay_result_notify_url;
		} else {
			throw new RuntimeException("代付支付结算结果通知地址为空");
		}

		String reconciliation_file_dir = props.getProperty("reconciliation.file.dir");
		if (StringUtils.isNotBlank(reconciliation_file_dir)) {
			RECONCILIATION_FILE_DIR = reconciliation_file_dir;
		} else {
			throw new RuntimeException("对账文件存放目录为空");
		}

		String reconciliation_download_dir = props.getProperty("reconciliation.download.dir");
		if (StringUtils.isNotBlank(reconciliation_download_dir)) {
			RECONCILIATION_DOWNLOAD_DIR = reconciliation_download_dir;
		} else {
			throw new RuntimeException("对账文件下载目录为空");
		}
		
		String reconciliation_upload_dir = props.getProperty("reconciliation.upload.dir");
		if (StringUtils.isNotBlank(reconciliation_download_dir)) {
			RECONCILIATION_UPLOAD_DIR = reconciliation_upload_dir;
		} else {
			throw new RuntimeException("对账文件上传目录为空");
		}

		String jwt_rsa_privateKey = props.getProperty("jwt.rsa.privateKey");
		if (StringUtils.isNotBlank(jwt_rsa_privateKey)) {
			JWT_RSA_PRIVATEKEY = jwt_rsa_privateKey;
		} else {
			throw new RuntimeException("JWT私钥为空");
		}

		String jwt_rsa_publicKey = props.getProperty("jwt.rsa.publicKey");
		if (StringUtils.isNotBlank(jwt_rsa_publicKey)) {
			JWT_RSA_PUBLICKEY = jwt_rsa_publicKey;
		} else {
			throw new RuntimeException("JWT公钥为空");
		}

		String alibaba_msg_url = props.getProperty("alibaba.msg.url");
		if (StringUtils.isNotBlank(alibaba_msg_url)) {
			ALIBABA_MSG_URL = alibaba_msg_url;
		} else {
			throw new RuntimeException("阿里大鱼请求地址为空");
		}

		String alibaba_app_key = props.getProperty("alibaba.app.key");
		if (StringUtils.isNotBlank(alibaba_app_key)) {
			ALIBABA_APP_KEY = alibaba_app_key;
		} else {
			throw new RuntimeException("阿里大鱼KEY为空");
		}

		String alibaba_app_secret = props.getProperty("alibaba.app.secret");
		if (StringUtils.isNotBlank(alibaba_app_secret)) {
			ALIBABA_APP_SECRET = alibaba_app_secret;
		} else {
			throw new RuntimeException("阿里大鱼SECRET为空");
		}

		String alibaba_msg_template_id = props.getProperty("alibaba.msg.template.id");
		if (StringUtils.isNotBlank(alibaba_msg_template_id)) {
			ALIBABA_MSG_TEMPLATE_ID = alibaba_msg_template_id;
		} else {
			throw new RuntimeException("阿里大鱼TEMPLATE_ID为空");
		}

		String aliyun_sms_status = props.getProperty("aliyun.sms.status");
		if (StringUtils.isNotBlank(aliyun_sms_status)) {
			ALIYUN_SMS_STATUS = aliyun_sms_status;
		} else {
			throw new RuntimeException("阿里云短信状态为空");
		}

		String aliyun_sms_key = props.getProperty("aliyun.sms.key");
		if (StringUtils.isNotBlank(aliyun_sms_key)) {
			ALIYUN_SMS_KEY = aliyun_sms_key;
		} else {
			throw new RuntimeException("阿里云短信KEY为空");
		}

		String aliyun_sms_secret = props.getProperty("aliyun.sms.secret");
		if (StringUtils.isNotBlank(aliyun_sms_secret)) {
			ALIYUN_SMS_SECRET = aliyun_sms_secret;
		} else {
			throw new RuntimeException("阿里云短信SECRET为空");
		}

		String aliyun_sms_template_id = props.getProperty("aliyun.sms.template.id");
		if (StringUtils.isNotBlank(aliyun_sms_template_id)) {
			ALIYUN_SMS_TEMPLATE_ID = aliyun_sms_template_id;
		} else {
			throw new RuntimeException("阿里云短信TEMPLATE_ID为空");
		}

		String aliyun_sms_signname;
		try {
			aliyun_sms_signname = new String(props.getProperty("aliyun.sms.signname").getBytes("ISO-8859-1"), "UTF-8");
			if (StringUtils.isNotBlank(aliyun_sms_signname)) {
				ALIYUN_SMS_SIGNNAME = aliyun_sms_signname;
			} else {
				throw new RuntimeException("阿里云签名为空");
			}
		} catch (UnsupportedEncodingException e) {
			LOG.info("获取阿里云签名异常", e);
			throw new RuntimeException("阿里云签名为空");
		}
		;

		String system_conditions = props.getProperty("system.conditions");
		if (StringUtils.isNotBlank(system_conditions)) {
			SYSTEM_CONDITIONS = system_conditions;
		} else {
			throw new RuntimeException("系统环境配置为空");
		}

		String system_owner = props.getProperty("system.owner");
		if (StringUtils.isNotBlank(system_owner)) {
			SYSTEM_OWNER = system_owner;
		} else {
			throw new RuntimeException("系统所属配置为空");
		}
		
		String system_jsp_style_version = props.getProperty("system.jsp.style.version");
		if (StringUtils.isNotBlank(system_jsp_style_version)) {
			SYSTEM_JSP_STYLE_VERSION = system_jsp_style_version;
		} else {
			throw new RuntimeException("系统所属配置为空");
		}
		
		
		
		
		// Redis主节点的IP地址（主备模式）
		String id_generator_redis_master_ip = props.getProperty("id.generator.redis.master.ip");
		if (StringUtils.isNotBlank(id_generator_redis_master_ip)) {
			ID_GENERATOR_REDIS_MASTER_IP = id_generator_redis_master_ip;
		} else {
			throw new RuntimeException("系统所属配置为空");
		}
		// Redis主节点的端口
		String id_generator_redis_master_port = props.getProperty("id.generator.redis.master.port");
		if (StringUtils.isNotBlank(id_generator_redis_master_port)) {
			ID_GENERATOR_REDIS_MASTER_PORT = Integer.valueOf(id_generator_redis_master_port.trim()).intValue();
		} else {
			throw new RuntimeException("系统所属配置为空");
		}
		// Redis主节点的请求密码
		String id_generator_redis_master_password = props.getProperty("id.generator.redis.master.password");
		if (StringUtils.isNotBlank(id_generator_redis_master_password)) {
			ID_GENERATOR_REDIS_MASTER_PASSWORD = id_generator_redis_master_password;
		} else {
			throw new RuntimeException("系统所属配置为空");
		}
		// Redis主节点的Lua脚本Sha码
		String id_generator_redis_master_luasha = props.getProperty("id.generator.redis.master.luasha");
		if (StringUtils.isNotBlank(id_generator_redis_master_luasha)) {
			ID_GENERATOR_REDIS_MASTER_LUASHA = id_generator_redis_master_luasha;
		} else {
			throw new RuntimeException("系统所属配置为空");
		}
		
		// Redis备用节点的IP地址（主备模式）
		String id_generator_redis_backup_ip = props.getProperty("id.generator.redis.backup.ip");
		if (StringUtils.isNotBlank(id_generator_redis_backup_ip)) {
			ID_GENERATOR_REDIS_BACKUP_IP = id_generator_redis_backup_ip;
		} else {
			throw new RuntimeException("系统所属配置为空");
		}
		// Redis备用节点的端口
		String id_generator_redis_backup_port = props.getProperty("id.generator.redis.backup.port");
		if (StringUtils.isNotBlank(id_generator_redis_backup_port)) {
			ID_GENERATOR_REDIS_BACKUP_PORT = Integer.valueOf(id_generator_redis_backup_port.trim()).intValue();
		} else {
			throw new RuntimeException("系统所属配置为空");
		}
		// Redis备用节点的请求密码
		String id_generator_redis_backup_password = props.getProperty("id.generator.redis.backup.password");
		if (StringUtils.isNotBlank(id_generator_redis_backup_password)) {
			ID_GENERATOR_REDIS_BACKUP_PASSWORD = id_generator_redis_backup_password;
		} else {
			throw new RuntimeException("系统所属配置为空");
		}
		// Redis备用节点的Lua脚本Sha码
		String id_generator_redis_backup_luasha = props.getProperty("id.generator.redis.backup.luasha");
		if (StringUtils.isNotBlank(id_generator_redis_backup_luasha)) {
			ID_GENERATOR_REDIS_BACKUP_LUASHA = id_generator_redis_backup_luasha;
		} else {
			throw new RuntimeException("系统所属配置为空");
		}
		
		
	}

}
