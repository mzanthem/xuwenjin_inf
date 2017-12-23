package cn.com.alo7.inf.common.utils;

import java.util.UUID;

public class TokenUtils {
	
	/**
	 * 生成token
	 * @return
	 */
	public static String createToken(){
		UUID uuid = UUID.randomUUID();
		String token = uuid.toString();
		token = token.toUpperCase();
		token = token.replaceAll("-", "");
		return token;
	}
	
	
	public static void main(String[] arg){
		UUID uuid = UUID.randomUUID();
		String token = uuid.toString();
		token = token.toUpperCase();
		token = token.replaceAll("-", "");
		System.out.println(token);
	}
}
