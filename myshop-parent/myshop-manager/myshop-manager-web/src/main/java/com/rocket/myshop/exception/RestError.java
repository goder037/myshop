package com.rocket.myshop.exception;

/**
* @FullClassName com.rocket.myshop.handler.RestError
* @Description: TODO
* @author  Liu Jie
* @date 2016年11月20日 下午6:09:52 
* @version V1.0.0
 */
public class RestError {

    private String status;
    private String code;
    private String message;
    private String developerMessage;
    private String moreInfoUrl;

    public RestError(String status) {
        if (status == null) {
            throw new NullPointerException("HttpStatus 参数不能为空.");
        }
        this.status = status;
    }

	public String getStatus() {
		return status;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public String getMoreInfoUrl() {
		return moreInfoUrl;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public void setMoreInfoUrl(String moreInfoUrl) {
		this.moreInfoUrl = moreInfoUrl;
	}
}