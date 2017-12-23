package cn.com.alo7.inf.vo;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)  
public class DataVo<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String type;
	private T attributes;
	private Map<String,Object> relationships;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public T getAttributes() {
		return attributes;
	}
	public void setAttributes(T attributes) {
		this.attributes = attributes;
	}
	public Map<String,Object> getRelationships() {
		return relationships;
	}
	public void setRelationships(Map<String,Object> relationships) {
		this.relationships = relationships;
	}
}
