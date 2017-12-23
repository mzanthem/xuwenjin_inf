package cn.com.alo7.inf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.com.alo7.inf.entity.AlbumView;

@Repository
public interface AlbumViewRepository extends JpaRepository<AlbumView, Long>{
}