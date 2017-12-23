package cn.com.alo7.inf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.com.alo7.inf.common.utils.PageUtils;
import cn.com.alo7.inf.entity.AlbumView;
import cn.com.alo7.inf.repository.AlbumViewRepository;
import cn.com.alo7.inf.service.IAlbumViewService;

@Service
public class AlbumViewServiceImpl implements IAlbumViewService{

	@Autowired
	private AlbumViewRepository albumViewRepository;

	@Override
	public Page<AlbumView> findByAlbumSizeAndType(Integer albumSize,String type){
		Pageable pageable = PageUtils.page(null, albumSize,null);
		
		//创建查询条件数据对象
		AlbumView albumView = new AlbumView();
		albumView.setType(type);
		
		//创建匹配器
		ExampleMatcher matcher = ExampleMatcher.matching();
		Example<AlbumView> ex = Example.of(albumView,matcher);
		return albumViewRepository.findAll(ex,pageable);
	}
}
