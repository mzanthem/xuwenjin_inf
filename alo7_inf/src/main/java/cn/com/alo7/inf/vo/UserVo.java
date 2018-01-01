package cn.com.alo7.inf.vo;

import java.io.Serializable;

public class UserVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String avatarUrl;
	
	public UserVo() {
		super();
	}
	
	public UserVo(String id, String name, String avatarUrl) {
		super();
		this.id = id;
		this.name = name;
		this.avatarUrl = avatarUrl;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	
}
