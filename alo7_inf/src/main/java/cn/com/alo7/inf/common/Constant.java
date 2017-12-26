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
     * 发布状态
     */
    public static final String STATUS_UP = "up";
    public static final String STATUS_DOWN = "down";
    
    /**
     * 是否
     */
    public static final String YES = "yes";
    public static final String NO = "no";
    
    public static final String TOKEN = "token";
    
	/**
	 * S3 配置 
	 */
	public static String ACCESSKEYID = "AKIAOHY26PYSEKTESD3Q";
	public static String SECRETKEY = "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";
	public static String BUCKETNAME = "njfhoddiowm4yjdj.saybot.net";
	public static String HTTP = "http://";
	
	/**
	 * 
	 */
	public static String USER_ID_URL = "http://account-api.dev.saybot.net/api/v1/open/users";
	public static String USER_TOKEN = "http://account-api.dev.saybot.net/api/v1/users/info";
}
