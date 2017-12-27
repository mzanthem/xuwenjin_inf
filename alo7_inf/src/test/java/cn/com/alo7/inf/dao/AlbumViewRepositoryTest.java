package cn.com.alo7.inf.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.alo7.inf.AloApplicationStarterTests;
import cn.com.alo7.inf.common.utils.JsonUtils;
import cn.com.alo7.inf.entity.AlbumView;
import cn.com.alo7.inf.repository.AlbumViewRepository;

public class AlbumViewRepositoryTest extends AloApplicationStarterTests {

	@Autowired
	private AlbumViewRepository albumViewDao;
	@Test
	public void testFindBySpecialCode() {
		AlbumView t = albumViewDao.findByTypeAndSpecialTypeCode("2","test2");
		System.out.println(JsonUtils.toJson(t));
	}

}
