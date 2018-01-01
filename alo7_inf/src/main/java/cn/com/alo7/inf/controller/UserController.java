package cn.com.alo7.inf.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.alo7.inf.common.Authorization;
import cn.com.alo7.inf.common.utils.JsonUtils;
import cn.com.alo7.inf.vo.DataVo;
import cn.com.alo7.inf.vo.RootVo;
import cn.com.alo7.inf.vo.UserVo;

@RestController
public class UserController extends BaseController {
	
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
}
