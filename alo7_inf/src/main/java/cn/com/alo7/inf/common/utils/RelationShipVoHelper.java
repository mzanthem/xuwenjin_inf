/*
 * RelationShipVoHelper.java Created On 2017年12月29日
 * Copyright(c) 2017 Mazan Inc.
 * ALL Rights Reserved.
 */
package cn.com.alo7.inf.common.utils;
/**
 * RelationShipVoHelper
 *
 * @time: 下午7:17:25
 * @author mazan
 */

import java.util.HashMap;
import java.util.Map;

import cn.com.alo7.inf.vo.RelationshipDataVo;
import cn.com.alo7.inf.vo.RelationshipListVo;
import cn.com.alo7.inf.vo.RelationshipVo;

public class RelationShipVoHelper {

	
	/**
	 * 构造relationship
	 * @return
	 */
	public static RelationshipVo<RelationshipDataVo> getInstance(String id, String type) {
		 
		RelationshipDataVo data = new RelationshipDataVo(id, type); //id,type 最里层
		RelationshipVo<RelationshipDataVo> relationship = new RelationshipVo<RelationshipDataVo>(data);
		return relationship;
	}
	
	/**
	 * 构造relationship
	 * @return
	 */
	public static RelationshipListVo<RelationshipDataVo> getListInstance(String id, String type) {
		 
		RelationshipDataVo data = new RelationshipDataVo(id, type); //id,type 最里层
		RelationshipListVo<RelationshipDataVo> relationshipList = new RelationshipListVo<RelationshipDataVo>();
		relationshipList.getData().add(data);
		return relationshipList;
	}
	
	
	
	/**
	 * 构造relationShip
	 * @param key
	 * @param id
	 * @param type
	 * @return
	 */
	public static Map<String, Object> buildRelationships(String key, String id, String type) {
		
		Map<String, Object> relationships = new HashMap<>();
		relationships.put(key, getInstance(id, type));
		return relationships;
	}
	/**
	 * 构造relationShip
	 * @param key
	 * @param id
	 * @param type
	 * @return
	 */
	public static Map<String, Object> buildRelationships(String key, RelationshipVo<?> relationship) {
		Map<String, Object> relationships = new HashMap<>();
		relationships.put(key, relationship);
		return relationships;
	}
	
	/**
	 * 构造relationShip --List
	 * @param key
	 * @param id
	 * @param type
	 * @return
	 */
	public static Map<String, Object> buildRelationships(String key, RelationshipListVo<?> relationshipList) {
		Map<String, Object> relationships = new HashMap<>();
		relationships.put(key, relationshipList);
		return relationships;
	}
	
	
}
