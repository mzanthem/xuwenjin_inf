package cn.com.alo7.inf.service;

import java.util.List;

import cn.com.alo7.inf.entity.Column;

/**
 * 栏目service
 * @author mazan
 *
 */
public interface IColumnService {
	/**
	 * 全查询
	 * @return
	 */
	List<Column> findAll();
	
}
