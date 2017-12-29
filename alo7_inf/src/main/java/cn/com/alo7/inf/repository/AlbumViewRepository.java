package cn.com.alo7.inf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.com.alo7.inf.entity.AlbumView;

/**
 * 栏目视图dao
 * @author mazan
 *
 */
@Repository
public interface AlbumViewRepository extends JpaRepository<AlbumView, Long>{
	
}
