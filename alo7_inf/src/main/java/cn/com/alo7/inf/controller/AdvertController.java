package cn.com.alo7.inf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.alo7.inf.common.Constant;
import cn.com.alo7.inf.common.utils.JsonUtils;
import cn.com.alo7.inf.entity.Advert;
import cn.com.alo7.inf.service.IAdvertService;
import cn.com.alo7.inf.vo.AdvertVo;
import cn.com.alo7.inf.vo.DataVo;
import cn.com.alo7.inf.vo.RootVo;

@RestController
public class AdvertController {
	
	@Autowired
	private IAdvertService advertService;
	
	@GetMapping("advert")
	@ResponseBody
	public RootVo getAdvert(){
		List<Advert> advertList = advertService.findByDeleteFlag(Constant.DELETE_FLAG_0);
		
		RootVo rootVo = JsonUtils.createRoot();
		List<DataVo<AdvertVo>> dataList = new ArrayList<DataVo<AdvertVo>>();
		
		AdvertVo advertVo = null;
		DataVo<AdvertVo> dataVo = null;
		//转换json
		for (Advert advert : advertList) {
			advertVo = new AdvertVo();
			BeanUtils.copyProperties(advert, advertVo);
			dataVo = (DataVo<AdvertVo>) JsonUtils.setData(advert.getId(), "advert", advertVo);
			dataList.add(dataVo);
		}
		rootVo.setData(dataList);
		
		return rootVo;
	}
}
