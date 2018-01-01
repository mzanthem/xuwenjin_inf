DROP VIEW IF EXISTS `v_a11_video`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `v_a11_video` AS (
	select 
		sv.id,
		sv.name,
		sv.title,
		sv.video_url,
		sv.voice_track_url,
		sv.background_track_url,
		sv.subtitle_url,
		sv.cover_url,
		sv.difficulty,
		sv.speed,
		sv.description,
		sv.source,
		sv.is_passed,
		sv.status,
		sv.is_display,
		sv.position,
		sv.delete_flag,
		sv.created_by,
		sv.created_at,
		sv.updated_by,
		sv.updated_at,
		sv.version,
		savr.album_id,
	    savr.position as manual,  -- 专辑视频的手动位置 Q:那sv.position代表什么
		sv.updated_at as released_time, -- 发布时间
        IFNULL(vvwc.count,0) as hot    -- 作品量
	from sys_album_video_ref savr
	inner join sys_video sv on savr.video_id = sv.id
    left join v_video_work_count vvwc on sv.id = vvwc.video_id 
	
    where savr.delete_flag = '0'
      and savr.type = '1'
	  and sv.delete_flag = '0'
	  and sv.status = 'up'
	
	LIMIT 0,999999999999 
)