package web.application.com.common.constans;

/**
 * 基本常量
 */
public class CommonConst {

	

	/**
	 * ES index type
	 */
	public static final String ES_INDEX_TYPE_JSON = "json";
	
	
	/**
	 * kafka topic name
	 */
	public final static String TOPIC_BUDGET_DELTA = "dev.delta.sctid.ibm_forecast_budget_delta";
	public final static String TOPIC_ROADMAP_DELTA = "dev.delta.sctid.ibm_forecast_roadmap_delta";
	

	/**
	 * elasticsearch connection info 
	 */
	public final static String IP = "9.37.248.224";
	public final static int PORT = 9300;
	
	/**
	 * elasticsearch index name
	 * 一个队列一个index
	 */
    public final static String ES_INDEX_BUDGET_DELTA = "dev.delta.sctid.ibm_forecast_budget_delta";
	public final static String ES_INDEX_ROADMAP_DELTA = "dev.delta.sctid.ibm_forecast_roadmap_delta";
	
	
	/**
	 * 邮件配置
	 */
	public static final String Mail_Server_Host = "smtp.163.com" ; //"smtp.mxhichina.com"; //邮件服务器地址
	public static final String Mail_Server_Port ="25";             //邮件服务器端口
	public static final String Mail_UserName =  "13998641323"   ;//"agui@dengzixu.com";   //邮件用户名
	public static final String Mail_Password = "1216guikeer" ;  // "Agui123456789";   //邮件密码
	public static final String Mail_FromAddress ="13998641323@163.com" ; // "agui@dengzixu.com";   //发送邮件地址
	public static final String Mail_Subject_Success ="审核通过";   //邮件标题成功
	public static final String Mail_Subject_Fail="审核未通过";   //邮件标题失败
	public static final String Mail_Contents_Enterprise="账号审核通过,请登录!<p>http://www.hroop.com/views/index/HR.jsp</p>";   //邮件内容
	public static final String Mail_Contents_Enterprise_DENY="账号审核失败,请重新注册!<p>http://www.hroop.com/views/index/hrRegister.jsp</p>";   //邮件内容
	public static final String Mail_Contents_School="学校审核通过账号，密码";   //邮件内容

	
	
	

}
