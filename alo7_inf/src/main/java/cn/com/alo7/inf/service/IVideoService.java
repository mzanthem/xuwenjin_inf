package cn.com.alo7.inf.service;

import cn.com.alo7.inf.entity.Video;

/**
 * 视频service
 * 
 * @author mazan
 *
 */
public interface IVideoService {

	/**
	 * 通过id相等查询
	 * 
	 * @param id
	 * @return
	 */
	Video findByIdAndDeleteFlag(Long id, String deleteFlag);

}
