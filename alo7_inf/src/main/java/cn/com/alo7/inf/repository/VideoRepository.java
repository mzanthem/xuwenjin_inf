package cn.com.alo7.inf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import cn.com.alo7.inf.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>,JpaSpecificationExecutor<Video> {
	
	/**
	 * 通过id相等查询
	 * @param id
	 * @return
	 */ 
	Video findByIdAndDeleteFlag(Long id,String deleteFlag);
	
}