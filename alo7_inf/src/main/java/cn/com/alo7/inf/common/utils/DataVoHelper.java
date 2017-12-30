/*
 * DateVoHelper.java Created On 2017年12月29日
 * Copyright(c) 2017 Mazan Inc.
 * ALL Rights Reserved.
 */
package cn.com.alo7.inf.common.utils;

import org.springframework.beans.BeanUtils;

import cn.com.alo7.inf.vo.DataVo;

/**
 * DateVoHelper
 *
 * @time: 下午6:51:24
 * @author mazan
 */
public class DataVoHelper {

	/**
	 * 构造dateVo<T>
	 * @param id
	 * @param type
	 * @param t
	 * @return
	 */
	public static <T> DataVo<T> getInstance(String id, String type, Object E, T t) {
		BeanUtils.copyProperties(E, t);
		DataVo<T> dataVo = JsonUtils.setData(id, type, t);
		return dataVo;
	}
}
