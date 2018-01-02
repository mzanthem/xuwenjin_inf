package cn.com.alo7.inf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.alo7.inf.common.Authorization;
import cn.com.alo7.inf.common.Constant;
import cn.com.alo7.inf.common.utils.DatetimeUtils;
import cn.com.alo7.inf.common.utils.JsonUtils;
import cn.com.alo7.inf.entity.Video;
import cn.com.alo7.inf.entity.Work;
import cn.com.alo7.inf.service.IVideoService;
import cn.com.alo7.inf.service.IWorkService;
import cn.com.alo7.inf.vo.DataVo;
import cn.com.alo7.inf.vo.RelationshipDataVo;
import cn.com.alo7.inf.vo.RelationshipVo;
import cn.com.alo7.inf.vo.RootVo;
import cn.com.alo7.inf.vo.UserVo;
import cn.com.alo7.inf.vo.VideoVo;
import cn.com.alo7.inf.vo.WorkVo;

/**
 * 视频controller
 * @author mazan
 */
@RestController
public class VideoController extends BaseController {
	/**
	 * 视频service
	 */
	@Autowired
	private IVideoService videoService;
	/**
	 * 作品service
	 */
	@Autowired
	private IWorkService workService;
	
	/**
	 * A03-查询单个视频信息
	 * @param videoId
	 * @return
	 */
	@GetMapping("videos/{videoId}")
	public RootVo getVideoInfo(@PathVariable Long videoId){
		//初始化
		VideoVo videoVo = null;
		DataVo<VideoVo> dataVo = null;
		//查询单个视频
		Video video = videoService.findByIdAndDeleteFlag(videoId,Constant.DELETE_FLAG_0);
		
		//转换json
		RootVo rootVo = JsonUtils.createRoot();
		videoVo = new VideoVo();
		BeanUtils.copyProperties(video, videoVo);
		
		dataVo = (DataVo<VideoVo>) JsonUtils.setData(String.valueOf(videoVo.getId()), "video", videoVo);
		
		rootVo.setData(dataVo);
		return rootVo;
	}
	
	/**
	 * A04-查询某个视频对应作品
	 * @param videoId
	 * @param size
	 * @return
	 */
	@GetMapping("videos/{videoId}/works")
	public RootVo getVideoCorresWork(@PathVariable Long videoId,
			@RequestParam(value = "size", required = false, defaultValue="20") Integer size){
		List<Work> workList = workService.findWorkByVideoId(videoId, size);
		//转换json
		RootVo rootVo = JsonUtils.createRoot();
		
		//构建Data
		WorkVo workVo = null;
		DataVo<WorkVo> dataVo = null;
		List<DataVo<WorkVo>> dataVoList = new ArrayList<DataVo<WorkVo>>();
		Map<String,Object> relationships = null;
		RelationshipVo<RelationshipDataVo> relationshipVo = null;
		RelationshipDataVo relationshipDataVo = null;
		for (Work work : workList) {
			work.setLikeCount(work.getLikeCountIn()+work.getLikeCountOut()+work.getLikeCountEdit());
			workVo = new WorkVo();
			BeanUtils.copyProperties(work, workVo);
			dataVo = (DataVo<WorkVo>) JsonUtils.setData(String.valueOf(work.getId()), "work", workVo);
			
			//构建relationships
			/*relationships = new HashMap<String,Object>();
			relationshipDataVo = new RelationshipDataVo(1L,"user");
			relationshipVo = new RelationshipVo<RelationshipDataVo>();
			relationshipVo.setData(relationshipDataVo);
			relationships.put("author", relationshipVo);
			dataVo.setRelationships(relationships);
			dataVoList.add(dataVo);*/
		}
		
		rootVo.setData(dataVoList);
		
		//构建included
		List<UserVo> userList = new ArrayList<UserVo>();
		userList.add(new UserVo("1","Gebhardt","图像url"));
		DataVo<UserVo> included = null;
		List<DataVo<UserVo>> includedList = new ArrayList<DataVo<UserVo>>();
		for (UserVo userVo : userList) {
			included = (DataVo<UserVo>) JsonUtils.setData("1","user", userVo);
			includedList.add(included);
		}
		rootVo.setIncluded(includedList);
		
		//构建meta
		Map<String,Object> meta = new HashMap<String,Object>();
		meta.put("worksNum", workList.size());
		rootVo.setMeta(meta);
		return rootVo;
	}
	
	
	/**
	 * A05-保存用户配音
	 * @param videoId
	 * @return
	 * @throws Exception 
	 */
	@PostMapping("videos/{videoId}/dub")
	@Authorization
	public RootVo saveUserWork(@PathVariable Long videoId,
			@RequestParam(value = "fileUrl", required = true) String fileUrl,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "type", required = true,defaultValue=Constant.TYPE_VOICE) String type,
			@RequestParam(value = "status", required = false,defaultValue=Constant.STATUS_UNRELEASED) String status){
		UserVo userVo = this.getUser();
		Work work = workService.saveUserWork(videoId, fileUrl, status,userVo);
		//转换json
		RootVo rootVo = JsonUtils.createRoot();
		WorkVo workVo = new WorkVo();
		BeanUtils.copyProperties(work, workVo);
		workVo.setCreatedAt(DatetimeUtils.getDatetime(work.getCreatedAt()));
		workVo.setUpdatedAt(DatetimeUtils.getDatetime(work.getUpdatedAt()));
		DataVo<WorkVo> dataVo = (DataVo<WorkVo>) JsonUtils.setData(String.valueOf(workVo.getId()), "work", workVo);
		
		//构建relationships
		Map<String,Object> relationships = new HashMap<String,Object>();
		RelationshipDataVo relationshipDataVo = new RelationshipDataVo(userVo.getId(),"user");
		RelationshipVo<RelationshipDataVo> relationshipVo = new RelationshipVo<RelationshipDataVo>();
		relationshipVo.setData(relationshipDataVo);
		relationships.put("author", relationshipVo);
		dataVo.setRelationships(relationships);
		rootVo.setData(dataVo);
		
		//构建included
		DataVo<UserVo> included = (DataVo<UserVo>) JsonUtils.setData(userVo.getId(),"user", userVo);
		rootVo.setIncluded(included);
		return rootVo;
	}
}
