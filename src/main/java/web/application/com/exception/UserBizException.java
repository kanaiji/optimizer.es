package web.application.com.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * 用户业务异常类 
 */
@SuppressWarnings("serial")
public class UserBizException extends BizException {

	/** 用户不存在 **/
	public static final int USER_IS_NULL = 101;

	/** 用户支付配置有误 **/
	public static final int USER_PAY_CONFIG_ERROR = 102;

	/** 用户银行卡账户异常 **/
	public static final int USER_BANK_ACCOUNT_ERROR = 103;

	/** 用户银行附属信息异常 **/
	public static final int USER_BANK_WAY_ERROR = 104;

	/** 用户状态异常 **/
	public static final int USER_STATUS_ERROR = 105;

	public static final UserBizException USER_BANK_ACCOUNT_IS_NULL = new UserBizException(10010002, "用户未设置银行账户信息!");

	public static final UserBizException USER_MOBILE_IS_EXIST = new UserBizException(10010003, "用户手机号已存在！");

	public static final UserBizException USER_LOGIN_ERROR = new UserBizException(10010004, "用户手机号/密码错误");

	public static final UserBizException RECOMMEND_USER_IS_NULL = new UserBizException(10010005, "推荐人为空！");

	public static final UserBizException USER_MOBILE_CODE_ERROR = new UserBizException(10010006, "验证码错误！");

	public static final UserBizException USER_RATE_IS_NULL = new UserBizException(10010007, "用户费率为空！");

	public static final UserBizException USER_TYPE_ERROR = new UserBizException(10010008, "用户类型异常！");

	public static final UserBizException USER_BANK_WAY_SUBMER_IS_REPEAT = new UserBizException(10010009, "用户银行附属信息，子商户编号重复分配");

	private static final Log LOG = LogFactory.getLog(UserBizException.class);

	public UserBizException() {
	}

	public UserBizException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}

	public UserBizException(int code, String msg) {
		super(code, msg);
	}

	public UserBizException print() {
		LOG.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
		return this;
	}
}
