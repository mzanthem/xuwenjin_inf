package cn.com.alo7.inf.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.com.alo7.inf.entity.VideoFullView;
import cn.com.alo7.inf.entity.VideoView;

/**
 * 视频视图service
 * @author mazan
 *
 */
public interface IVideoViewService {
	
	Page<VideoView> findByAlbumIdAndVideoSizeAndSort(Long albumId, Integer videoSize, String sort);
	/**
	 * 根据专辑id查询视频翻页信息
	 * @param albumId
	 * @param page
	 * @param size
	 * @param sort
	 * @return
	 */
	Page<VideoFullView> findFullByAlbumIdAndQueryWithPage(Long albumId, Pageable pageable);
	
	/**
	 * 根据专辑id查询视频总数&视频下作品总数
	 * @param albumId
	 * @return
	 */
	Map<String, Object> findVideoAndWorkTotal(Long albumId);
}
