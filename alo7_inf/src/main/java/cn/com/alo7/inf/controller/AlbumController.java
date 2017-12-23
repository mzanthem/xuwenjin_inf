package cn.com.alo7.inf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.alo7.inf.entity.AlbumView;
import cn.com.alo7.inf.entity.VideoView;
import cn.com.alo7.inf.service.IAlbumViewService;
import cn.com.alo7.inf.service.IVideoViewService;

@RestController
public class AlbumController {
	
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
	public String getCommonlyAlbumVideoList(@RequestParam(value="type", required=false) String type,
			@RequestParam(value="albumSize", required=false) Integer albumSize,
			@RequestParam(value="videoSize", required=false) Integer videoSize,
			@RequestParam(value="sort", required=false) String sort){
		Page<AlbumView> pageAlbumViewList = albumViewService.findByAlbumSizeAndType(albumSize, type);
		Page<VideoView> pageVideoViewList = null;
		for (AlbumView albumView : pageAlbumViewList) {
			pageVideoViewList = videoViewService.findByAlbumIdAndVideoSizeAndSort(albumView.getId(), videoSize, sort);
		}
		
		return "";
	}
	
	/**
	 * A11-查询专辑视频清单
	 * @param page
	 * @param size
	 * @param sort
	 * @return
	 */
	@GetMapping("albums/{albumId}/videos")
	@ResponseBody
	public String getAlbumVideoList(@RequestParam(value="page", required=false) Integer page,
			@RequestParam(value="size", required=false) Integer size,
			@RequestParam(value="sort", required=false) String sort){
		
		return "";
	}
	
}
