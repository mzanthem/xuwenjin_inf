package cn.com.alo7.inf.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Sort;

import cn.com.alo7.inf.common.utils.SortUtils;

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
	
	private static Map<String, String> sortMap = new HashMap<>();
	
	private static final String MANUAL_POSITION = "position";
	private static final String DATE_UPDATED_AT = "updated_at";
	private static final String HOT_COUNT = "count";
	
	/**
	 * 默认构造函数
	 */
	public BaseController() {
		sortMap.put(SORT_MANUAL, MANUAL_POSITION);
		sortMap.put(SORT_RELEASED_TIME, DATE_UPDATED_AT);
		sortMap.put(SORT_HOT, HOT_COUNT);
	}
	
	/**
	 * 通用排序方法
	 * 
	 * @param sortStr
	 * @param isASC
	 * @return
	 */
	protected Sort getCommonSort(String orderField, boolean isASC) {
		
		String orderType = isASC ? "asc" : "desc"; 
		String personalField = sortMap.get(orderField);
		
		String field = (null == personalField) ? orderField : personalField;
		Sort sort = SortUtils.baseSort(orderType, field); 
		
		return sort;
	}
}
