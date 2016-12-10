package com.rocket.myshop.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class RestfulExceptionInterceptor extends HandlerInterceptorAdapter {

	protected static final Log logger = LogFactory.getLog(RestfulExceptionInterceptor.class);
	
	private RestfulExceptionHandler restfulExceptionHandler;
	
	

	public RestfulExceptionHandler getRestfulExceptionHandler() {
		return restfulExceptionHandler;
	}

	public void setRestfulExceptionHandler(
			RestfulExceptionHandler restfulExceptionHandler) {
		this.restfulExceptionHandler = restfulExceptionHandler;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,	HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//发生异常，进行处理
		if(ex!=null){
			ServletWebRequest webRequest = new ServletWebRequest(request, response);
			RestError error = restfulExceptionHandler.resolveError(webRequest, handler, ex);
			
			String status = error.getStatus();
			restfulExceptionHandler.applyStatusCodeIfPossible(request, response, Integer.parseInt(status));
			Object body = error;
			try{
				restfulExceptionHandler.handleResponseBody(body, webRequest);
			} catch (Exception invocationEx) {
		        logger.error("无法处理异常 [" + ex + "] ,并且出现错误.", invocationEx);
		    }
		}
	}
	
	
}
