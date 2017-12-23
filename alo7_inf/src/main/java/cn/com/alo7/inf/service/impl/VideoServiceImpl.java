package cn.com.alo7.inf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.alo7.inf.common.Constant;
import cn.com.alo7.inf.entity.Video;
import cn.com.alo7.inf.entity.Work;
import cn.com.alo7.inf.repository.VideoRepository;
import cn.com.alo7.inf.repository.WorkRepository;
import cn.com.alo7.inf.service.IVideoService;

@Service
public class VideoServiceImpl implements IVideoService{

	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private WorkRepository workRepository;
	
	@Override
	public Video findByIdAndDeleteFlag(Long id,String deleteFlag) {
		return videoRepository.findByIdAndDeleteFlag(id,deleteFlag);
	}

	@Override
	public List<Work> findWorkByVideoId(Long id,Integer size) {
		if(null == size || "".equals(size)){
			size = Constant.SIZE_20;
		}
		List<Work> workList = workRepository.findWorkByVideoId(id,size);
		
		return workList;
	}
}