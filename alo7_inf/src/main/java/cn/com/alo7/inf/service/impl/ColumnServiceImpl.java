package cn.com.alo7.inf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.alo7.inf.common.Constant;
import cn.com.alo7.inf.entity.Column;
import cn.com.alo7.inf.repository.ColumnRepository;
import cn.com.alo7.inf.service.IColumnService;

/**
 * 栏目
 * 
 * @author mazan
 *
 */
@Service
public class ColumnServiceImpl implements IColumnService {

	@Autowired
	private ColumnRepository columnRepository;
	
	@Override
	public List<Column> findAll() {
		List<Column> columnList = columnRepository.findByDeleteFlag(Constant.DELETE_FLAG_0);
		return columnList;
	}
}
