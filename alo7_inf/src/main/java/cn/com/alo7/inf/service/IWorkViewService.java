package cn.com.alo7.inf.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 作品相关service
 * @author mazan
 *
 */
import cn.com.alo7.inf.entity.WorkFullView;
public interface IWorkViewService {
	
	/**
	 * 根据作品id查找作品
	 * @param workId
	 * @return
	 */
	WorkFullView findById(Long workId);
	/**
	 * 通过视频Id查询视频对应所有的作品
	 * @param id
	 * @return
	 */
	Page<WorkFullView> findWorkByAlbumId(Long albumId, Pageable pageable);
	
	/**
	 * 通过用户id查找作品
	 * @param uuid
	 * @param pageable
	 * @return
	 */
	Page<WorkFullView> findWorkByUserId(String uuid, Pageable pageable);
	/**
	 * 根据专辑id查询视频总数&视频下作品总数
	 * @param albumId
	 * @return
	 */
	Map<String, Object> findWorkTotal(Long albumId);
	
	/**
	 * 根据用户id查询视频总数&视频下作品总数
	 * @param albumId
	 * @return
	 */
	Map<String, Object> findWorkTotalbyUserId(String userId);
}
