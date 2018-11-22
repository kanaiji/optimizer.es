package web.application.com.common.uitls;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 百度地图坐标和火星坐标转换 Created by 明明如月 on 2017-03-22.
 */
public class CoodinateCovertor {

	/*
	 * GCJ-02(火星坐标) 和 BD-09 （百度坐标） 9 * 算法代码如下，其中 bd_encrypt 将 GCJ-02 坐标转换成 BD-09
	 * 坐标， bd_decrypt 反之。 10
	 */
	static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

	public static void main(String[] args) {
		// --高德 119.254356 26.080030
		// --百度 119.260989 26.086051

		// bdEncrypt(116.408343,39.884298);
		bd_decrypt(116.408343, 39.884298);
		// 119.2608552145561
		// 26.086114589896383
	}

	// 高德转百度
	public static Map<String, Float> bdEncrypt(double gg_lat, double gg_lon) {
		Map<String, Float> data = new HashMap<String, Float>();
		double x = gg_lon, y = gg_lat;
		double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
		double bd_lon = z * Math.cos(theta) + 0.0065;
		double bd_lat = z * Math.sin(theta) + 0.006;
		data.put("lon", new BigDecimal(String.valueOf(bd_lon)).floatValue());
		data.put("lat", new BigDecimal(String.valueOf(bd_lat)).floatValue());
		return data;
	}

	// 百度转高德
	public static Map<String, Double> bd_decrypt(double bd_lat, double bd_lon) {
		double x = bd_lon - 0.0065, y = bd_lat - 0.006;
		double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
		double gd_lon = z * Math.cos(theta);
		double gd_lat = z * Math.sin(theta);
		Map<String, Double> data = new HashMap<String, Double>();
		data.put("lon", gd_lon);
		data.put("lat", gd_lat);
		return data;
	}
}