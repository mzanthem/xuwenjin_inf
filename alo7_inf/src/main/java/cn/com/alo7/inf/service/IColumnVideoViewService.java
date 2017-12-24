package cn.com.alo7.inf.service;

import org.springframework.data.domain.Page;

import cn.com.alo7.inf.entity.ColumnVideoView;

/**
 * 栏目视频service
 * @author mazan
 *
 */
public interface IColumnVideoViewService {
	/**
	 * 根据查询条件查询栏目视频
	 * @param columnId
	 * @param page
	 * @param size
	 * @param sort
	 * @return
	 */
	Page<ColumnVideoView> findAll(Long columnId,Integer page, Integer size, String sort);
}
