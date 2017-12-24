package cn.com.alo7.inf.common.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.alo7.inf.vo.DataVo;
import cn.com.alo7.inf.vo.RelationshipDataVo;
import cn.com.alo7.inf.vo.RootVo;

public class JsonUtils {
	
	public static RootVo createRoot(){
		return new RootVo();
	}
	
	public static <T> DataVo<T> setData(Long id,String type,T t){
		DataVo<T> dataVo = new DataVo<T>();
		dataVo.setId(id);
		dataVo.setType(type);
		dataVo.setAttributes(t);
		return dataVo;
	}
	
	public static RelationshipDataVo setRelationshipDataVo(Long id,String type){
		RelationshipDataVo relationshipDataVo = new RelationshipDataVo();
		relationshipDataVo.setId(id);
		relationshipDataVo.setType(type);
		return relationshipDataVo;
	}

	//--------------------------------------------------------------//
	
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 把json字符串转换为对象
	 * 
	 * @param json
	 * @param parametrized
	 * @param parameterClasses
	 * @return 如果为空串，将返回null
	 * @throws ClientException
	 * @since
	 */
	public static <T> T parseJson(String json, Class<?> parametrized,
			Class<?>... parameterClasses) {
		if (json == null || json=="" ) {
			return null;
		}
		try {
			JavaType jt = mapper.getTypeFactory().constructParametricType(
					parametrized, parameterClasses);
			return mapper.readValue(json, jt);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T parseJson(String json, Class<?> parametrized) {
		if (json == null || json=="" ) {
			return null;
		}
		try {
			return (T) mapper.readValue(json, parametrized);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static String toJson(Object obj) {
		if (obj == null) {
			return "";
		}
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
