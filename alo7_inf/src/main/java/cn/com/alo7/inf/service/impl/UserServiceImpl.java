package cn.com.alo7.inf.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.alo7.inf.common.Constant;
import cn.com.alo7.inf.service.IUserService;
import cn.com.alo7.inf.vo.UserVo;

/**
 * 用户 信息
 * 
 * @author xuwenjin
 *
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public UserVo getUserInfoByAccessToken(String accessToken) {
		UserVo userVo = null;
		try {
			String json = restTemplate.getForObject(Constant.USER_TOKEN,String.class,accessToken);
			userVo = new UserVo();
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			JsonNode datas = node.get("data");
			userVo.setId(datas.get("id").textValue());
			JsonNode attributes = datas.get("attributes");
			userVo.setName(attributes.get("displayName").textValue());
			JsonNode avatars = attributes.get("avatar");
			userVo.setAvatarUrl(avatars.get("origin").textValue());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return new UserVo();
		} catch (IOException e) {
			e.printStackTrace();
			return new UserVo();
		}
		
		return userVo;
	}

	@Override
	public List<UserVo> getUserInfoByUUID(String uuids) {
		List<UserVo> userVoList = null;
		try {
			String json = restTemplate.getForObject(Constant.USER_ID_URL,String.class,uuids);
			userVoList = new ArrayList<UserVo>();
			UserVo userVo = null;
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
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
			e.printStackTrace();
			return new ArrayList<UserVo>();
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<UserVo>();
		}
		return userVoList;
	}
}
