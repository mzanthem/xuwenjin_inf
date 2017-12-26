package cn.com.alo7.inf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController extends BaseController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * 获取根据token获取用户信息
	 * @param videoId
	 * @return
	 * @throws Exception 
	 */
	@GetMapping("users/info")
	public String getUserInfo(){
		String str = restTemplate.getForObject("http://account-api.dev.saybot.net/api/v1/users/info",String.class);
		return "";
	}
}
