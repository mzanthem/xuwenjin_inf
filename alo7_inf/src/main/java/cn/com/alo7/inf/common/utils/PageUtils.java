package cn.com.alo7.inf.common.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import cn.com.alo7.inf.common.Constant;

public class PageUtils {
	public static Pageable page(Integer page, Integer size,Sort sort) {
		if (null == page || "".equals(page)) {
			page = Constant.PAGE;
		}
		if (null == size || "".equals(size)) {
			size = Constant.SIZE;
		}
		return new PageRequest(page, size,sort);
	}
	
	
	/**
	 * 翻页，单排序降序
	 * @param page
	 * @param size
	 * @param sort
	 * @return
	 */
	public static Pageable page(Integer page, Integer size, String orderField) {
		return page(page, size, orderField, false);
	}
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @param orderField
	 * @param isASC
	 * @return
	 */
	public static Pageable page(Integer page, Integer size, String orderField, boolean isASC) {
		String orderType = isASC ? "asc" : "desc";
		return page(page, size, SortUtils.baseSort(orderType, orderField));
	}
}
