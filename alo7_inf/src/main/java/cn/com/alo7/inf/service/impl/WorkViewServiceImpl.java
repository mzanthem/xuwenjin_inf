package cn.com.alo7.inf.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.com.alo7.inf.common.Constant;
import cn.com.alo7.inf.entity.WorkFullView;
import cn.com.alo7.inf.entity.WorkRankTotalView;
import cn.com.alo7.inf.entity.WorkRankWeekView;
import cn.com.alo7.inf.repository.WorkFullViewRepository;
import cn.com.alo7.inf.repository.WorkRankTotalViewRepository;
import cn.com.alo7.inf.repository.WorkRankWeekViewRepository;
import cn.com.alo7.inf.service.IWorkViewService;

@Service
public class WorkViewServiceImpl implements IWorkViewService {
	
	/**
	 * 作品dao
	 */
	@Autowired
	private WorkFullViewRepository workFullViewRepository;
	
	/**
	 * 作品总排行
	 */
	@Autowired
	private WorkRankTotalViewRepository rankTotalDao;
	
	/**
	 * 作品周排行
	 */
	@Autowired
	private WorkRankWeekViewRepository rankWeekDao;
	
	
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
	public Page<WorkFullView> findWorkByUserId(String userId, boolean isCurrentUser, Pageable pageable) {
		// 创建查询信息
		WorkFullView wfv = new WorkFullView();
		wfv.setUuid(userId);
		
		// 自己：发布+非发布，非自己：发布
		if (!isCurrentUser) {
			wfv.setStatus(Constant.STATUS_RELEASED);
		}
		
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
	
	/**
	 * 查询用户排序
	 */
	@Override
	public Page<WorkFullView> findWorkRank(String type, Pageable pageable) {

		if("total".equals(type)) {
			return  this.findTotalRank(pageable);
		} 
		if("week".equals(type)) {
			return  this.findWeekRank(pageable);
		} 
		return null;
	}
	/**
	 * 查询总榜
	 * @param pageable
	 * @return
	 */
	private Page<WorkFullView> findTotalRank(Pageable pageable) {
		WorkRankTotalView entry = new WorkRankTotalView();
		// 创建匹配器
		ExampleMatcher matcher = ExampleMatcher.matching();
		Example<WorkRankTotalView> ex = Example.of(entry, matcher);

		// 查询分页
		Page<WorkRankTotalView> pageList = this.rankTotalDao.findAll(ex, pageable);
		List<WorkFullView> resultList = new ArrayList<>();
		for (WorkRankTotalView workRankTotalView : pageList) {
			WorkFullView entity = new WorkFullView();
			BeanUtils.copyProperties(workRankTotalView, entity);
			resultList.add(entity);
		}
		Page<WorkFullView> resultPage = new PageImpl<>(resultList);
		return resultPage;
	}
	/**
	 * 查询每周榜
	 * @param pageable
	 * @return
	 */
	private Page<WorkFullView> findWeekRank(Pageable pageable) {
		WorkRankWeekView entry = new WorkRankWeekView();
		// 创建匹配器
		ExampleMatcher matcher = ExampleMatcher.matching();
		Example<WorkRankWeekView> ex = Example.of(entry, matcher);

		// 查询分页
		Page<WorkRankWeekView> pageList = this.rankWeekDao.findAll(ex, pageable);
		List<WorkFullView> resultList = new ArrayList<>();
		for (WorkRankWeekView workRankWeekView : pageList) {
			WorkFullView entity = new WorkFullView();
			BeanUtils.copyProperties(workRankWeekView, entity);
			resultList.add(entity);
		}
		Page<WorkFullView> resultPage = new PageImpl<>(resultList);
		return resultPage;
	}
	
}
