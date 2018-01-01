package cn.com.alo7.inf.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.com.alo7.inf.vo.DataVo;

/**
 * 
 * @author mazan
 *
 */
public class DataVoHelperTest {

	
	/**
	 * 测试map对象
	 */
	@Test
	public void testHashMap() {
		
		Map<String, Object> map = new HashMap<>();
		map.put("aa", "aa");
		map.put("ab", 1);
		
		
		DataVo<Map<String, Object>> instance = DataVoHelper.getInstance("1", "map", map);
		
		System.out.println(JsonUtils.toJson(instance));
		
	}
}
