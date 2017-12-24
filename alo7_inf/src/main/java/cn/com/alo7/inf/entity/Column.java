package cn.com.alo7.inf.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.com.alo7.inf.entity.base.BaseEntity;

/**
 * 栏目表
 * @author mazan
 *
 */
@Entity
@Table(name = "sys_column")
public class Column extends BaseEntity{
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
	 * 栏目名称						
	 */
	private String name;
	/**
	 * 图片路径
	 */
	private String picUrl;
	/**
	 * 栏目描述						
	 */
	private String description;
	/**
	 * 是否上架
	 * IS_NOT	是：yes；否：no											
	 */
	private String status;
	/**
	 * 排序						
	 */
	private Long position;
	/**
	 * 栏目下的视频
	 */
	@OneToMany(mappedBy="column")
	private List<ColumnVideoRef> columnVideoRef;

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
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

	public long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public List<ColumnVideoRef> getColumnVideoRef() {
		return columnVideoRef;
	}

	public void setColumnVideoRef(List<ColumnVideoRef> columnVideoRef) {
		this.columnVideoRef = columnVideoRef;
	}
}
