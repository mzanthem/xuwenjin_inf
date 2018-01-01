package cn.com.alo7.inf.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.com.alo7.inf.entity.WorkFullView;
/**
 * 作品dao
 * @author mazan
 *
 */
@Repository
public interface WorkFullViewRepository extends JpaRepository<WorkFullView, Long> {
	/**
	 * 统计专辑下作品的总数
	 * @param albumId
	 * @return
	 */
	@Query(value = "select count(vaw.id)  as albumWorkNum from WorkFullView vaw where vaw.albumId = ?1")
	List<Map<String, Object>> findTotalWorkNum(Long albumId);
}
