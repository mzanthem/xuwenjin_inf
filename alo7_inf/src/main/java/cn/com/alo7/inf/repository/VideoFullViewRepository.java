package cn.com.alo7.inf.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.com.alo7.inf.entity.VideoFullView;

/**
 * 视频视图dao
 * @author mazan
 *
 */
@Repository
public interface VideoFullViewRepository extends JpaRepository<VideoFullView, Long> {
	
	/**
	 * 返回查询结果
	 * @param albumId
	 * @return
	 */
	@Query(value="select count(vav.id) as videoCount,sum(vav.hot) as videoWorkCount from VideoFullView vav where vav.albumId = ?1")
	List<Map<String, Object>> findVideoAndWorkCount(Long albumId);
	
}
