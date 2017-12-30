package cn.com.alo7.inf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.alo7.inf.common.Constant;
import cn.com.alo7.inf.common.utils.DatetimeUtils;
import cn.com.alo7.inf.entity.Video;
import cn.com.alo7.inf.entity.Work;
import cn.com.alo7.inf.repository.WorkRepository;
import cn.com.alo7.inf.service.IRedisService;
import cn.com.alo7.inf.service.IWorkService;
import cn.com.alo7.inf.vo.UserVo;

/**
 * 作品相关实现
 * 
 * @author mazan
 *
 */
@Service
public class WorkServiceImpl implements IWorkService {

	@Autowired
	private WorkRepository workRepository;

	@Autowired
	private IRedisService redisService;
	
	@Override
	public Work findById(Long id) {
		return workRepository.findByIdAndDeleteFlag(id, Constant.DELETE_FLAG_0);
	}

	/**
	 * 得到视频下的所有作品
	 */
	@Override
	public List<Work> findWorkByVideoId(Long id, Integer size) {
		List<Work> workList = workRepository.findWorkByVideoId(id, size);

		return workList;
	}

	@Override
	public Work saveUserWork(Long videoId, String fileUrl, String status,UserVo userVo) {
		Work work = new Work();
		Video video = new Video();
		//发布需要合成视频调用接口 合成视频的url(分享视频url) 添加workuuid
		if(Constant.STATUS_RELEASED.equals(status)){
			
		}else{
			video.setId(videoId);
			work.setUuid(userVo.getId());
			work.setVideo(video);
			work.setVoiceTrackUrl(fileUrl);
			work.setStatus(status);
			work.setIsInBlacklist(Constant.NO);
			work.setCreatedBy(work.getUuid());
			work.setCreatedAt(DatetimeUtils.getTimestamp());
			work.setUpdatedBy(work.getUuid());
			work.setUpdatedAt(work.getCreatedAt());
			work.setDeleteFlag(Constant.DELETE_FLAG_0);
			work.setLikeCount(work.getLikeCountIn());
			return workRepository.save(work);
		}
		return null;
	}
}
