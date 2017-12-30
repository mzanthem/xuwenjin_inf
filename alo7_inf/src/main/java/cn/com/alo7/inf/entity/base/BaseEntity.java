package cn.com.alo7.inf.entity.base;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.MappedSuperclass;

/**
 * baseEntity 通用字段
 * @author mazan
 *
 */
@MappedSuperclass
public class BaseEntity implements Serializable{

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
	private Timestamp createdAt;
	 
	/**
	 * 更新人
	 */
	private String updatedBy;
	 
	/**
	 * 更新时间
	 */
	private Timestamp updatedAt;
	  
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

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
