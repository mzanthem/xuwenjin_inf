package cn.com.alo7.inf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.com.alo7.inf.entity.WorkRankWeekView;
/**
 * 作品dao - 每周排行
 * @author mazan
 *
 */
@Repository
public interface WorkRankWeekViewRepository extends JpaRepository<WorkRankWeekView, Long> {
	
}
