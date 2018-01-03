package cn.com.alo7.inf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.com.alo7.inf.common.Constant;
import cn.com.alo7.inf.service.IRedisService;
import cn.com.alo7.inf.vo.UserVo;

/**
 * 通用controller
 * @author mazan
 *
 */
public class BaseController {
	//默认值
	protected static final String PAGE = "0";
	protected static final String SIZE = "15";
	protected static final String SORT = "manual";
	protected static final String ALBUM_SIZE = "4";
	protected static final String VIDEO_SIZE = "4";
	
	protected static final String SORT_MANUAL = "manual";
	protected static final String SORT_RELEASED_TIME = "releasedTime";
	protected static final String SORT_HOT = "hot";
	
	protected static final String START = "1";
	protected static final String END = "99999999";
	@Autowired
	private IRedisService redisService;
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();	
		return request;
	}
	
	public UserVo getUser(){
		String accessToken =  this.getRequest().getHeader(Constant.ACCESS_TOKEN);
		return redisService.getObject(accessToken, UserVo.class);
	}
	
	
	public boolean checkCurrentUser(String uuid) {
		// TODO 判断当前用户
		if ("1111".equals(uuid)) {
			return true;
		}
		return false;
	}
}
