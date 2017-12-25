package cn.com.alo7.inf.service;

import java.util.List;

import cn.com.alo7.inf.entity.Advert;

public interface IAdvertService {
	
	/**
	 * 查询Advert广告
	 * @param deleteFlag
	 * @return
	 */
	List<Advert> findByDeleteFlag(String deleteFlag);
}
