package cn.com.alo7.inf.vo;

import cn.com.alo7.inf.entity.base.BaseEntity;
import cn.com.alo7.inf.vo.base.BaseVo;

public class WorkVo extends BaseVo{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1944257133801444332L;
	
	private Long id;
	private String uuid;
	private String title;
	private String voiceTrackUrl;
	private String status;
	private String isInBlacklist;
	private Integer likeCountIn;
	private Integer likeCountOut;
	private Integer likeCountEdit;
	private Integer likeCount;
	private String shareUrl;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVoiceTrackUrl() {
		return voiceTrackUrl;
	}
	public void setVoiceTrackUrl(String voiceTrackUrl) {
		this.voiceTrackUrl = voiceTrackUrl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsInBlacklist() {
		return isInBlacklist;
	}
	public void setIsInBlacklist(String isInBlacklist) {
		this.isInBlacklist = isInBlacklist;
	}
	public Integer getLikeCountIn() {
		return likeCountIn;
	}
	public void setLikeCountIn(Integer likeCountIn) {
		this.likeCountIn = likeCountIn;
	}
	public Integer getLikeCountOut() {
		return likeCountOut;
	}
	public void setLikeCountOut(Integer likeCountOut) {
		this.likeCountOut = likeCountOut;
	}
	public Integer getLikeCountEdit() {
		return likeCountEdit;
	}
	public void setLikeCountEdit(Integer likeCountEdit) {
		this.likeCountEdit = likeCountEdit;
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public String getShareUrl() {
		return shareUrl;
	}
	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
}
