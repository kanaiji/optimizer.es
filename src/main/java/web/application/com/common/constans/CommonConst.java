package web.application.com.common.constans;

import web.application.com.common.uitls.DateUtils;

/**
 * 基本常量
 *
 *
 */
public class CommonConst {

	/**
	 * 当前用户
	 */
	public static final String CURRENT_USER = "currentUser";
	
	
	/**
	 * 短信验证码
	 */
	public static final String SMS_CODE = "smsCode";
	
	
	/**
	 * 异常页面
	 */
	public static final String ERROR_PAGE = "error/500";
	
	
	/**
	 * 用户状态
	 */
	public static final String USER_ACTIVE   = "active" ;
	public static final String USER_NOACTIVE  = "noactive" ;
	
	
	
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

	
	
	/**
	 * 数据搜索条件
	 */
	public static final String SEARCH_DATA_CONDITION_HOSPITAL_TYPE = "hospitalType";
	public static final String SEARCH_DATA_CONDITION_HOSPITAL_NAME = "hospitalName";
	public static final String SEARCH_DATA_CONDITION_HOSPITAL_ID = "id";
	public static final String SEARCH_DATA_CONDITION_START_TIME = "startTime";
	public static final String SEARCH_DATA_CONDITION_END_TIME = "endTime";
	public static final String SEARCH_DATA_CONDITION_SCOPE = "scope";
	public static final String SEARCH_DATA_CONDITION_DETECTIONRESULT = "detectionResult";
	
	
	/**
	 * 全国，省，市，区 级别
	 */
	public static final String SCOPE_LEVEL_COUNTRY = "00";
	public static final String SCOPE_LEVEL_PROVINCE = "01";
	public static final String SCOPE_LEVEL_CITY = "02";
	public static final String SCOPE_LEVEL_AREA = "03";
	
	
	/**
	 * dtb pdf 文件路径
	 */
	public static final String DATA_RESOURCES_ = "/data/resources/";
	
	
	/**
	 * 本地文件路径
	 */
	public static final String LOCAL_FILE_PATH_DOWN_OSS_FILE = "d:/" + DateUtils.getReqDate() ;
	public static final String LOCAL_FILE_PATH_STERILIZATIONS = "d:/" + DateUtils.getReqDate() ;
	
	
	
	/**
	 * data tableName
	 */
	public static final String DATA_TABLE_NAME_STERILIZATION = "wj_sterilization_";
	public static final String DATA_TABLE_NAME_PUSH = "wj_push_";
	
	
	/**
	 * 数据处理（map, data, push, statistics）状态类别
	 * data status
	 */
	public static final String DATA_STATUS_CREATE = "CREATE";
	public static final String DATA_STATUS_SUCCESS = "SUCCESS";
	public static final String DATA_STATUS_FAIL = "FAIL";
	
	
	/**
	 * 地图画色
	 */
	public static final String COLOR_TYPE_GRAY = "#bbb9b7"  ;            // 灰色
	public static final String COLOR_TYPE_RED = "#ff0000"  ;             // 红色
	public static final String COLOR_TYPE_ORANGE = "#ff6700"  ;          // 橙色
	public static final String COLOR_TYPE_YELLOW = "#ffd800"  ;          // 黄色
	public static final String COLOR_TYPE_GREEN = "#66ff00"  ;           // 浅绿
	public static final String COLOR_TYPE_DARK_GREEN = "#018824"  ;      // 深绿
	
	
	/**
	 * 表名基本信息
	 */
	public static final String WJ_PUSH = "wj_push_" ;                          // 医院诊所
	public static final String WJ_STERILIZATION  = "wj_sterilization_" ;       // 灭菌效果包
	
	/**
	 * 医院推送开关 ： 1 打开 ， 2关闭
	 */
	public static final String PUSH_OPEN   = "1" ;
	public static final String PUSH_CLOSE  = "2" ;
	
	
	/**
	 *消息推送 ： 1 已读 ， 2未读
	 */
	public static final String IS_READ  = "1" ;
	public static final String NO_READ  = "2" ;
	
	
	/**
	 *消息推送  Title
	 */
	public static final String TITLE_UP_DATA_PUSH  = "数据上传推送" ;
	public static final String TITLE_DATA_EXPIRE_PUSH  = "数据到期提醒" ;
	
	/**
	 * DataSource 
	 * opsSqlSessionTemplate 老系统
	 * devSqlSessionTemplate 新系统
	 */
	public static final String DATASOURCE_DEV  = "devSqlSessionTemplate" ;  
	public static final String DATASOURCE_OPS  = "opsSqlSessionTemplate" ;

	
	

}
