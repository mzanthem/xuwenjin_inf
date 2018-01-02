package cn.com.alo7.inf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.alo7.inf.common.utils.DataVoHelper;
import cn.com.alo7.inf.common.utils.JsonUtils;
import cn.com.alo7.inf.entity.Video;
import cn.com.alo7.inf.entity.Work;
import cn.com.alo7.inf.entity.WorkFullView;
import cn.com.alo7.inf.service.IWorkService;
import cn.com.alo7.inf.service.IWorkViewService;
import cn.com.alo7.inf.vo.DataVo;
import cn.com.alo7.inf.vo.RelationshipDataVo;
import cn.com.alo7.inf.vo.RootVo;
import cn.com.alo7.inf.vo.VideoVo;
import cn.com.alo7.inf.vo.WorkVo;
import io.swagger.annotations.ApiOperation;

@RestController
public class WorkController extends BaseController {
	
	@Autowired
	private IWorkService workService;
	
	/**
	 * 作品视图service
	 */
	@Autowired
	private IWorkViewService workViewService;
	/**
	 * A17-查询S3保存路径
	 * @param videoId
	 * @return
	 * @throws Exception 
	 */
	@GetMapping("work/s3url")
	public String columns(@RequestParam(value="fileName", required=false) String fileName,
			@RequestParam(value="fileExtension", required=false) String fileExtension){
		
		return "";
	}
	
	/**
	 * A06_A07-查询点赞数
	 * @param videoId
	 * @return
	 * @throws Exception 
	 */
	@GetMapping("works/{workId}/likes")
	public RootVo getLikes(@PathVariable Long workId){
		Work work = workService.findById(workId);
		RootVo rootVo = JsonUtils.createRoot();
		Map<String,Object> meta = new HashMap<String,Object>();
		meta.put("likeCount", work.getLikeCountIn()+work.getLikeCountOut()+work.getLikeCountEdit());
		rootVo.setMeta(meta);
		
		return rootVo;
	}
	
	/**
	 * TODO 日期转换问题
	 * A19-获取一个作品的所有信息
	 * @return
	 */
	@ApiOperation(value = "A19", notes = "获取一个作品的所有信息")
	@GetMapping("works/{workId}")
	public Object getWorkInfo(@PathVariable Long workId) {
		
		WorkFullView workFull = this.workViewService.findById(workId);
		if(null == workFull) {
			System.out.println("work is not exist");
			return null;
		}
		
		
		//构建dataVo
		DataVo<WorkVo> dataVoWork = DataVoHelper.getInstance(workId, "work", workFull, new WorkVo());
		
		// videoData
		Video video = workFull.getVideo();
		RelationshipDataVo relationshipVideo = new RelationshipDataVo(video.getId().toString(), "video");
		// authorData
		List<Map<String, Object>> userList = this.mockUserList();
		Map<String, Object> user = userList.get(0);
		RelationshipDataVo relationshipAuthor = new RelationshipDataVo(workFull.getUuid(), "user");
		
		//构建data-relationships
		Map<String, Object> relationships = new HashMap<>();
		relationships.put("video", relationshipVideo);
		relationships.put("author", relationshipAuthor);
		dataVoWork.setRelationships(relationships);
		
		//构建included
		List<DataVo<?>> includedList = new ArrayList<>();
		DataVo<VideoVo> includeVideo = DataVoHelper.getInstance(video.getId(), "video", video, new VideoVo());
		DataVo<Map<String, Object>> includeUser = DataVoHelper.getInstance(workFull.getUuid(), "user", user);
		includedList.add(includeVideo);
		includedList.add(includeUser);
		
		//构建rootVo
		RootVo rootVo = JsonUtils.createRoot();
		rootVo.setData(dataVoWork);
		rootVo.setIncluded(includedList);
		return rootVo;
	}
	
	
	/**
	 * 模拟作品作者
	 * @return
	 */ 
	private List<Map<String, Object>> mockUserList() {
		Map<String, Object> map = new HashMap<>();
		map.put("avatarUrl", "图像Url");
		map.put("name", "Gebhardt");
		
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(map);
		return list;
		
	}
}
