package cn.com.alo7.inf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import cn.com.alo7.inf.common.utils.JsonUtils;
import cn.com.alo7.inf.entity.Column;
import cn.com.alo7.inf.entity.ColumnVideoView;
import cn.com.alo7.inf.service.IColumnService;
import cn.com.alo7.inf.service.impl.ColumnVideoViewServiceImpl;
import cn.com.alo7.inf.vo.ColumnVo;
import cn.com.alo7.inf.vo.DataVo;
import cn.com.alo7.inf.vo.RootVo;
import cn.com.alo7.inf.vo.VideoVo;

@RestController
public class ColumnController extends BaseController {

	@Autowired
	private IColumnService columnService;
	
	@Autowired
	private ColumnVideoViewServiceImpl columnVideoViewServiceImpl;
	
	/**
	 * A01-栏目清单
	 * 
	 * @param videoId
	 * @return
	 * @throws JsonProcessingException 
	 * @throws Exception
	 */
	@GetMapping("columns")
	public RootVo getColumnList() throws JsonProcessingException {
		//查询栏目(deleteFlag为0)
		List<Column> columnList = columnService.findAll();
		RootVo rootVo = JsonUtils.createRoot();
		List<DataVo<ColumnVo>> dataList = new ArrayList<DataVo<ColumnVo>>();
		
		ColumnVo columnVo = null;
		DataVo<ColumnVo> dataVo = null;
		//转换json
		for (Column column : columnList) {
			columnVo = new ColumnVo();
			BeanUtils.copyProperties(column, columnVo);
			dataVo = (DataVo<ColumnVo>) JsonUtils.setData(columnVo.getId(), "column", columnVo);
			dataList.add(dataVo);
		}
		rootVo.setData(dataList);
		
		return rootVo;
	}

	/**
	 * A02-查询栏目视频
	 * @param columnId
	 * @param sort
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("columns/{columnId}/videos")
	public RootVo getColumnVideoList(@PathVariable Long columnId,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size) {
		//查询分页
		Page<ColumnVideoView> pageList = columnVideoViewServiceImpl.findAll(columnId, page, size, sort);
		//转换json
		RootVo rootVo = JsonUtils.createRoot();
		List<DataVo<VideoVo>> dataList = new ArrayList<DataVo<VideoVo>>();
		VideoVo videoVo = null;
		DataVo<VideoVo> dataVo = null;
		for (ColumnVideoView vColumnVideo : pageList) {
			videoVo = new VideoVo();
			BeanUtils.copyProperties(vColumnVideo, videoVo);
			dataVo = (DataVo<VideoVo>) JsonUtils.setData(vColumnVideo.getId(), "video", videoVo);
			dataList.add(dataVo);
		}
		rootVo.setData(dataList);
		return rootVo;
	}
}
