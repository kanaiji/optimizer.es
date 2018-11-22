package web.application.com.common.log;

import ch.qos.logback.core.PropertyDefinerBase;

/**
 * 识别操作系统，设置不同的log 路径
 * @author 93733
 *
 */
public class LogbackCustomName extends PropertyDefinerBase{

	private boolean isLux = true;
	
	@Override
	public String getPropertyValue() {
		
		if (System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0) {
            isLux = false;
        }
        return (isLux ? "/wsjc/logback/" : "D:/logback/");
	}

}
