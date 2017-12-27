package cn.com.alo7.inf.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.com.alo7.inf.entity.base.BaseEntity;

/**
 * 作品视图
 * 
 * @author mazan
 *
 */
@Entity
@Table(name = "v_a13_work")
public class WorkFullView extends BaseEntity {
	/**
	 * UID
	 */
	private static final long serialVersionUID = -81926016672775368L;
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 用户id
	 */
	private String uuid;
	/**
	 * 视频ID						
	 */
	@ManyToOne
	@JoinColumn(name = "video_id", referencedColumnName = "id")
	private Video video;
	/**
	 * 宣传名称						
	 */
	private String title;
	/**
	 * 作品语音音轨路径						
	 */
	private String voiceTrackUrl;
	/**
	 * 发布状态	
	 * RELEASE_STATUS	发布：released,未发布：unreleased											
	 */
	private String status;
	/**
	 * 是否拉黑	
	 * IS_NOT	1.是;2.否											
	 */
	private String isInBlacklist;
	/**
	 * 内部点赞数						
	 */
	private Integer likeCountIn = 0;
	/**
	 * 外部点赞数						
	 */
	private Integer likeCountOut = 0;
	/**
	 * 编辑点赞数						
	 */
	private Integer likeCountEdit = 0;
	/**
	 * 点赞总数
	 */
	private Integer likeCount;
	/**
	 * 点赞结果： yes 视频点过赞； no 视频未点过赞
	 */
	private Boolean isLiked;
	/**
	 * 分享URL						
	 */
	private String shareUrl;
	
	/**
	 * sys_album_video_ref.album_id
	 */
	private Long albumId;
	
	/**
	 * 视频排序
	 */
	private String manual;
	/**
	 * 视频时间
	 */
	private Date todayVideo;
	/**
	 * 视频作品量
	 */
	private Integer hot;
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
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
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
	public Boolean getIsLiked() {
		return isLiked;
	}
	public void setIsLiked(Boolean isLiked) {
		this.isLiked = isLiked;
	}
	public String getShareUrl() {
		return shareUrl;
	}
	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
	public Long getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}
	public String getManual() {
		return manual;
	}
	public void setManual(String manual) {
		this.manual = manual;
	}
	public Date getTodayVideo() {
		return todayVideo;
	}
	public void setTodayVideo(Date todayVideo) {
		this.todayVideo = todayVideo;
	}
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	
	
	
}
