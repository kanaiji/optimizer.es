//package web.application.com.interceptors;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.alibaba.fastjson.JSONObject;
//
//import web.application.com.common.entity.ApiCommonResultVo;
//import web.application.com.common.uitls.StringUtil;
//
///**
// * Interceptor拦截器类
// * @author agui
// */
//@Component
//public class AuthInterceptor implements HandlerInterceptor {
//	
//	
//	private Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
//	
//
////	@Autowired
////	public UserAuthService userAuthService;
//	
//	
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//
//		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
//			String token = request.getHeader("token");
//			if (StringUtil.isEmpty(token)) {
//				String token1 = request.getParameter("token");
//				if (StringUtil.isEmpty(token1)) {
//					ApiCommonResultVo resultVo = new ApiCommonResultVo(-1, "请求失败！缺少token参数！", "");
//					writeErrorMsg(response, resultVo);
//					return false;
//				}
//				token = token1;
//			}
//
//			String clientId = "APP";
//			/*WjUser user = userAuthService.checkToken(clientId, token);
//			if (user != null) {
//				String uJson = JSON.toJSONString(user);
//				request.setAttribute("uJson", uJson);
//			} else {
//				ApiCommonResultVo resultVo = new ApiCommonResultVo(-10, "鉴权失败！", "");
//				writeErrorMsg(response, resultVo);
//				return false;
//			}*/
//		}
//		return false;
//
//	}
//    
//    public void writeErrorMsg(HttpServletResponse response, Object obj) throws IOException {
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("application/json;charset=UTF-8");
//		PrintWriter writer = response.getWriter();
//		logger.info("=========>" + JSONObject.toJSONString(obj));
//		writer.print(JSONObject.toJSONString(obj));
//	}
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//
//        System.out.println("myinterc posthandler");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//
//        System.out.println("myinterc aftercompletion");
//    }
//}
