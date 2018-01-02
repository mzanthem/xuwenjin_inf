package cn.com.alo7.inf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.com.alo7.inf.entity.WorkFullView;
import cn.com.alo7.inf.repository.WorkFullViewRepository;
import cn.com.alo7.inf.service.IWorkViewService;

@Service
public class WorkViewServiceImpl implements IWorkViewService {
	
	/**
	 * 作品dao
	 */
	@Autowired
	private WorkFullViewRepository workFullViewRepository;
	
	/**
	 * 特殊作品翻页
	 */
	@Override
	public Page<WorkFullView> findWorkByAlbumId(Long albumId, Pageable pageable) {
		// 创建查询信息
		WorkFullView wfv = new WorkFullView();
		wfv.setAlbumId(albumId);
		// 创建匹配器
		ExampleMatcher matcher = ExampleMatcher.matching();
		Example<WorkFullView> ex = Example.of(wfv, matcher);

		
		// 查询数量?
		// 查询分页
		Page<WorkFullView> pageList = this.workFullViewRepository.findAll(ex, pageable);
		return pageList;
	}
	/**
	 * 查找用户作品
	 */
	@Override
	public Page<WorkFullView> findWorkByUserId(String userId, Pageable pageable) {
		// 创建查询信息
		WorkFullView wfv = new WorkFullView();
		wfv.setUuid(userId);
		// 创建匹配器
		ExampleMatcher matcher = ExampleMatcher.matching();
		Example<WorkFullView> ex = Example.of(wfv, matcher);

		// 查询分页
		Page<WorkFullView> pageList = this.workFullViewRepository.findAll(ex, pageable);
		return pageList;
	}

	/**
	 * 作品总数
	 */
	@Override
	public Map<String, Object> findWorkTotal(Long albumId) {
		Map<String, Object> result = new HashMap<>();
		List<Map<String, Object>> list = this.workFullViewRepository.findTotalWorkNum(albumId);
		if (list.isEmpty()) {
			result.put("albumWorkNum", 0);
		} else {
			result = list.get(0);
		}
		
		
		return result;
	}
	
	/**
	 * 作品总数
	 */
	@Override
	public Map<String, Object> findWorkTotalbyUserId(String userId) {
		Map<String, Object> result = new HashMap<>();
		List<Map<String, Object>> list = this.workFullViewRepository.findTotalWorkNumbyUserId(userId);
		if (list.isEmpty()) {
			result.put("workNum", 0);
		} else {
			result = list.get(0);
		}
		
		
		return result;
	}

	/**
	 * 根据id查找作品
	 */
	@Override
	public WorkFullView findById(Long workId) {
		return this.workFullViewRepository.findOne(workId);
	}

}
