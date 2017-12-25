package cn.com.alo7.inf.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.alo7.inf.entity.base.BaseEntity;

@Entity
@Table(name = "sys_ad")
public class AdvertVo extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1944257133801444332L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ad_name;
	private String advert_type;
	private String img_url;
	private String parameter;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAd_name() {
		return ad_name;
	}
	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}
	public String getAdvert_type() {
		return advert_type;
	}
	public void setAdvert_type(String advert_type) {
		this.advert_type = advert_type;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
}
