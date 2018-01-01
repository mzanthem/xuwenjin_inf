package cn.com.alo7.inf.service;

import java.util.List;

import cn.com.alo7.inf.vo.UserVo;

/**
 * 用户 信息 相关service
 * @author mazan
 *
 */
public interface IUserService {
	
	/**
	 * 根据 access_token获取用户信息
	 * @param accessToken
	 * @return
	 */
	public UserVo getUserInfoByAccessToken(String accessToken);
	
	/**
	 * 根据 uuid获取用户信息
	 * @param uuids
	 * @return
	 */
	public List<UserVo> getUserInfoByUUID(String uuids);
}
