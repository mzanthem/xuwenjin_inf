package cn.com.alo7.inf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.com.alo7.inf.entity.VideoView;

/**
 * 视频视图dao
 * @author mazan
 *
 */
@Repository
public interface VideoViewRepository extends JpaRepository<VideoView, Long>{
}
