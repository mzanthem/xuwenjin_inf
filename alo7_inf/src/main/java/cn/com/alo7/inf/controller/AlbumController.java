package cn.com.alo7.inf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.alo7.inf.common.Constant;
import cn.com.alo7.inf.common.utils.DataVoHelper;
import cn.com.alo7.inf.common.utils.JsonUtils;
import cn.com.alo7.inf.common.utils.PageUtils;
import cn.com.alo7.inf.common.utils.RelationShipVoHelper;
import cn.com.alo7.inf.entity.AlbumFullView;
import cn.com.alo7.inf.entity.AlbumView;
import cn.com.alo7.inf.entity.VideoFullView;
import cn.com.alo7.inf.entity.VideoView;
import cn.com.alo7.inf.entity.WorkFullView;
import cn.com.alo7.inf.service.IAlbumViewService;
import cn.com.alo7.inf.service.IVideoViewService;
import cn.com.alo7.inf.service.IWorkViewService;
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
 * @author Administrator
 *
 */
@RestController
public class AlbumController extends BaseController {

	@Autowired
	private IAlbumViewService albumViewService;

	@Autowired
	private IVideoViewService videoViewService;
	
	/**
	 * 作品service
	 */
	@Autowired
	private IWorkViewService workViewService;
	
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
	public RootVo getCommonlyAlbumVideoList(@RequestParam(value="type", required=false,defaultValue="commonly") String type,
			@RequestParam(value="albumSize", required=false, defaultValue="4") Integer albumSize,
			@RequestParam(value="videoSize", required=false, defaultValue="4") Integer videoSize,
			@RequestParam(value="sort", required=false, defaultValue="manual") String sort){
		Page<AlbumView> pageAlbumViewList = albumViewService.findByAlbumSizeAndSpecialType(albumSize, type);
		Page<VideoView> pageVideoViewList = null;
		System.out.println(pageAlbumViewList.getContent());
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
		for (AlbumView albumView : pageAlbumViewList.getContent()) {
			albumVo = new AlbumVo();
			BeanUtils.copyProperties(albumView, albumVo);
			dataVo = (DataVo<AlbumVo>) JsonUtils.setData(String.valueOf(albumView.getId()), "album", albumVo);
			pageVideoViewList = videoViewService.findByAlbumIdAndVideoSizeAndSort(albumView.getId(), videoSize, sort);
			for (VideoView videoView : pageVideoViewList.getContent()) {
				//构建relationships
				relationships = new HashMap<String,Object>();
				relationshipDataVo = new RelationshipDataVo(String.valueOf(videoView.getId()),"video");
				relationshipVo = new RelationshipVo<RelationshipDataVo>();
				relationshipVo.setData(relationshipDataVo);
				relationships.put("video", relationshipVo);
				dataVo.setRelationships(relationships);
				dataVoList.add(dataVo);
				//构建included
				videoVo = new VideoVo();
				BeanUtils.copyProperties(videoView, videoVo);
				included = (DataVo<VideoVo>) JsonUtils.setData(String.valueOf(videoView.getId()),"video", videoVo);
				includedList.add(included);
			}
		}
		rootVo.setData(dataVoList);
		rootVo.setIncluded(includedList);
		
		return rootVo;
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
		//查找一般作品专辑
		Pageable pageable = PageUtils.build(Constant.PAGE, albumSize);
		Page<AlbumFullView> albumPageList = this.albumViewService.findPageByTypeAndSpecialType(pageable, null, Constant.ALBUM_SPECIAL_TYPE_COMMON);
		
		List<DataVo<AlbumVo>> dataList = new ArrayList<>(); //data数组
		List<Object> includedList = new ArrayList<>(); //include数组
		
		//为每一个专辑构造data
		DataVo<AlbumVo> albumDataVo;
		for (AlbumFullView albumFullView : albumPageList) {
			Long albumId = albumFullView.getId();
			//封装dataList
			albumDataVo = DataVoHelper.getInstance(albumId, "album", albumFullView, new AlbumVo());
			
			//专辑下作品
			Pageable pageableWork = PageUtils.build(Constant.PAGE, Constant.SIZE, sort);
			Page<WorkFullView> workVoList = this.workViewService.findWorkByAlbumId(albumId, pageableWork);
			
			//专辑下作品ship 初始化List<T>
			RelationshipListVo<RelationshipDataVo> relationshipListVoWork = new RelationshipListVo<>(true);
			DataVo<WorkVo> includedWork;
			
			Set<String> userSet = new HashSet<>();
			//album data 的relationShip
			for (WorkFullView workFullView : workVoList) {
				Long workId = workFullView.getId();
				// 构建relationships
				relationshipListVoWork.addData(new RelationshipDataVo(workId.toString(), "work"));
				
				// 构建included data
				includedWork = DataVoHelper.getInstance(workId, "work", workFullView, new WorkVo());
				//album下作者关联
				String uuid = workFullView.getUuid();
				includedWork.setRelationships(RelationShipVoHelper.buildRelationships("author", uuid, "user"));
				includedList.add(includedWork);
				//用户信息
				userSet.add(uuid);
			} //end for j
			
			//TODO 所有作者的信息 列表[模拟]
			List<Map<String, Object>> list = this.mockUserList(userSet);
			DataVo<Map<String, Object>> includedUser;
			for (Map<String, Object> map : list) {
				includedUser = DataVoHelper.getInstance(map.get("id"), "user", map);
				includedList.add(includedUser);
			}
			//album下work关联
			albumDataVo.setRelationships(RelationShipVoHelper.buildRelationships("work", relationshipListVoWork));
			dataList.add(albumDataVo);
			
		} //end for i
		
		
		// root
		RootVo rootVo = JsonUtils.createRoot(dataList, includedList);
		
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
	@RequestMapping(value = "albums/works2", params = "type=special", method = RequestMethod.GET)
	public Object getSpecialAlbumWorkList(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "identifier", required = true) String identifier,
			@RequestParam(value = "sort", required = false, defaultValue = SORT_MANUAL) String sort,
			@RequestParam(value = "page", required = false, defaultValue = PAGE) Integer page,
			@RequestParam(value = "size", required = false, defaultValue = SIZE) Integer size) {
		//根据专辑code查找专辑 【唯一】
		AlbumFullView albumFullView = this.albumViewService.findSpecialAlbumByCode(identifier);
		if (null == albumFullView) {
			System.out.println("album is null  ");
			return null;
		}
		Long albumId = albumFullView.getId();
		// 构造data
		DataVo<AlbumVo> dataVoAlbum = DataVoHelper.getInstance(albumId, "album", albumFullView, new AlbumVo());
		//翻页查询
		Pageable pageable = PageUtils.build(page, size, sort);
		Page<WorkFullView> workList = this.workViewService.findWorkByAlbumId(albumId, pageable);
		
		//included 包含work 和user
		List<Object> includedList = new ArrayList<>();
		//work list
		RelationshipListVo<RelationshipDataVo> relationshipListVoWork = new RelationshipListVo<>(true);
		DataVo<WorkVo> included;
		//作者信息
		Set<String> userSet = new HashSet<>();
		
		for (WorkFullView workFullView : workList) {
			Long workId = workFullView.getId();
			
			// 构建relationships
			RelationshipDataVo relationshipDataVo = new RelationshipDataVo(workId.toString(), "work"); //id,type 最里层
			relationshipListVoWork.addData(relationshipDataVo);
			
			// 构建included data
			included = DataVoHelper.getInstance(workId, "work", workFullView, new WorkVo());
			//album下作者关联
			String uuid = workFullView.getUuid();
			
			included.setRelationships(RelationShipVoHelper.buildRelationships("author", uuid, "user"));
			includedList.add(included);
			
			userSet.add(uuid);
			//根据uuid查找用户信息
		}
		//album下work关联
		dataVoAlbum.setRelationships(RelationShipVoHelper.buildRelationships("work", relationshipListVoWork));
				
		//TODO 根据作品的作者所有作者的信息 列表[模拟]
		List<Map<String, Object>> list = this.mockUserList(userSet);
		DataVo<Map<String, Object>> userInclude;
		for (Map<String, Object> map : list) {
			userInclude = DataVoHelper.getInstance(map.get("id"), "user", map);
			includedList.add(userInclude);
		}
		
		
		// mata [显示所有的作品]
		Map<String, Object> meta =  this.workViewService.findWorkTotal(albumFullView.getId());
		
		RootVo rootVo = JsonUtils.createRoot(dataVoAlbum, includedList, meta);
		
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
		AlbumFullView albumFullView = this.albumViewService.findFullAlbumByIdAndStatus(Long.valueOf(albumId), Constant.STATUS_UP);
		if (null == albumFullView) {
			System.out.println("album is null");
			return null; // 返回空的结果
		}
		// 构造data
		DataVo<AlbumVo> dataVoAlbum = DataVoHelper.getInstance(albumFullView.getId(), "album", albumFullView, new AlbumVo());
		
		Pageable pageable = PageUtils.build(page, size, sort);
		Page<VideoFullView> videoViewPage = this.videoViewService
				.findFullByAlbumIdAndQueryWithPage(Long.valueOf(albumId), pageable);
		
		// data-relationShip list
		RelationshipListVo<RelationshipDataVo> relationshipListVo = new RelationshipListVo<>(true);
		
		List<DataVo<VideoVo>> includedList = new ArrayList<>();
		DataVo<VideoVo> included;
		
		for (VideoFullView videoFullView : videoViewPage) {
			// 构建relationships
			RelationshipDataVo relationshipDataVo = new RelationshipDataVo(videoFullView.getId().toString(), "video"); //id,type 最里层
			relationshipListVo.addData(relationshipDataVo);
			
			// 构建included
			included = DataVoHelper.getInstance(videoFullView.getId(), "video", videoFullView, new VideoVo());
			includedList.add(included);
		}
		
		dataVoAlbum.setRelationships(RelationShipVoHelper.buildRelationships("video", relationshipListVo));
		
		// mata [显示所有的]
		Map<String, Object> meta =  this.videoViewService.findVideoAndWorkTotal(albumFullView.getId());
		
		RootVo rootVo = JsonUtils.createRoot(dataVoAlbum, includedList, meta);
		return rootVo;
	}

	/**
	 * 模拟作品作者
	 * @return
	 */
	private List<Map<String, Object>> mockUserList(Set<String> userSet) {
		
		List<Map<String, Object>> list = new ArrayList<>(userSet.size());
		for (String uuid : userSet) {
			list.add(mockUser(uuid));
		}
		return list;
	}
	/**
	 * 模拟作品作者
	 * @return
	 */
	private Map<String, Object> mockUser(String uuid) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", uuid);
		map.put("avatarUrl", "图像Url");
		map.put("name", "Gebhardt");
		return map;
	}
}
