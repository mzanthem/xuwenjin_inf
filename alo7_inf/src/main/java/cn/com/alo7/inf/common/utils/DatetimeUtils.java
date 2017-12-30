package cn.com.alo7.inf.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;


public class DatetimeUtils {
	
	/**
	 * 时区
	 */
	private static final String PATTERN_01= "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	
	
	/**
	 * 获取Timestamp
	 * @return
	 */
	public static Timestamp getTimestamp(){
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * 获取时区
	 * @param timestamp
	 * @return
	 */
	public static String getDatetime(Timestamp timestamp) {
		DateFormat dateFormat = new SimpleDateFormat(DatetimeUtils.PATTERN_01);
		Date date = new Date();
		date = timestamp;
		return dateFormat.format(date);
	}
	
	public static void main(String[] arg){
		System.out.println(getDatetime(getTimestamp()));
	}
}
