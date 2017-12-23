package cn.com.alo7.inf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.com.alo7.inf.entity.Work;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long>{
	
	/**
	 * 根据videoId和size查询某个视频下非拉黑work
	 * @param id
	 * @param size
	 * @return
	 */
	@Query(value="select sw.* from sys_video sv inner join sys_work sw on sv.id = sw.video_id where sv.delete_flag = '0' and sw.delete_flag = '0' and sw.is_in_blacklist = 'no' and sv.id= ?1 order by sw.like_count_in + sw.like_count_out + sw.like_count_edit DESC limit ?2",nativeQuery = true)
	List<Work> findWorkByVideoId(Long id, Integer size);
	
	/**
	 * 查询单个work
	 * @param id
	 * @param deleteFlag
	 * @return
	 */
	Work findByIdAndDeleteFlag(Long id,String deleteFlag);
}
