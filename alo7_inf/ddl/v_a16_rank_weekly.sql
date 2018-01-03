-- 查询用户排行
DROP VIEW IF EXISTS `v_a16_rank_weekly`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `v_a16_rank_weekly` AS (

select  t0.id,
	    t0.uuid,
	    t0.video_id,
	    t0.title,
		t0.voice_track_url,
		t0.`status`,
		t0.is_in_blacklist,
		t0.like_count_in,
		t0.like_count_out,
		t0.like_count_edit,
		t0.like_count,
		t0.is_liked,
		t0.share_url,
		t0.delete_flag,
		t0.created_by,
		t0.created_at,
		t0.updated_by,
		t0.updated_at,
		t0.version,
		t0.album_id
from v_a13_work t0
inner join 
(
  -- 用户去重
  select t1.uuid, max(t1.id) as maxId from v_a13_work t1
  inner join 
  (
    -- 找出最高赞
	select 
		t.uuid,
		MAX(t.like_count) as maxCount
	from v_a13_work t
    where 1=1
    and t.`status`='released'
	and t.is_in_blacklist = 'no'
    and t.updated_at > DATE_ADD(now(),INTERVAL -7 DAY)
	group by t.uuid
  ) t2 on t2.uuid = t1.uuid and t2.maxCount = t1.like_count
  group by t1.uuid
  
) t01 on t01.maxId = t0.id 

order by t0.like_count desc
)