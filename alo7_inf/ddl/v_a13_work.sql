DROP VIEW IF EXISTS `v_a13_work`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `v_a13_work` AS (
	select 
		sw.id,
		sw.uuid,
	    sw.video_id,
		sw.title,
		sw.voice_track_url,
		sw.`status`,
		sw.is_in_blacklist,
		sw.like_count_in,
		sw.like_count_out,
		sw.like_count_edit,
		sw.like_count_in + sw.like_count_out + sw.like_count_edit as like_count,
		IF(sw.like_count_in + sw.like_count_out + sw.like_count_edit > 0,'yes','no') as is_liked,
		sw.share_url,
		
		savr.album_id,
	    
		vav.manual as manual,  -- 专辑视频的手动位置 Q:那sv.position代表什么
		vav.released_time as today_video, -- 发布时间
        vav.hot as hot    -- 作品量
	from sys_album_video_ref savr
	inner join sys_work sw on savr.video_id = sw.id
    inner join v_a11_video  vav on vav.id = sw.video_id  -- 作品反查视频 

    where savr.delete_flag = '0'
      and savr.type = '1'
	  and sw.delete_flag = '0'
	  and sw.status = 'released'
	
	LIMIT 0,999999999999 
)