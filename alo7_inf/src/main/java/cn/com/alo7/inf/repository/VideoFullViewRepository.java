package cn.com.alo7.inf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.com.alo7.inf.entity.VideoFullView;

/**
 * 视频视图dao
 * @author mazan
 *
 */
@Repository
public interface VideoFullViewRepository extends JpaRepository<VideoFullView, Long>{
}
