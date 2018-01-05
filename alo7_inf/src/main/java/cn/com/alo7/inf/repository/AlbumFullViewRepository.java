package cn.com.alo7.inf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.com.alo7.inf.entity.AlbumFullView;

/**
 * 栏目视图dao
 * @author mazan
 *
 */
@Repository
public interface AlbumFullViewRepository extends JpaRepository<AlbumFullView, Long>{
	
	/**
	 * 根据专辑和上架状态查询
	 * @param id
	 * @param status
	 * @return
	 */
	AlbumFullView findByIdAndStatus(Long id, String status);
	/**
	 * 根据类型和专辑code查找特殊专辑信息
	 * @param albumTypeSpecial
	 * @param identifier
	 * @return
	 */
	AlbumFullView findByTypeAndSpecialTypeCode(String albumTypeSpecial, String identifier);
}
