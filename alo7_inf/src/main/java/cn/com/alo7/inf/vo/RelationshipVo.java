package cn.com.alo7.inf.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * relationShip:xxx:data{}
 * @author mazan
 *
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)  
public class RelationshipVo<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private T data;

	public RelationshipVo() {
	}
	
	
	public RelationshipVo(T data) {
		this.data = data;
	}


	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
