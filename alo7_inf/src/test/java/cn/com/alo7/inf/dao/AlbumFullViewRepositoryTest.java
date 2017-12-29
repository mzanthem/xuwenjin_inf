package cn.com.alo7.inf.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.alo7.inf.AloApplicationStarterTests;
import cn.com.alo7.inf.common.utils.JsonUtils;
import cn.com.alo7.inf.entity.AlbumFullView;
import cn.com.alo7.inf.repository.AlbumFullViewRepository;

public class AlbumFullViewRepositoryTest extends AloApplicationStarterTests {

	@Autowired
	private AlbumFullViewRepository albumFullViewDao;
	@Test
	public void testFindBySpecialCode() {
//		AlbumFullView t = albumFullViewDao.findByTypeAndSpecialTypeCode("2","test2");
		AlbumFullView t = albumFullViewDao.findOne(1L);
		System.out.println(JsonUtils.toJson(t));
	}

}
