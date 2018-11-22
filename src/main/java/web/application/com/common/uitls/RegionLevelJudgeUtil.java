package web.application.com.common.uitls;

import web.application.com.common.constans.CommonConst;

public class RegionLevelJudgeUtil {

	
	/**
	 * 获得区域级别
	 * @param string
	 */
	public static String obtainScopeLevel(String scope) {
		
		String level = scope.substring(0, 2);
		if(level.equals("00")){
			return CommonConst.SCOPE_LEVEL_COUNTRY;
		}
		level = scope.substring(2, 6);
		if(level.equals("0000")){
			return CommonConst.SCOPE_LEVEL_PROVINCE;
		}
		level = scope.substring(4, 6);
		if(level.equals("00")){
			return CommonConst.SCOPE_LEVEL_CITY;
		}
		return CommonConst.SCOPE_LEVEL_AREA;
		
	}
}
