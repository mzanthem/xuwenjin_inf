package cn.com.alo7.inf.service;

import java.util.List;

import cn.com.alo7.inf.entity.Work;
/**
 * 作品相关service
 * @author mazan
 *
 */
public interface IWorkService {
	
	/**
	 * 查询单个work
	 * @param id
	 * @return
	 */
	Work findById(Long id);
	
	
	/**
	 * 通过视频Id查询视频对应所有的作品
	 * @param id
	 * @return
	 */
	List<Work> findWorkByVideoId(Long id,Integer size);
}
