//package web.application.com.configuration.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 该工程链路追踪切面
// * @author JingWangZou
// *
// */
/////证明是一个配置文件(使用@Component也可以,因为点入后会发现@Configuration还是使用了@Component)
//@Configuration
////证明是一个切面
//@Aspect
//public class LinkTrackAopConfig {
//
//	protected static Logger logger = LoggerFactory.getLogger(LinkTrackAopConfig.class);
//	
//	// 需要拦截 delete** update** insert**的方法
//	//环绕aop
//    //execution表达式 此表达式表示扫描mapper下所有类的所有方法都执行此aop
//    @Around("execution (* com.rpc.service.v1.user.service.*.*LT(..))")
//    public Object testAop(ProceedingJoinPoint pro){
//    	logger.info("start mapper method");
//        //执行调用的方法
//        Object proceed = null;
//		
//        try {
//			proceed = pro.proceed();
//		} catch (Throwable e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        //方法执行完成后执行的方法
//        logger.info("end mapper method");
//        
//        return proceed;
//    }
//}
