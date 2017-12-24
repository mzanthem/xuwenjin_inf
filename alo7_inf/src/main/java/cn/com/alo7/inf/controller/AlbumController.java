package cn.com.alo7.inf.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.alo7.inf.common.utils.JsonUtils;
import cn.com.alo7.inf.entity.AlbumView;
import cn.com.alo7.inf.entity.VideoView;
import cn.com.alo7.inf.service.IAlbumViewService;
import cn.com.alo7.inf.service.IVideoViewService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * swagger2 对于同样命名的url有个bug,只能发现一个
 * https://github.com/springfox/springfox/issues/2116
 * @author mazan
 *
 */
@RestController
public class AlbumController extends BaseController {

	@Autowired
	private IAlbumViewService albumViewService;

	@Autowired
	private IVideoViewService videoViewService;

	/**
	 * A09-查询一般视频专辑清单 type=commonly 
	 * albums/videos?type=commonly&albumlimit=albumlimit&videolimit=videolimit&sortType=sortType		
	 * @param albumSize
	 * @param videoSize
	 * @param sort
	 * @return
	 */
	@ApiOperation(tags = "9", value="A09", notes="查询一般视频专辑清单", httpMethod = "GET")
	@RequestMapping(name = "A09", value = "albums/videos", params = "type=commonly", method = RequestMethod.GET)
	public String getCommonlyAlbumVideoList(
			@RequestParam(value = "albumlimit", required = false) Integer albumSize,
			@RequestParam(value = "videolimit", required = false) Integer videoSize,
			@RequestParam(value = "sortType", required = false, defaultValue = SORT) String sort) {
		Page<AlbumView> pageAlbumViewList = albumViewService.findByAlbumSizeAndType(albumSize, "commonly");
		Page<VideoView> pageVideoViewList = null;
		for (AlbumView albumView : pageAlbumViewList) {
			pageVideoViewList = videoViewService.findByAlbumIdAndVideoSizeAndSort(albumView.getId(), videoSize, sort);
		}

		return "commonly";
	}

	/**
	 * A12-查询特殊专辑视频清单 type=special
	 * albums/videos?type=special&albumVideoNum=albumVideoNum&sortType=sortType		
	 * @param albumSize
	 * @param videoSize
	 * @param sort
	 * @return
	 */
	@ApiOperation(tags = "9", value="A12", notes="查询特殊专辑视频清单", httpMethod = "GET")
	@RequestMapping(name = "A12", value = "albums/videos", params = "type=special", method = RequestMethod.GET)
	public String getSpeciallbumVideoList(
			@RequestParam(value = "albumVideoNum", required = false) Integer albumVideoNum,
			@RequestParam(value = "sortType", required = false) String sortType) {

		return "special";
	}
	/**
	 * A10-查询一般作品专辑清单
	 * albums/works?type=commonly&albumlimit=albumlimit&videolimit=videolimit&sortType=sortType
	 * @param albumSize
	 * @param videoSize
	 * @param sort
	 * @return
	 * @author mazan
	 */
	@ApiOperation(value="A10", notes="查询一般作品专辑清单", httpMethod = "GET")
	@ApiImplicitParam(name = "type", value = "作品类型", required = true, paramType = "String", allowableValues = "commonly")
	@RequestMapping(value = "albums/works", params = "type=commonly", method = RequestMethod.GET)
	public String getCommonlyAlbumWorkList(
			@RequestParam(value = "albumlimit", required = false, defaultValue = ALBUM_SIZE) Integer albumSize,
			@RequestParam(value = "videolimit", required = false, defaultValue = VIDEO_SIZE) Integer videoSize,
			@RequestParam(value = "sortType", required = false, defaultValue = SORT) String sort) {
		// TODO
		Map<String, Object> map = new HashMap<>();
		map.put("arg1", new Long(1000));
		map.put("arg2", "Mz");

		return JsonUtils.toJson(map);
	}
	/**
	 * A13-查询特殊作品专辑视频清单					
	 * albums/works?type=special&identifier=identifier&sortType=sortType&start=start&limit=limit		
	 * @param identifier 特殊专辑code
	 * @param sortType
	 * @param start
	 * @param limit
	 * @return
	 */
	@ApiOperation(value="A13", notes="查询特殊作品专辑视频清单", httpMethod = "GET")
	@ApiImplicitParam(name = "type", value = "作品类型", required = true, paramType = "String", allowableValues = "special")
	@RequestMapping(value = "albums/works", params = "type=special", method = RequestMethod.GET)
	public String getSpecialAlbumWorkList(
			@RequestParam(value = "identifier", required = true) String identifier,
			@RequestParam(value = "sortType", required = false, defaultValue = SORT) String sortType,
			@RequestParam(value = "start", required = false, defaultValue = START) Integer start,
			@RequestParam(value = "limit", required = false) Integer limit) {
		// TODO
		Map<String, Object> map = new HashMap<>();
		map.put("arg1", new Long(1000));
		map.put("arg2", "Mz2");

		return JsonUtils.toJson(map);
	}
	/**
	 * A11-查询专辑视频清单
	 * @param albumId
	 * @param page
	 * @param size
	 * @param sort
	 * @return
	 * @author mazan
	 */
	@ApiOperation(value="A11", notes="查询专辑视频清单")
	@GetMapping("albums/{albumId}/videos")
	public String getAlbumVideoList(
			@PathVariable(value = "albumId", required =true) String albumId,
			@RequestParam(value = "page", required = false, defaultValue = PAGE) Integer page,
			@RequestParam(value = "size", required = false, defaultValue = SIZE) Integer size,
			@RequestParam(value = "sort", required = false, defaultValue = SORT) String sort) {
		// TODO
		return  albumId;
	}



}
