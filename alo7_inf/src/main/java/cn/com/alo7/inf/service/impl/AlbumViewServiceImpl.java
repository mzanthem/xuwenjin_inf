package cn.com.alo7.inf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
	public Page<AlbumView> findByAlbumSizeAndType(Integer albumSize, String type) {
		Pageable pageable = PageUtils.page(null, albumSize, null);

		// 创建查询条件数据对象
		AlbumView albumView = new AlbumView();
		albumView.setType(type);

		// 创建匹配器
		ExampleMatcher matcher = ExampleMatcher.matching();
		Example<AlbumView> ex = Example.of(albumView, matcher);
		return albumViewRepository.findAll(ex, pageable);
	}
	
	/**
	 * 根据类型查找专辑列表
	 * @param pageable 1/2
	 * @param type
	 * @return
	 */
	@Override
	public Page<AlbumFullView> findByTypeWithPage(Pageable pageable, String type) {
		// 创建查询条件数据对象
		AlbumFullView albumView = new AlbumFullView();
		albumView.setType(type);
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
		return albumFullViewRepository.findByTypeAndSpecialTypeCode(Constant.ALBUM_TYPE_SPECIAL, identifier);
	}
	
	/**
	 * 根据id查找专辑信息
	 */
	@Override
	public AlbumFullView findFullAlbumById(Long id) {
		return albumFullViewRepository.findOne(id);
	}

}
