package cn.com.alo7.inf.service;

import org.springframework.data.domain.Page;

import cn.com.alo7.inf.entity.VideoView;

/**
 * 视频视图service
 * @author mazan
 *
 */
public interface IVideoViewService {
	
	Page<VideoView> findByAlbumIdAndVideoSizeAndSort(Long albumId, Integer videoSize, String sort);
}
