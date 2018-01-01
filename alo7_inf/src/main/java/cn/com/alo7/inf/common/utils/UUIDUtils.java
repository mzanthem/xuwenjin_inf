package cn.com.alo7.inf.common.utils;

import java.util.UUID;

public class UUIDUtils {
	
	/**
	 * 生成uuid
	 * @return
	 */
	public static String createUUID(){
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}
}
