package cn.com.alo7.inf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.alo7.inf.entity.Video;
import cn.com.alo7.inf.repository.VideoRepository;
import cn.com.alo7.inf.service.IVideoService;

/**
 * 
 * @author mazan 把findWorkByVideoId移到WorkService,做到单一职责原则；
 *         使用时，EntityService尽量只调用本EntityRepository; 需要用到其他实体时，调用service接口而非dao;
 */
@Service
public class VideoServiceImpl implements IVideoService {

	@Autowired
	private VideoRepository videoRepository;

	// @Autowired
	// private WorkRepository workRepository;

	@Override
	public Video findByIdAndDeleteFlag(Long id, String deleteFlag) {
		return videoRepository.findByIdAndDeleteFlag(id, deleteFlag);
	}

}
