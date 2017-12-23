package cn.com.alo7.inf.vo;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)  
public class RootVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object data;
	private Object included;
	private Map<String,Object> meta;
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getIncluded() {
		return included;
	}

	public void setIncluded(Object included) {
		this.included = included;
	}

	public Map<String,Object> getMeta() {
		return meta;
	}

	public void setMeta(Map<String,Object> meta) {
		this.meta = meta;
	}
	
}
