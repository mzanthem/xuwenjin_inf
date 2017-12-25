package cn.com.alo7.inf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.alo7.inf.common.utils.JsonUtils;
import cn.com.alo7.inf.entity.AlbumView;
import cn.com.alo7.inf.entity.VideoView;
import cn.com.alo7.inf.service.IAlbumViewService;
import cn.com.alo7.inf.service.IVideoViewService;
import cn.com.alo7.inf.vo.AlbumVo;
import cn.com.alo7.inf.vo.DataVo;
import cn.com.alo7.inf.vo.RelationshipDataVo;
import cn.com.alo7.inf.vo.RelationshipVo;
import cn.com.alo7.inf.vo.RootVo;
import cn.com.alo7.inf.vo.VideoVo;

@RestController
public class AlbumController extends BaseController {

	@Autowired
	private IAlbumViewService albumViewService;

	@Autowired
	private IVideoViewService videoViewService;

	/**
	 * A09-查询一般视频专辑清单 type=commonly
	 * A12-查询特殊专辑视频清单type=special
	 * @param albumSize
	 * @param videoSize
	 * @param sort
	 * @return
	 */
	@GetMapping("albums/videos")
	@ResponseBody
	public RootVo getCommonlyAlbumVideoList(@RequestParam(value="type", required=false) String type,
			@RequestParam(value="albumSize", required=false) Integer albumSize,
			@RequestParam(value="videoSize", required=false) Integer videoSize,
			@RequestParam(value="sort", required=false) String sort){
		Page<AlbumView> pageAlbumViewList = albumViewService.findByAlbumSizeAndType(albumSize, type);
		Page<VideoView> pageVideoViewList = null;
		
		//转json
		//构建Data
		AlbumVo albumVo = null;
		VideoVo videoVo = null;
		DataVo<AlbumVo> dataVo = null;
		DataVo<VideoVo> included = null;
		List<DataVo<AlbumVo>> dataVoList = new ArrayList<DataVo<AlbumVo>>();
		List<DataVo<VideoVo>> includedList = new ArrayList<DataVo<VideoVo>>();

		Map<String,Object> relationships = null;
		RelationshipVo<RelationshipDataVo> relationshipVo = null;
		RelationshipDataVo relationshipDataVo = null;
		
		RootVo rootVo = JsonUtils.createRoot();
		for (AlbumView albumView : pageAlbumViewList) {
			albumVo = new AlbumVo();
			BeanUtils.copyProperties(albumView, albumVo);
			dataVo = (DataVo<AlbumVo>) JsonUtils.setData(albumView.getId(), "album", albumVo);
			pageVideoViewList = videoViewService.findByAlbumIdAndVideoSizeAndSort(albumView.getId(), videoSize, sort);
			for (VideoView videoView : pageVideoViewList) {
				//构建relationships
				relationships = new HashMap<String,Object>();
				relationshipDataVo = new RelationshipDataVo(videoView.getId(),"video");
				relationshipVo = new RelationshipVo<RelationshipDataVo>();
				relationshipVo.setData(relationshipDataVo);
				relationships.put("video", relationshipVo);
				dataVo.setRelationships(relationships);
				dataVoList.add(dataVo);
				//构建included
				videoVo = new VideoVo();
				BeanUtils.copyProperties(videoView, videoVo);
				included = (DataVo<VideoVo>) JsonUtils.setData(videoView.getId(),"video", videoVo);
				includedList.add(included);
			}
		}
		rootVo.setData(dataVoList);
		rootVo.setIncluded(includedList);
		
		return rootVo;
	}

	/**
	 * A10-查询一般作品专辑清单
	 * 
	 * @param albumSize
	 * @param videoSize
	 * @param sort
	 * @return
	 * @author mazan
	 */
	@GetMapping(value = "albums/works")
	public String getCommonlyAlbumWorkList(
			@RequestParam(value = "albumSize", required = false, defaultValue = ALBUM_SIZE) Integer albumSize,
			@RequestParam(value = "videoSize", required = false, defaultValue = VIDEO_SIZE) Integer videoSize,
			@RequestParam(value = "sort", required = false, defaultValue = SORT) String sort) {
		// TODO
		Map<String, Object> map = new HashMap<>();
		map.put("arg1", new Long(1000));
		map.put("arg2", "Mz");

		return JsonUtils.toJson(map);
	}

	/**
	 * A11-查询专辑视频清单
	 * 
	 * @param page
	 * @param size
	 * @param sort
	 * @return
	 * @author mazan
	 */
	@GetMapping("albums/{albumId}/videos")
	@ResponseBody
	public String getAlbumVideoList(@RequestParam(value = "page", required = false, defaultValue = PAGE) Integer page,
			@RequestParam(value = "size", required = false, defaultValue = SIZE) Integer size,
			@RequestParam(value = "sort", required = false, defaultValue = SORT) String sort) {
		// TODO
		return "";
	}

}
