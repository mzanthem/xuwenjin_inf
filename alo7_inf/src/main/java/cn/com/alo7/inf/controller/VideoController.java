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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cn.com.alo7.inf.common.Constant;
import cn.com.alo7.inf.common.utils.JsonUtils;
import cn.com.alo7.inf.entity.Video;
import cn.com.alo7.inf.entity.Work;
import cn.com.alo7.inf.service.IVideoService;
import cn.com.alo7.inf.vo.DataVo;
import cn.com.alo7.inf.vo.RelationshipDataVo;
import cn.com.alo7.inf.vo.RelationshipVo;
import cn.com.alo7.inf.vo.RootVo;
import cn.com.alo7.inf.vo.UserVo;
import cn.com.alo7.inf.vo.VideoVo;
import cn.com.alo7.inf.vo.WorkVo;

@RestController
public class VideoController {
	
	@Autowired
	private IVideoService videoService;
	
	/**
	 * A03-查询单个视频信息
	 * @param videoId
	 * @return
	 */
	@GetMapping("videos/{videoId}")
	@ResponseBody
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
		
		dataVo = (DataVo<VideoVo>) JsonUtils.setData(videoVo.getId(), "video", videoVo);
		
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
	@ResponseBody
	public RootVo getVideoCorresWork(@PathVariable Long videoId,
			@RequestParam(value = "size", required = false) Integer size){
		List<Work> workList = videoService.findWorkByVideoId(videoId, size);
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
			dataVo = (DataVo<WorkVo>) JsonUtils.setData(work.getId(), "work", workVo);
			
			//构建relationships
			relationships = new HashMap<String,Object>();
			relationshipDataVo = new RelationshipDataVo(1L,"user");
			relationshipVo = new RelationshipVo<RelationshipDataVo>();
			relationshipVo.setData(relationshipDataVo);
			relationships.put("author", relationshipVo);
			dataVo.setRelationships(relationships);
			dataVoList.add(dataVo);
		}
		
		rootVo.setData(dataVoList);
		
		//构建included
		List<UserVo> userList = new ArrayList<UserVo>();
		userList.add(new UserVo(1L,"Gebhardt","图像url"));
		DataVo<UserVo> included = null;
		List<DataVo<UserVo>> includedList = new ArrayList<DataVo<UserVo>>();
		for (UserVo userVo : userList) {
			included = (DataVo<UserVo>) JsonUtils.setData(1L,"user", userVo);
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
	@ResponseBody
	public String saveUserWork(@PathVariable String videoId,@RequestBody Video video){
		
		return "";
	}
}