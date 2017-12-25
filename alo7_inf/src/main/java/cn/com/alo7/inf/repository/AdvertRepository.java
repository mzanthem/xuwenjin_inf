package cn.com.alo7.inf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.com.alo7.inf.entity.Advert;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long>{
	
	/**
	 * 查询Advert广告
	 * @param deleteFlag
	 * @return
	 */
	List<Advert> findByDeleteFlag(String deleteFlag);
}
