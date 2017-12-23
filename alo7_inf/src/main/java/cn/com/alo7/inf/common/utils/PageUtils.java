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
}
