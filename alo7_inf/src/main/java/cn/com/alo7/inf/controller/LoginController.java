package cn.com.alo7.inf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController extends BaseController {
	
	/**
	 * A18-用户第一次登陆 
	 * @param videoId
	 * @return
	 * @throws Exception 
	 */
	@GetMapping("login")
	public String columns(@RequestParam(value="uuid", required=false) String uuid){
		
		return "";
	}
}
