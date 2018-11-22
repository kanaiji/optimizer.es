package web.application.com.common.uitls;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Http 工具
 */
public final class HttpUtils {

	private static final String CHARSET_UTF_8 = "UTF-8";

	public static String get(String url) {
		System.out.println(url.trim());
		HttpGet httpGet = new HttpGet(url.trim());
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(5000).build();
		httpGet.setConfig(requestConfig);
		try {
			HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpGet);
			return EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF_8);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static String post(String url, String param) {
		System.out.println(url.trim());
		System.out.println(param);
		try {
			HttpPost httpPost = new HttpPost(url.trim());
			 
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
			 httpPost.setConfig(requestConfig);
			StringEntity se = new StringEntity(param.toString(), CHARSET_UTF_8);
			httpPost.setEntity(se);
			System.out.println("请求路径:"+httpPost.getURI());
			System.out.println("请求方法:"+httpPost.getMethod());
			System.out.println("请求头:"+httpPost.getHeaders("Content-Type").toString());
			System.out.println("请求头:"+httpPost.getRequestLine());
			HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpPost);
			return EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	public static String postWithForm(String url, String param) {
		System.out.println(url.trim());
		System.out.println(param);
		try {
			HttpPost httpPost = new HttpPost(url.trim());
			 
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			 httpPost.setConfig(requestConfig);
			StringEntity se = new StringEntity(param.toString(), CHARSET_UTF_8);
			httpPost.setEntity(se);
			System.out.println("请求路径:"+httpPost.getURI());
			System.out.println("请求方法:"+httpPost.getMethod());
			System.out.println("请求头:"+httpPost.getHeaders("Content-Type").toString());
			System.out.println("请求头:"+httpPost.getRequestLine());
			HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpPost);
			return EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) {
		System.out.println(get("http://real.izhongyin.com/middlepaytrx/wx/scanCode?amount=0.02&idCardNo=vv452F0Upvlw6q8fIoEFn7Kcj3gHrNzW&toibkn=105&sign=5c93fd1e4046c67837eef7c869a58ea2&orderNum=DEV66662016121311054601&trxType=WX_SCANCODE&cardNo=t7h/WEFGN82ZRl9mU1mtRpZxdyN57jKA&timeOut=20161214070450&serverCallbackUrl=http://www.xxx.com/bigpay-web-gateway/cnpPayNotify/notify/WEIXIN_SCANPAY_KAMENG_T0&encrypt=T0&orderIp=192.168.1.13&payerName=1pqIIRevIaWxW3DhH2VMFw==&callbackUrl=http://www.xxx.com&goodsName=%E6%B0%B4%E6%9D%AF&merchantNo=B100001269"));
	}

}
