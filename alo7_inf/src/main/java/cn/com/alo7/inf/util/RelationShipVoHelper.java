/*
 * RelationShipVoHelper.java Created On 2017年12月29日
 * Copyright(c) 2017 Mazan Inc.
 * ALL Rights Reserved.
 */
package cn.com.alo7.inf.util;
/**
 * RelationShipVoHelper
 *
 * @time: 下午7:17:25
 * @author mazan
 */

import cn.com.alo7.inf.vo.RelationshipDataVo;
import cn.com.alo7.inf.vo.RelationshipListVo;
import cn.com.alo7.inf.vo.RelationshipVo;

public class RelationShipVoHelper {

	
	/**
	 * 构造relationship
	 * @return
	 */
	public static RelationshipVo<RelationshipDataVo> getInstance(Long id, String type) {
		 
		RelationshipDataVo data = new RelationshipDataVo(id, type); //id,type 最里层
		RelationshipVo<RelationshipDataVo> relationship = new RelationshipVo<RelationshipDataVo>(data);
		return relationship;
	}
	
	/**
	 * 构造relationship
	 * @return
	 */
	public static RelationshipListVo<RelationshipDataVo> getListInstance(Long id, String type) {
		 
		RelationshipDataVo data = new RelationshipDataVo(id, type); //id,type 最里层
		RelationshipListVo<RelationshipDataVo> relationshipList = new RelationshipListVo<RelationshipDataVo>();
		relationshipList.getData().add(data);
		return relationshipList;
	}
	
	
}
