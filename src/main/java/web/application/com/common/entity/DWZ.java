package web.application.com.common.entity;

/**
 * 封装DWZ框架ajax请求及响应的参数. 
 * 
 */
public final class DWZ {

	/**
	 * Ajax请求成功,statusCode 为200.
	 */
	public static final String SUCCESS = "200";
	/**
	 * Ajax请求失败,statusCode 为300.
	 */
	public static final String ERROR = "300";
	/**
	 * Ajax请求超时,statusCode 为301.
	 */
	public static final String TIMEOUT = "301";

	/**
	 * callbackType ajax请求回调类型. <br/>
	 * callbackType如果是closeCurrent就会关闭当前tab选项,
	 */
	public static final String CLOSE = "closeCurrent";

	/**
	 * 只有callbackType="forward"时需要forwardUrl值,以重定向到另一个URL.
	 */
	public static final String FORWARD = "forward";
	
	public static final String AJAX_DONE = "common/ajaxDone";
	
	public static final String SUCCESS_MSG = "操作成功";

	/**
	 * 私有构造方法,单例模式.
	 */
	private DWZ() {

	}
}
