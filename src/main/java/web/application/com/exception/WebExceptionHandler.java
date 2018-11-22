package web.application.com.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import web.application.com.common.entity.ApiCommonResultVo;




/**
 * @描述: Spring异常拦截器.
 * @版本: 1.0 .
 */
@ControllerAdvice
public class WebExceptionHandler {

	private static final Log log = LogFactory.getLog(WebExceptionHandler.class);

	/**
	 * 没有权限 异常
	 * <p/>
	 * 后续根据不同的需求定制即可
	 */
	@ExceptionHandler({ BizException.class })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ApiCommonResultVo processBizException(HttpServletRequest request, BizException e) {
		ApiCommonResultVo resultVo = new ApiCommonResultVo(-2, e.getMessage(), "");
		log.error("BizException", e);
		return resultVo;
	}

	/**
	 * 总异常
	 */
	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ApiCommonResultVo processException(Exception e, HttpServletRequest request) {
		log.error("Exception", e);
		ApiCommonResultVo resultVo = new ApiCommonResultVo(-2, "操作异常", "");
		return resultVo;
	}

}
