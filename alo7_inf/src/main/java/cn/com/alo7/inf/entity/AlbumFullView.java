package cn.com.alo7.inf.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.alo7.inf.entity.base.BaseEntity;

/**
 * 专辑实体
 * @author mazan
 *
 */
@Entity
@Table(name = "v_a10_album")
public class AlbumFullView extends BaseEntity {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -2775032162827156240L;
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
	/**
	 * 是否上架 up/down
	 */
	private String status;
	/**
	 * 专辑位置
	 */
	private Integer position;
	/**
	 * 专辑手动排序
	 */
	private String manual;
	/**
	 * 专辑发布时间
	 */
	private Date releasedTime;
	/**
	 * 专辑下 视频/作品 数量
	 */
	private Integer hot;
	
	/**
	 * 作品点赞数(type=2)
	 */
	private Integer likeCount;
	
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
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getManual() {
		return manual;
	}
	public void setManual(String manual) {
		this.manual = manual;
	}
	public Date getReleasedTime() {
		return releasedTime;
	}
	public void setReleasedTime(Date releasedTime) {
		this.releasedTime = releasedTime;
	}
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	
	
	
}
