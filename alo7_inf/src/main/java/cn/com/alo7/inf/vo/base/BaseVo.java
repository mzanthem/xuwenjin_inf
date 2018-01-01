package cn.com.alo7.inf.vo.base;

import java.io.Serializable;

public class BaseVo implements Serializable{
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 删除标识  
	 */
	private String deleteFlag;
	
	/**
	 * 创建人
	 */
	private String createdBy;
	
	/**
	 * 创建时间
	 */
	private String createdAt;
	 
	/**
	 * 更新人
	 */
	private String updatedBy;
	 
	/**
	 * 更新时间
	 */
	private String updatedAt;
	  
	/**
	 * 版本号
	 */
	private String version;

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
