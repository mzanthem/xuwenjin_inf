package cn.com.alo7.inf.service;

import org.springframework.data.domain.Page;

import cn.com.alo7.inf.entity.AlbumView;

public interface IAlbumViewService {
	
	/**
	 * 一般视频专辑清单
	 * 特殊视频专辑清单
	 * @param albumSize
	 * @param type
	 * @return
	 */
	Page<AlbumView> findByAlbumSizeAndType(Integer albumSize,String type);
}
