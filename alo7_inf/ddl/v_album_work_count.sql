DROP VIEW IF EXISTS `v_album_work_count`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `v_album_work_count` AS (
-- 专辑下作品数量
SELECT
	sa.id as album_id,
	count(sw.id) AS count,
    sum(sw.like_count_in + sw.like_count_out + sw.like_count_edit) as like_count
FROM sys_album sa
LEFT JOIN sys_album_video_ref savr on savr.album_id = sa.id
inner join sys_work sw on sw.id = savr.video_id
WHERE 1=1
and   sw.delete_flag = '0'
and   sw.`status` = 'released'
and   sw.is_in_blacklist = 'no'
and   sa.delete_flag = '0'
and   sa.type = '2'  -- 作品
GROUP BY sa.id
)