package web.application.com.common.constans;

import web.application.com.common.config.SystemConfig;

/**
 * 
 * 各种编号的前缀（头）.
 * 
 * @author .
 *
 */
public class NoPrefixConstant {

	/** 商户订单编号前缀 **/
	public static final String MERCHANT_ORDER_NO_PREFIX = SystemConfig.SYSTEM_CONDITIONS + "1111";

	/** 对账批次号前缀 **/
	public static final String RECONCILIATION_BATCH_NO = SystemConfig.SYSTEM_CONDITIONS + "5555";

	/** 银行订单号 **/
	public static final String BANK_ORDER_NO_PREFIX = SystemConfig.SYSTEM_CONDITIONS + "";

	/** 支付流水号前缀 **/
	public static final String TRX_NO_PREFIX = SystemConfig.SYSTEM_CONDITIONS + "7777";

	/** 用户编号前缀 **/
	public static final String USER_NO_PREFIX = SystemConfig.SYSTEM_CONDITIONS + "8888";

	/** 账户编号前缀 **/
	public static final String ACCOUNT_NO_PREFIX = SystemConfig.SYSTEM_CONDITIONS + "9999";

	/** 结算请求号前缀 **/
	public static final String REMIT_REQUEST_NO_PREFIX = "";

}
