DROP VIEW IF EXISTS `v_a10_album`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `v_a10_album` AS (
SELECT
	sa.id AS id,
	sa.name AS name,
	sa.type AS type,
	sa.special_type AS special_type,
	sa.special_type_code AS special_type_code,
	sa.special_type_name AS special_type_name,
	sa.special_type_description AS special_type_description,
	sa.background_img_url AS background_img_url,
	sa.description AS description,
	sa.status AS status,
	sa.position AS position,
	sa.delete_flag AS delete_flag,
	sa.created_by AS created_by,
	sa.created_at AS created_at,
	sa.updated_by AS updated_by,
	sa.updated_at AS updated_at,
	sa.version AS version,
	sa.position as manual,
    sa.updated_at as released_time,
  (
  case sa.type
    when '1' then avc.count
    when '2' then awc.count
    else NULL
  end 
  ) as hot

 ,(
  case sa.type
    when '2' then awc.like_count
    else NULL
  end 
  ) as like_count

FROM sys_album sa
LEFT JOIN v_album_video_count avc on sa.id = avc.album_id
LEFT JOIN v_album_work_count  awc on sa.id = awc.album_id

WHERE 1=1
	and sa.delete_flag = '0'
	AND sa.status = 'up'
)