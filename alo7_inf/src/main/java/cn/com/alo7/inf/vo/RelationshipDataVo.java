package cn.com.alo7.inf.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)  
public class RelationshipDataVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RelationshipDataVo(){
		
	}
	
	public RelationshipDataVo(String id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	private String id;
	
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
