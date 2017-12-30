package cn.com.alo7.inf.common;

public abstract class Constant {
    /**
     * 删除标识
     */
    public static final String DELETE_FLAG_0 = "0";
    public static final String DELETE_FLAG_1 = "1";
    
    /**
     * page
     */
    public static final Integer PAGE = 0;
    public static final Integer SIZE = 15;
    public static final Integer SIZE_20 = 20;
    
    /**
     * 排序
     */
    //手工排序
    public static final String SORT_MANUAL = "manual";
    //发布时间排序
    public static final String SORT_RELEASEDTIME = "releasedTime";
    //作品量排序
    public static final String SORT_HOT = "hot";

    /**
     * 专辑类型 TODO 
     */
    public static final String ALBUM_TYPE_COMMON = "1";
    public static final String ALBUM_TYPE_SPECIAL = "2";
    /**
     * 上架、下架状态
     */
    public static final String STATUS_UP = "up";
    public static final String STATUS_DOWN = "down";
    
    /**
     * 发布、未发布
     */
    public static final String STATUS_RELEASED = "released";
    public static final String STATUS_UNRELEASED = "unreleased";
    
    /**
     * 作品类型
     */
    public static final String TYPE_VOICE = "voice";
    public static final String TYPE_BACKGROUND = "background";
    public static final String TYPE_VOICEBACKGROUND = "voiceBackground";
    
    /**
     * 是否
     */
    public static final String YES = "yes";
    public static final String NO = "no";
    
    public static final String ACCESS_TOKEN = "accessToken";
    
	/**
	 * S3 配置 
	 */
	public static String ACCESSKEYID = "AKIAOHY26PYSEKTESD3Q";
	public static String SECRETKEY = "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";
	public static String BUCKETNAME = "njfhoddiowm4yjdj.saybot.net";
	public static String HTTP = "http://";
	
	/**
	 * user接口
	 */
	public static String USER_ID_URL = "http://account-api.dev.saybot.net/api/v1/open/users?uuids[]={uuids}";
	public static String USER_TOKEN = "http://account-api.dev.saybot.net/api/v1/users/info?accessToken={accessToken}";
}
