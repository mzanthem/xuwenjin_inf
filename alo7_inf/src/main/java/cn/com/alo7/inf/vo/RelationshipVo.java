package cn.com.alo7.inf.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)  
public class RelationshipVo<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<T> data;

	public RelationshipVo() {
	}
	/**
	 * 是否初始化列表
	 * @param init
	 */
	public RelationshipVo(boolean init) {
		if (init) {
			this.data = new ArrayList<>();
		}
	}
	
	
	public RelationshipVo(List<T> data) {
		this();
		this.data = data;
	}


	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	public void setData(T t) {
		if (null == this.data) {
			this.data = new ArrayList<>();
		}
		this.data.add(t);
	}
}
