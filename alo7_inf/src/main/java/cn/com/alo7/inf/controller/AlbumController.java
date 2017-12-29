package cn.com.alo7.inf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.alo7.inf.common.Constant;
import cn.com.alo7.inf.common.utils.JsonUtils;
import cn.com.alo7.inf.common.utils.PageUtils;
import cn.com.alo7.inf.entity.AlbumView;
import cn.com.alo7.inf.entity.VideoFullView;
import cn.com.alo7.inf.entity.VideoView;
import cn.com.alo7.inf.entity.WorkFullView;
import cn.com.alo7.inf.service.IAlbumViewService;
import cn.com.alo7.inf.service.IVideoViewService;
import cn.com.alo7.inf.service.IWorkViewService;
import cn.com.alo7.inf.util.DataVoHelper;
import cn.com.alo7.inf.vo.AlbumVo;
import cn.com.alo7.inf.vo.DataVo;
import cn.com.alo7.inf.vo.RelationshipDataVo;
import cn.com.alo7.inf.vo.RelationshipListVo;
import cn.com.alo7.inf.vo.RelationshipVo;
import cn.com.alo7.inf.vo.RootVo;
import cn.com.alo7.inf.vo.VideoVo;
import cn.com.alo7.inf.vo.WorkVo;
import io.swagger.annotations.ApiOperation;

/**
 * swagger2 对于同样命名的url有个bug,只能发现一个
 * https://github.com/springfox/springfox/issues/2116
 * 
 * @author mazan
 *
 */
@RestController
public class AlbumController extends BaseController {

	/**
	 * 专辑service
	 */
	@Autowired
	private IAlbumViewService albumViewService;

	/**
	 * 视频service
	 */
	@Autowired
	private IVideoViewService videoViewService;
	
	/**
	 * 作品service
	 */
	@Autowired
	private IWorkViewService workViewService;
	/**
	 * A09-查询一般视频专辑清单 type=commonly A12-查询特殊专辑视频清单type=special
	 * 
	 * @param albumSize
	 * @param videoSize
	 * @param sort
	 * @return
	 */
	@GetMapping("albums/videos")
	public RootVo getCommonlyAlbumVideoList(@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "albumSize", required = false) Integer albumSize,
			@RequestParam(value = "videoSize", required = false) Integer videoSize,
			@RequestParam(value = "sort", required = false) String sort) {
		Page<AlbumView> pageAlbumViewList = albumViewService.findByAlbumSizeAndType(albumSize, type);
		Page<VideoView> pageVideoViewList = null;

		// 转json
		// 构建Data
		AlbumVo albumVo = null;
		VideoVo videoVo = null;
		DataVo<AlbumVo> dataVo = null;
		DataVo<VideoVo> included = null;
		List<DataVo<AlbumVo>> dataVoList = new ArrayList<DataVo<AlbumVo>>();
		List<DataVo<VideoVo>> includedList = new ArrayList<DataVo<VideoVo>>();

		Map<String, Object> relationships = null;
		RelationshipVo<RelationshipDataVo> relationshipVo = null;
		RelationshipDataVo relationshipDataVo = null;

		RootVo rootVo = JsonUtils.createRoot();
		for (AlbumView albumView : pageAlbumViewList) {
			albumVo = new AlbumVo();
			BeanUtils.copyProperties(albumView, albumVo);
			dataVo = (DataVo<AlbumVo>) JsonUtils.setData(albumView.getId(), "album", albumVo);
			pageVideoViewList = videoViewService.findByAlbumIdAndVideoSizeAndSort(albumView.getId(), videoSize, sort);
			for (VideoView videoView : pageVideoViewList) {
				// 构建relationships
				relationships = new HashMap<String, Object>();
				relationshipDataVo = new RelationshipDataVo(videoView.getId(), "video");
				relationshipVo = new RelationshipVo<RelationshipDataVo>();
				relationshipVo.setData(relationshipDataVo);
				relationships.put("video", relationshipVo);

				dataVo.setRelationships(relationships);
				dataVoList.add(dataVo);
				// 构建included
				videoVo = new VideoVo();
				BeanUtils.copyProperties(videoView, videoVo);
				included = (DataVo<VideoVo>) JsonUtils.setData(videoView.getId(), "video", videoVo);
				includedList.add(included);
			}
		}
		rootVo.setData(dataVoList);
		rootVo.setIncluded(includedList);

		return rootVo;
	}

	/**
	 * A12-查询特殊专辑视频清单 type=special
	 * albums/videos?type=special&albumVideoNum=albumVideoNum&sortType=sortType
	 * 
	 * @param albumSize
	 * @param videoSize
	 * @param sort
	 * @return
	 */
	@ApiOperation(tags = "A12", value = "A12", notes = "查询特殊专辑视频清单", httpMethod = "GET")
	@RequestMapping(name = "A12", value = "albums/videos", params = "type=special", method = RequestMethod.GET)
	public String getSpeciallbumVideoList(
			@RequestParam(value = "albumVideoNum", required = false) Integer albumVideoNum,
			@RequestParam(value = "sortType", required = false) String sortType) {

		return "special";
	}

	/**
	 * A10-查询一般作品专辑清单 
	 * 查询全部的专辑、视频、作品
	 * albums/works?type=commonly&albumSize=albumSize&videoSize=videoSize&sort=sort
	 * 
	 * @param albumSize	专辑个数 4-6  按照sort,取专辑列表
	 * @param videoSize 专辑下显示视频数目  按照sort排序，取专辑下的视频
	 * @param sort 专辑下作品排序规则
	 * @return
	 * @author mazan
	 */
	@ApiOperation(value = "A10", notes = "查询一般作品专辑清单", httpMethod = "GET")
	@RequestMapping(value = "albums/works", params = "type=commonly", method = RequestMethod.GET)
	public Object getCommonlyAlbumWorkList(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "albumSize", required = false, defaultValue = ALBUM_SIZE) Integer albumSize,
			@RequestParam(value = "videoSize", required = false, defaultValue = VIDEO_SIZE) Integer videoSize,
			@RequestParam(value = "sort", required = false, defaultValue = SORT_MANUAL) String sort) {
		// TODO
		//查找一般作品专辑
		Pageable pageable = PageUtils.build(Constant.PAGE, albumSize, sort);
		Page<AlbumView> albumPageList = this.albumViewService.findByTypeWithPage(pageable, type);
		
		List<DataVo<AlbumVo>> resultList = new ArrayList<>(); //data数组
		List<DataVo<WorkVo>> includedList = new ArrayList<>(); //include数组
		
		//为每一个专辑构造data
		DataVo<AlbumVo> dataVoAlbum;
		for (AlbumView albumView : albumPageList) {
			Long albumId = albumView.getId();
			//封装dataList
			// 构造album data
			dataVoAlbum = DataVoHelper.getInstance(albumId, "album", albumView, new AlbumVo());
			
			
			//专辑下视频
			Page<VideoVo> videoList;
			
			//视频下作品
			
			//album data 的relationShip
			
		}
		
		RootVo rootVo = JsonUtils.createRoot();
		
		return rootVo;
	}

	/**
	 * A13-查询特殊作品专辑视频清单
	 * albums/works?type=special&identifier=identifier&sort=sort&page=page&size=size		
	 * 
	 * @param identifier
	 *            特殊专辑code
	 * @param sort 专辑下视频排序 【查出来作品列表--再反查视频？】
	 * @param page 当前页数
	 * @param size 每页记录数
	 * @return
	 */
	@ApiOperation(value = "A13", notes = "查询特殊作品专辑视频清单", httpMethod = "GET")
//	@ApiImplicitParam(name = "type", value = "作品类型", required = true, paramType = "String", allowableValues = "special")
	@RequestMapping(value = "albums/works2", params = "type=special", method = RequestMethod.GET)
	public Object getSpecialAlbumWorkList(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "identifier", required = true) String identifier,
			@RequestParam(value = "sort", required = false, defaultValue = SORT_MANUAL) String sort,
			@RequestParam(value = "page", required = false, defaultValue = PAGE) Integer page,
			@RequestParam(value = "size", required = false) Integer size) {
		//根据专辑code查找专辑 【唯一】
		AlbumView albumView = this.albumViewService.findSpecialAlbumByCode(identifier);
		if (null == albumView) {
			System.out.println("album is null  ");
			return null;
		}
		Long albumId = albumView.getId();
		// 构造data
		DataVo<AlbumVo> albumDataVo = DataVoHelper.getInstance(albumId, "album", albumView, new AlbumVo());
				
				
		//翻页查询
		Pageable pageable = PageUtils.build(page, size, sort);
		Page<WorkFullView> workList = this.workViewService.findWorkByAlbumId(2L, pageable);
		
		List<Object> includedList = new ArrayList<>();
		
		RelationshipListVo<RelationshipDataVo> relationshipVoWork = new RelationshipListVo<>(true);
		DataVo<WorkVo> included;
		
		for (WorkFullView workFullView : workList) {
			Long workId = workFullView.getId();
			
			// 构建relationships
			RelationshipDataVo relationshipDataVo = new RelationshipDataVo(workId, "work"); //id,type 最里层
			relationshipVoWork.getData().add(relationshipDataVo);
			
			// 构建included data
			included = (DataVo<WorkVo>)DataVoHelper.getInstance(workId, "work", workFullView, new WorkVo());
			// 构建relationshiop author信息
			RelationshipDataVo relationshipDataVoAuthor = new RelationshipDataVo(1L, "user"); //id,type 最里层
			RelationshipVo<RelationshipDataVo> relationshipVoAuthor = new RelationshipVo<>(relationshipDataVoAuthor);
			//album下作者关联
			Map<String, Object> relationships = new HashMap<String, Object>();
			relationships.put("author", relationshipVoAuthor);
			included.setRelationships(relationships);
			
			includedList.add(included);
			
		}
		
		
		//所有作者的信息 列表[模拟]
		List<Map<String, Object>> list = this.mockUserList();
		DataVo<Map<String, Object>> userInclude;
		for (Map<String, Object> map : list) {
			userInclude = JsonUtils.setData(1L, "user", map);
			includedList.add(userInclude);
		}
		
		//album下work关联
		Map<String, Object> relationships = new HashMap<String, Object>();
		relationships.put("work", relationshipVoWork);
		albumDataVo.setRelationships(relationships);
		
		
		// root
		RootVo rootVo = JsonUtils.createRoot();
		rootVo.setData(albumDataVo);
		
		rootVo.setIncluded(includedList);
		// mata [显示所有的作品]
		Map<String, Object> meta =  this.workViewService.findWorkTotal(albumView.getId());
		
		rootVo.setMeta(meta);
		
		return rootVo;
	}

	/**
	 * A11-查询专辑视频清单
	 * 
	 * @param albumId
	 *            专辑ID
	 * @param page
	 *            专辑下视频分页-第{}页
	 * @param size
	 *            专辑下视频分页-每页{}记录
	 * @param sort
	 *            专辑下视频排序-默认DESC
	 * @return
	 * @author mazan
	 */
	@ApiOperation(value = "A11", notes = "查询专辑视频清单")
	@GetMapping("albums/{albumId}/videos")
	public Object getAlbumVideoList(@PathVariable(value = "albumId", required = true) Integer albumId,
			@RequestParam(value = "page", required = false, defaultValue = PAGE) Integer page,
			@RequestParam(value = "size", required = false, defaultValue = SIZE) Integer size,
			@RequestParam(value = "sort", required = false, defaultValue = SORT_MANUAL) String sort) {
		// 根据id查找专辑
		AlbumView albumView = this.albumViewService.findAlbumById(Long.valueOf(albumId));
		if (null == albumView) {
			System.out.println("album is null");
			return null; // 返回空的结果
		}
		
		
		// 构造data
		AlbumVo albumVo = new AlbumVo();
		BeanUtils.copyProperties(albumView, albumVo);
		DataVo<AlbumVo> albumDataVo = JsonUtils.setData(albumVo.getId(), "album", albumVo);
		
		Pageable pageable = PageUtils.build(page, size, sort);
		Page<VideoFullView> videoViewPage = this.videoViewService
				.findFullByAlbumIdAndQueryWithPage(Long.valueOf(albumId), pageable);
		
		RelationshipListVo<RelationshipDataVo> relationshipListVo = new RelationshipListVo<>(true);
		List<DataVo<VideoVo>> includedList = new ArrayList<>();
		DataVo<VideoVo> included;
		
		for (VideoFullView videoFullView : videoViewPage) {
			// 构建relationships
			RelationshipDataVo relationshipDataVo = new RelationshipDataVo(videoFullView.getId(), "video"); //id,type 最里层
			relationshipListVo.getData().add(relationshipDataVo);
			
			// 构建included
			VideoVo videoVo = new VideoVo();
			BeanUtils.copyProperties(videoFullView, videoVo);
			included = (DataVo<VideoVo>) JsonUtils.setData(videoFullView.getId(), "video", videoVo);
			includedList.add(included);
			
		}
		Map<String, Object> relationships = new HashMap<String, Object>();
		relationships.put("video", relationshipListVo);
		albumDataVo.setRelationships(relationships);
		
		
		// root
		RootVo rootVo = JsonUtils.createRoot();
		rootVo.setData(albumDataVo);
		
		rootVo.setIncluded(includedList);
		// mata [显示所有的]
		Map<String, Object> meta =  this.videoViewService.findVideoAndWorkTotal(albumView.getId());
		
		rootVo.setMeta(meta);
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
