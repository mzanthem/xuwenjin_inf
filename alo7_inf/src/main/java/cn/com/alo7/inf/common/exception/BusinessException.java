package cn.com.alo7.inf.common.exception;

/**
 *
 */
public class BusinessException extends RuntimeException {
    /**
	 * UID
	 */
	private static final long serialVersionUID = 7160794254459813278L;
	
	/**
	 * 状态码
	 */
	private int statusCode;
    

    /**
     * @param statusCode
     * @param key
     * @param cause
     * @param param
     */
    public BusinessException(int statusCode, Throwable cause, Object... param) {
        super(cause);
        this.statusCode = statusCode;
    }

    /**
     * @param statusCode
     * @param key
     * @param cause
     */
    public BusinessException(int statusCode, Throwable cause) {
        super(cause);
        this.statusCode = statusCode;
    }

    /**
     * @param cause
     */
    public BusinessException(Throwable cause) {
        super(cause);
    }

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}