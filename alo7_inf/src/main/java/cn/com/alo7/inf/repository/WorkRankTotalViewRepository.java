package cn.com.alo7.inf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.com.alo7.inf.entity.WorkRankTotalView;
/**
 * 作品dao--总排行
 * @author mazan
 *
 */
@Repository
public interface WorkRankTotalViewRepository extends JpaRepository<WorkRankTotalView, Long> {
	
}
