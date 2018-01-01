package cn.com.alo7.inf.common;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.com.alo7.inf.service.IRedisService;
import cn.com.alo7.inf.service.IUserService;
import cn.com.alo7.inf.vo.UserVo;

@Component
public class TokenInterceptor implements HandlerInterceptor {

	@Autowired
	private IRedisService redisService;
	
	@Autowired
	private IUserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(!(handler instanceof HandlerMethod)){
			return true;
		}
		
		HandlerMethod handerMethod = (HandlerMethod)handler;
		Method method = handerMethod.getMethod();
		
		//token验证
		if(null != method.getAnnotation(Authorization.class)){
			String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
			if(null == accessToken || "".equals(accessToken)){
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
			//判断redis中的token,如果第一次登陆token
			UserVo userVo = redisService.getObject(accessToken,UserVo.class);
			if(null == userVo){
				redisService.setObject(accessToken, userService.getUserInfoByAccessToken(accessToken));
				redisService.expire(accessToken, 60 * 30);
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
