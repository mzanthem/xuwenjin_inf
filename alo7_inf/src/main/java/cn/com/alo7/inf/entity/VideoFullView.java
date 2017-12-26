package cn.com.alo7.inf.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.alo7.inf.entity.base.BaseEntity;

/**
 * 视频视图
 * 
 * @author mazan
 *
 */
@Entity
@Table(name = "v_a13_video")
public class VideoFullView extends BaseEntity {
	/**
	 * UID
	 */
	private static final long serialVersionUID = -1944257133801444332L;
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String title;
	private String videoUrl;
	private String voiceTrackUrl;
	private String backgroundTrackUrl;
	private String subtitleUrl;
	private String coverUrl;
	private String difficulty;
	private String speed;
	private String description;
	private String source;
	private String isPassed;
	private String status;
	private String isDisplay;
	/**
	 * 视频内部排序
	 */
	private String position;
	/**
	 * sys_album_video_ref.album_id
	 */
	private Long albumId;
	/**
	 * 专辑视频手动排序 manual
	 */
	private String manual;

	/**
	 * 发布时间
	 */
	private Date releasedTime;
	/**
	 * 作品排序
	 */
	private Integer hot;

	//-------------------------- getters and setters ----------------------------//
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getVoiceTrackUrl() {
		return voiceTrackUrl;
	}

	public void setVoiceTrackUrl(String voiceTrackUrl) {
		this.voiceTrackUrl = voiceTrackUrl;
	}

	public String getBackgroundTrackUrl() {
		return backgroundTrackUrl;
	}

	public void setBackgroundTrackUrl(String backgroundTrackUrl) {
		this.backgroundTrackUrl = backgroundTrackUrl;
	}

	public String getSubtitleUrl() {
		return subtitleUrl;
	}

	public void setSubtitleUrl(String subtitleUrl) {
		this.subtitleUrl = subtitleUrl;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getIsPassed() {
		return isPassed;
	}

	public void setIsPassed(String isPassed) {
		this.isPassed = isPassed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

}
