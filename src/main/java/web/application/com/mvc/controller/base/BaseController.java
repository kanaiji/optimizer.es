package web.application.com.mvc.controller.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.ByteArrayBuffer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import web.application.com.common.uitls.StringUtil;



/**
 * Controller基础类
 */
public abstract class BaseController {

	private static final Log log = LogFactory.getLog(BaseController.class);

	protected static final String RESPONSE_CODE = "code";
	protected static final String RESPONSE_MESSAGE = "msg";
	protected static final String RESPONSE_ERROR = "error";
	public static final String OPERATOR_SESSION_KEY = "session_operator";
	/**
	 * 需要超级管理权限才能操作的提示信息.
	 */
	public static final String CHECK_SUPER_ADMIN_MSG = "执行此操作需要超级管理员权限";


	/**
	 * 获取request
	 * 
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 获取session
	 * 
	 * @return
	 */
	protected HttpSession getSession() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}

	/**
	 * 获取application
	 * 
	 * @return
	 */
	protected ServletContext getApplication() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getServletContext();
	}

	/**
	 * 获取客户端的IP地址
	 * 
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					log.error("未知主机", e);
				}
				ipAddress = inet.getHostAddress();
			}

		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) {
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	public int getInt(String name) {
		return getInt(name, 0);
	}

	public int getInt(String name, int defaultValue) {
		String resultStr = getRequest().getParameter(name);
		if (StringUtil.isEmpty(resultStr)) {
			try {
				return Integer.parseInt(resultStr);
			} catch (Exception e) {
				log.error("参数转换错误:", e);
				return defaultValue;
			}
		}
		return defaultValue;
	}

	public String getString(String name) {
		return getString(name, null);
	}

	public String getString(String name, String defaultValue) {
		String resultStr = getRequest().getParameter(name);
		if (resultStr == null || "".equals(resultStr) || "null".equals(resultStr) || "undefined".equals(resultStr)) {
			return defaultValue;
		} else {
			return resultStr;
		}
	}

	/**
	 * 根据参数名从HttpRequest中获取Long类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return LongValue or null .
	 */
	public Long getLong(String key) {
		String value = getRequest().getParameter(key);
		if (StringUtil.isNotNull(value)) {
			return Long.parseLong(value);
		}
		return null;
	}

	
	private static ThreadLocal<Map<String, Object>> outPutMsg = new ThreadLocal<Map<String, Object>>();

	/**
	 * 线程绑定，其内容会在outPrint方法调用后清空
	 * 
	 * @return the outputMsg
	 */
	public Map<String, Object> getOutputMsg() {
		Map<String, Object> output = outPutMsg.get();
		if (output == null) {
			output = new HashMap<String, Object>();
			outPutMsg.set(output);
		}
		return output;
	}

	/**
	 * 输出，同时清空outPutMsg
	 * 
	 * @param response
	 * @param result
	 */
	public void outPrint(HttpServletResponse response, Object result) {
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			out.print(result.toString());
			getOutputMsg().clear();
		} catch (IOException e) {
		} finally {
			out.close();
		}
	}
	
	
	/**
	 * 将请求参数封装成一个Map
	 * 
	 * @return
	 */
	public Map<String, Object> getParameterMap() {

		// 参数Map
		Map<String, String[]>  properties = getRequest().getParameterMap();
		// 返回值Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Iterator<Entry<String, String[]>> entries = properties.entrySet().iterator();
		Map.Entry<String, String[]> entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry<String, String[]>) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}

	/**
	 * 将请求参数封装成一个Map_中文使用gbk格式转换
	 * 
	 * @return
	 */
	public Map<String, Object> getParameterMap_GBK() {

		// 参数Map
		Map<String, String[]> properties = getRequest().getParameterMap();
		// 返回值Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Iterator<Entry<String, String[]>> entries = properties.entrySet().iterator();
		Map.Entry<String, String[]> entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry<String, String[]>) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
				try {
					byte[] fullByte = new String(value.getBytes("ISO-8859-1"), "UTF-8").getBytes("GBK");
					value = new String(fullByte, "GBK");
				} catch (UnsupportedEncodingException e) {
				}

			} else {
				value = valueObj.toString();
				try {
					byte[] fullByte = new String(value.getBytes("ISO-8859-1"), "UTF-8").getBytes("GBK");
					value = new String(fullByte, "GBK");
				} catch (UnsupportedEncodingException e) {
				}
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}

	/**
	 * 将请求参数封装成一个Map_中文使用gbk格式转换
	 *
	 * @return
	 */
	public Map<String, Object> getGBKParameterMap() {

		HttpServletRequest httpServletRequest = getRequest();
		try {
			httpServletRequest.setCharacterEncoding("GBK");
		} catch (UnsupportedEncodingException e) {
			return null;
		}

		// 参数Map
		Map<String, String[]> properties = httpServletRequest.getParameterMap();
		// 返回值Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Iterator<Entry<String, String[]>> entries = properties.entrySet().iterator();
		Map.Entry<String, String[]> entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry<String, String[]>) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
				try {
					byte[] fullByte = value.getBytes("GBK");
					value = new String(fullByte, "GBK");
				} catch (UnsupportedEncodingException e) {
				}

			} else {
				value = valueObj.toString();
				try {
					byte[] fullByte = value.getBytes("GBK");
					value = new String(fullByte, "GBK");
				} catch (UnsupportedEncodingException e) {
				}
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}
	
	
	public String getReuquestHeadToString(){
		//Header部分
		HttpServletRequest request = getRequest();
	    System.out.print(request.getHeaderNames());
	    log.info("getHeaderNames:"+request.getHeaderNames());
	    Enumeration<?> enum1 = request.getHeaderNames();
	    while (enum1.hasMoreElements()) {
	        String key = (String) enum1.nextElement();
	        String value = request.getHeader(key);
	        System.out.println(key + "\t" + value);
	        log.info(key + "\t" + value);
	    }
	    return "";
	}
	
	
	public String getRequestInputStreamToString() {
		String param = null;
		try {
			HttpServletRequest res = getRequest();
			ServletInputStream in = res.getInputStream();
			param = readStringFromInputStream(in);
		} catch (IOException e) {
			log.error("get fast pay inputsteam exception :", e);
		}
		return param;
	}

	private String readStringFromInputStream(InputStream is) throws IOException {
		byte[] buf = new byte[4096];
		int len = 0;
		ByteArrayBuffer bytes = new ByteArrayBuffer(4096);

		while (true) {
			len = is.read(buf);
			if (len >= 0) {
				bytes.append(buf, 0, len);
			} else {
				break;
			}
		}
		return new String(bytes.toByteArray(), "UTF-8");
	}
	
	
	
	
	
	
	
}
