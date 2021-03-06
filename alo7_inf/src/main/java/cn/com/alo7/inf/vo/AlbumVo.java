package cn.com.alo7.inf.vo;

import cn.com.alo7.inf.entity.base.BaseEntity;

public class AlbumVo extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1944257133801444332L;

	private Long id;
	private String name;
	private String type;
	private String specialType;
	private String specialTypeCode;
	private String specialTypeName;
	private String specialTypeDescription;
	private String backgroundImgUrl;
	private String description;
	private String status;
	private Long position;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSpecialType() {
		return specialType;
	}
	public void setSpecialType(String specialType) {
		this.specialType = specialType;
	}
	public String getSpecialTypeCode() {
		return specialTypeCode;
	}
	public void setSpecialTypeCode(String specialTypeCode) {
		this.specialTypeCode = specialTypeCode;
	}
	public String getSpecialTypeName() {
		return specialTypeName;
	}
	public void setSpecialTypeName(String specialTypeName) {
		this.specialTypeName = specialTypeName;
	}
	public String getSpecialTypeDescription() {
		return specialTypeDescription;
	}
	public void setSpecialTypeDescription(String specialTypeDescription) {
		this.specialTypeDescription = specialTypeDescription;
	}
	public String getBackgroundImgUrl() {
		return backgroundImgUrl;
	}
	public void setBackgroundImgUrl(String backgroundImgUrl) {
		this.backgroundImgUrl = backgroundImgUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getPosition() {
		return position;
	}
	public void setPosition(Long position) {
		this.position = position;
	}
}
