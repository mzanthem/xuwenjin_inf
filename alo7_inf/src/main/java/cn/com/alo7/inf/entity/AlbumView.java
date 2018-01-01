package cn.com.alo7.inf.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.alo7.inf.entity.base.BaseEntity;

/**
 * 专辑视图
 * @author mazan
 *
 */
@Entity
@Table(name = "v_a09_album")
public class AlbumView extends BaseEntity{
	/**
	 * UID
	 */
	private static final long serialVersionUID = -1944257133801444332L;
	/**
	 * id
	 */
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	/**
	 * 专辑名称						
	 */
	private String name;
	/**
	 * 专辑类型		
	 * ALBUM_TYPE	1:视频；2:用户作品											
	 */
	private String type;
	/**
	 * 特殊专辑类型						
	 * SPECIAL_ALBUM_TYPE:    1:一般专辑；2:特殊专辑											
	 */
	private String specialType;
	/**
	 * 特殊专辑编码						
	 */
	private String specialTypeCode;
	/**
	 * 特殊专辑名称						
	 */
	private String specialTypeName;
	/**
	 * 特殊专辑说明						
	 */
	private String specialTypeDescription;
	/**
	 * 背景图片路径						
	 */
	private String backgroundImgUrl;
	/**
	 * 专辑描述						
	 */
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
