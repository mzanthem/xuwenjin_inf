DROP VIEW IF EXISTS `v_album_video_count`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `v_album_video_count` AS (
-- 专辑下视频数量
SELECT
	sa.id as album_id,
	count(sv.id) AS count
FROM sys_album sa
LEFT JOIN sys_album_video_ref savr on savr.album_id = sa.id
inner join sys_video sv on sv.id = savr.video_id
WHERE 1=1
and   sv.delete_flag = '0'
and   sv.`status` = 'up'
and   sv.is_passed = 'yes'
and   sa.delete_flag = '0'
and   sa.type = '1' -- 视频
GROUP BY sa.id
)