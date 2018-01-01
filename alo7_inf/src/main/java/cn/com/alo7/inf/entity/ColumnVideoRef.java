package cn.com.alo7.inf.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.com.alo7.inf.entity.base.BaseEntity;
/**
 * 栏目下的视频
 * @author mazan
 *
 */
@Entity
@Table(name = "sys_column_video_ref")
public class ColumnVideoRef extends BaseEntity{
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
	/**
	 * 排序
	 */
	private Long position;
	/**
	 * 栏目
	 */
	@ManyToOne
    @JoinColumn(name = "column_id",referencedColumnName="id")
	private Column column;
	
	/**
	 * 视频
	 */
	@ManyToOne
    @JoinColumn(name = "video_id",referencedColumnName="id")
	private Video video;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getPosition() {
		return position;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPosition(Long position) {
		this.position = position;
	}
}
