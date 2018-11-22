package web.application.com.common.log.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

/**
 * logback appender
 * 
 * @author 93733
 *
 */
public class GwarnLogbackAppender extends AppenderBase<ILoggingEvent> {

	@Override
	protected void append(ILoggingEvent eventObject) {
		
		
		if (eventObject != null && eventObject.getMessage() != null) {
			
			// 自定义实现
			System.out.println("自定义logbackAppender :" + eventObject.getMessage());
			eventObject.getLevel().toString() ;
		}

	}

}
