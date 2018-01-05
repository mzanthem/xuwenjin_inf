package cn.com.alo7.inf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import cn.com.alo7.inf.common.Constant;
import cn.com.alo7.inf.common.utils.PageUtils;
import cn.com.alo7.inf.entity.AlbumFullView;
import cn.com.alo7.inf.entity.AlbumView;
import cn.com.alo7.inf.repository.AlbumFullViewRepository;
import cn.com.alo7.inf.repository.AlbumViewRepository;
import cn.com.alo7.inf.service.IAlbumViewService;

/**
 * 专辑
 * 
 * @author mazan
 *
 */
@Service
public class AlbumViewServiceImpl implements IAlbumViewService {

	@Autowired
	private AlbumViewRepository albumViewRepository;

	@Autowired
	private AlbumFullViewRepository albumFullViewRepository;
	
	@Override
	public Page<AlbumView> findByAlbumSizeAndSpecialType(Integer albumSize, String type) {
		Pageable pageable = PageUtils.page(null, albumSize, null);

		// 创建查询条件数据对象
		AlbumView albumView = new AlbumView();
		albumView.setSpecialType(type);

		// 创建匹配器
		ExampleMatcher matcher = ExampleMatcher.matching();
		Example<AlbumView> ex = Example.of(albumView, matcher);
		
		return albumViewRepository.findAll(ex, pageable);
	}
	
	
	/**
	 * 根据类型查找专辑列表
	 * @param pageable 1/2
	 * @param specialType  1.一般专辑 2.特殊专辑
	 * @return
	 */
	@Override
	public Page<AlbumFullView> findPageByTypeAndSpecialType(Pageable pageable, String type, String specialType) {
		// 创建查询条件数据对象
		AlbumFullView albumView = new AlbumFullView();
	    if (!Strings.isNullOrEmpty(type)) {
	    	albumView.setType(type);
	    }
	    if (!Strings.isNullOrEmpty(specialType)) {
	    	albumView.setSpecialType(specialType);
	    }
		
		// 创建匹配器
		ExampleMatcher matcher = ExampleMatcher.matching();
		Example<AlbumFullView> ex = Example.of(albumView, matcher);
		return albumFullViewRepository.findAll(ex, pageable);
	}
	/**
	 * 根据id查找专辑信息
	 */
	@Override
	public AlbumView findAlbumById(Long id) {
		return albumViewRepository.findOne(id);
	}

	/**
	 * 根据专辑code,查找特殊专辑
	 */
	@Override
	public AlbumFullView findSpecialAlbumByCode(String identifier) {
		return albumFullViewRepository.findByTypeAndSpecialTypeCode(Constant.ALBUM_SPECIAL_TYPE_SPECIAL, identifier);
	}
	
	/**
	 * 根据id查找专辑信息
	 */
	@Override
	public AlbumFullView findFullAlbumById(Long id) {
		return albumFullViewRepository.findOne(id);
	}
	
	/**
	 * 查找上架专辑
	 * @param id
	 * @return
	 */
	@Override
	public AlbumFullView findFullAlbumByIdAndStatus(Long id, String status) {
		return this.albumFullViewRepository.findByIdAndStatus(id, status);
	}
}
