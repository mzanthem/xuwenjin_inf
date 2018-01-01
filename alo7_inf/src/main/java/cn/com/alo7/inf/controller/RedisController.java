package cn.com.alo7.inf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.alo7.inf.service.IRedisService;
import cn.com.alo7.inf.vo.UserVo;

@RestController
public class RedisController extends BaseController {
	
	@Autowired
	private IRedisService redisService;
	
	@GetMapping("token")
	public String setToken(@RequestParam(value="token", required=true) String token,
			@RequestParam(value="value", required=true) String value){
		boolean isOk = redisService.set(token, value);
		return isOk == true?"200":"500";
	}
	
	@PostMapping("tokenObject")
	public String postTokenObject(@RequestParam(value="token", required=true) String token,UserVo userVo){
		boolean isOk = redisService.setObject(token, userVo);
		return isOk == true?"200":"500";
	}
}
