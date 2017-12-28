package cn.com.alo7.inf.controller;

/**
 * 通用controller
 * @author mazan
 *
 */
public class BaseController {
	//默认值
	protected static final String PAGE = "0";
	protected static final String SIZE = "15";
	
	protected static final String SORT_MANUAL = "manual";
	protected static final String SORT_RELEASED_TIME = "releasedTime";
	protected static final String SORT_HOT = "hot";
	
	protected static final String ALBUM_SIZE = "4";
	protected static final String VIDEO_SIZE = "4";
	
	protected static final String START = "1";
	protected static final String END = "99999999";
	
//	private static Map<String, String> sortMap = new HashMap<>();
//	
//	private static final String MANUAL_POSITION = "position";
//	private static final String DATE_UPDATED_AT = "updated_at";
//	private static final String HOT_COUNT = "count";
	
//	/**
//	 * 默认构造函数
//	 */
//	public BaseController() {
//		sortMap.put(SORT_MANUAL, MANUAL_POSITION);
//		sortMap.put(SORT_RELEASED_TIME, DATE_UPDATED_AT);
//		sortMap.put(SORT_HOT, HOT_COUNT);
//	}
	

}
