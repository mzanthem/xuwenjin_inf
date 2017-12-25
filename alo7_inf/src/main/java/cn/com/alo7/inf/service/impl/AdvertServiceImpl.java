package cn.com.alo7.inf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.alo7.inf.common.Constant;
import cn.com.alo7.inf.entity.Advert;
import cn.com.alo7.inf.repository.AdvertRepository;
import cn.com.alo7.inf.service.IAdvertService;

@Service
public class AdvertServiceImpl implements IAdvertService{
	
	@Autowired
	private AdvertRepository advertRepository;
	
	@Override
	public List<Advert> findByDeleteFlag(String deleteFlag) {
		return advertRepository.findByDeleteFlag(Constant.DELETE_FLAG_0);
	}
	
}
