package cn.com.alo7.inf.common.utils;

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
	
}
