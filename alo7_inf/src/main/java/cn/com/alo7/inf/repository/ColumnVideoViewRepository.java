package cn.com.alo7.inf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.com.alo7.inf.entity.ColumnVideoView;

/**
 * 栏目视频dao
 * @author mazan
 *
 */
@Repository
public interface ColumnVideoViewRepository extends JpaRepository<ColumnVideoView, Long>{
}
