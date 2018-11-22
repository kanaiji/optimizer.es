package web.application.com.common.uitls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Key {

	public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String NUMBERCHAR = "0123456789";

	public static  String  getKey24() {

		// 时间戳
		String timeStr = Long.toString(System.currentTimeMillis());
		System.out.println("时间戳 ：" + timeStr);
		//三位随机数
		Random r = new Random();
		String number3 = Integer.toString(r.nextInt(900)+100) ;
		// 8 位大写字母+数字 随机数
		String suiji = generateUpperString(8);
		
		String result = suiji + number3 + timeStr ;

		System.out.println("拼接之后的结果：" + result);
		String resultEnd = shuffleForSortingString(result);

		System.out.println("24 位Key  :" + resultEnd);
		
		return resultEnd ;
	}

	/**
	 * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateUpperString(int length) {
		return generateMixString(length).toUpperCase();
	}

	/**
	 * 返回一个定长的随机纯字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateMixString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(ALLCHAR.charAt(random.nextInt(LETTERCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * 打乱顺序
	 * 
	 * @param s
	 * @return
	 */
	public static String shuffleForSortingString(String s) {
		char[] c = s.toCharArray();
		List<Character> lst = new ArrayList<Character>();
		for (int i = 0; i < c.length; i++) {
			lst.add(c[i]);
		}

		Collections.shuffle(lst);

		String resultStr = "";
		for (int i = 0; i < lst.size(); i++) {
			resultStr += lst.get(i);
		}
		return resultStr;
	}

}
