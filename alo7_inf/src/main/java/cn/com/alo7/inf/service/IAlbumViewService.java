package cn.com.alo7.inf.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.com.alo7.inf.entity.AlbumFullView;
import cn.com.alo7.inf.entity.AlbumView;

/**
 * 专辑视图service
 * 
 * @author mazan
 *
 */
public interface IAlbumViewService {

	/**
	 * 一般视频专辑清单 特殊视频专辑清单
	 * 
	 * @param albumSize
	 * @param type
	 * @return
	 */
	Page<AlbumView> findByAlbumSizeAndSpecialType(Integer albumSize, String type);

	/**
	 * 根据专辑id,查找专辑信息
	 * @param id
	 * @return
	 */
	AlbumView findAlbumById(Long id);
	
	
	//-------------------------------------------------------//
	/**
	 * 根据id查找专辑信息
	 * @param id
	 * @return
	 */
	AlbumFullView findFullAlbumById(Long id);
	/**
	 * 根据专辑code,查找特殊专辑
	 * @param identifier
	 * @return
	 */
	AlbumFullView findSpecialAlbumByCode(String identifier);
	
	/**
	 * 专辑翻页查询
	 * @param pageable
	 * @param type
	 * @return
	 */
	Page<AlbumFullView> findByTypeWithPage(Pageable pageable, String type);
	
}
