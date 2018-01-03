package cn.com.alo7.inf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.alo7.inf.common.Authorization;
import cn.com.alo7.inf.common.utils.DataVoHelper;
import cn.com.alo7.inf.common.utils.JsonUtils;
import cn.com.alo7.inf.common.utils.PageUtils;
import cn.com.alo7.inf.common.utils.RelationShipVoHelper;
import cn.com.alo7.inf.entity.WorkFullView;
import cn.com.alo7.inf.service.IWorkViewService;
import cn.com.alo7.inf.vo.DataVo;
import cn.com.alo7.inf.vo.RelationshipDataVo;
import cn.com.alo7.inf.vo.RelationshipVo;
import cn.com.alo7.inf.vo.RootVo;
import cn.com.alo7.inf.vo.UserVo;
import cn.com.alo7.inf.vo.WorkVo;
import io.swagger.annotations.ApiOperation;

@RestController
public class UserController extends BaseController {
	
	/**
	 * 作品service
	 */
	@Autowired
	private IWorkViewService workViewService;
	
	/**
	 * 根据token获取用户信息
	 * @return
	 */
	@GetMapping("users/info")
	@Authorization
	public RootVo getUserInfoByAccessToken(){
		//accessToken 86828360c690013575440c4de9bf6bbe
		UserVo userVo = this.getUser();
		//转换json
		RootVo rootVo = JsonUtils.createRoot();
		DataVo<UserVo> dataVo = (DataVo<UserVo>) JsonUtils.setData(userVo.getId(), "user", userVo);
		rootVo.setData(dataVo);
		
		return rootVo;
	}
	
	/**
	 * 根据uuid获取用户信息
	 * @return
	 */
	/*@GetMapping("users")
	public List<UserVo> getUserInfoByUUID(){
		String uuids = "77501336";
		String str = restTemplate.getForObject("http://account-api.dev.saybot.net/api/v1/open/users?uuids[]={uuids}",String.class,uuids);
		List<UserVo> userVoList = new ArrayList<UserVo>();
		UserVo userVo = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(str);
			JsonNode datas = node.get("data");
			JsonNode data = null;
			for(int i=0;i<datas.size();i++){
				userVo = new UserVo();
				data = datas.get(i);
				userVo.setId(data.get("id").textValue());
				JsonNode attributes = data.get("attributes");
				userVo.setName(attributes.get("displayName").textValue());
				JsonNode avatars = attributes.get("avatar");
				userVo.setAvatarUrl(avatars.get("origin").textValue());
				userVoList.add(userVo);
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userVoList;
		return null;
	}*/
	
	
	
	/**
	 * users/{uuid}/works?page=page&size=size
	 * A15-查询某用户作品清单
	 * @param uuid 用户id
	 * @param page 翻页
	 * @param size 每页记录
	 * @return
	 */
	@ApiOperation(value = "A15", notes = "查询某用户作品清单")
	@GetMapping("users/{uuid}/works")
	public Object getUserWorks(@PathVariable String uuid,
			@RequestParam(value="page", required=false, defaultValue = PAGE) Integer page,
			@RequestParam(value="size", required=false, defaultValue = SIZE) Integer size
			) {
		
		//判断当前用户
		boolean isCurrentUser = checkCurrentUser(uuid);
		
		
		Pageable pageable = PageUtils.build(page, size);
		Page<WorkFullView> result = this.workViewService.findWorkByUserId(uuid, isCurrentUser, pageable);
		
		
		List<DataVo<WorkVo>> dataList = new ArrayList<>();
		DataVo<WorkVo> dataVoWork;
		for (WorkFullView workFullView : result) {
			Long workId = workFullView.getId();
			dataVoWork = DataVoHelper.getInstance(workId, "work", workFullView, new WorkVo());
			//userRelationShipData
			RelationshipVo<RelationshipDataVo> relationshipVo = RelationShipVoHelper.getInstance(uuid, "user");
			Map<String, Object> relationship = new HashMap<>();
			relationship.put("author", relationshipVo);
			dataVoWork.setRelationships(relationship);
			
			dataList.add(dataVoWork);
		}
		//构造include
		List<Map<String, Object>> userList = this.mockUserList();
		List<DataVo<?>> included = new ArrayList<>();
		DataVo<?> dataVoUser;
		for (Map<String, Object> map : userList) {
			
			dataVoUser = DataVoHelper.getInstance(uuid, "user", map);
			included.add(dataVoUser);
		}
		//构造meta
		
		Map<String, Object> workNum = this.workViewService.findWorkTotalbyUserId(uuid);
		
		RootVo root = JsonUtils.createRoot();
		root.setData(dataList);
		root.setIncluded(included);
		root.setMeta(workNum);
		
		return root;
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
