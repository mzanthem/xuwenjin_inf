package cn.com.alo7.inf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.alo7.inf.common.Constant;
import cn.com.alo7.inf.entity.Work;
import cn.com.alo7.inf.repository.WorkRepository;
import cn.com.alo7.inf.service.IWorkService;

/**
 * 作品相关实现
 * 
 * @author mazan
 *
 */
@Service
public class WorkServiceImpl implements IWorkService {

	@Autowired
	private WorkRepository workRepository;

	@Override
	public Work findById(Long id) {
		return workRepository.findByIdAndDeleteFlag(id, Constant.DELETE_FLAG_0);
	}

	/**
	 * 得到视频下的所有作品
	 */
	@Override
	public List<Work> findWorkByVideoId(Long id, Integer size) {
		List<Work> workList = workRepository.findWorkByVideoId(id, size);

		return workList;
	}

}
