package cn.com.alo7.inf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.alo7.inf.common.Constant;
import cn.com.alo7.inf.entity.Work;
import cn.com.alo7.inf.repository.WorkRepository;
import cn.com.alo7.inf.service.IWorkService;

@Service
public class WorkServiceImpl implements IWorkService{
	
	@Autowired
	private WorkRepository workRepository;
	
	@Override
	public Work findById(Long id) {
		return workRepository.findByIdAndDeleteFlag(id,Constant.DELETE_FLAG_0);
	}
	
}