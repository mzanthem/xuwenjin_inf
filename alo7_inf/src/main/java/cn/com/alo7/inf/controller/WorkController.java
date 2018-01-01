package cn.com.alo7.inf.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.alo7.inf.common.utils.JsonUtils;
import cn.com.alo7.inf.entity.Work;
import cn.com.alo7.inf.service.IWorkService;
import cn.com.alo7.inf.vo.RootVo;

@RestController
public class WorkController extends BaseController {
	
	@Autowired
	private IWorkService workService;
	
	/**
	 * A17-查询S3保存路径
	 * @param videoId
	 * @return
	 * @throws Exception 
	 */
	@GetMapping("work/s3url")
	@ResponseBody
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
	@ResponseBody
	public RootVo getLikes(@PathVariable Long workId){
		Work work = workService.findById(workId);
		RootVo rootVo = JsonUtils.createRoot();
		Map<String,Object> meta = new HashMap<String,Object>();
		meta.put("likeCount", work.getLikeCountIn()+work.getLikeCountOut()+work.getLikeCountEdit());
		rootVo.setMeta(meta);
		
		return rootVo;
	}
	
	
	/**
	 * TODO 
	 * A19
	 * @return
	 */
	public Object getWork() {
		
		return null;
	}
}
