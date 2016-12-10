package com.rocket.myshop.exception;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

public class RestfulExceptionResolver extends AbstractHandlerExceptionResolver implements InitializingBean{

	protected static final Log logger = LogFactory.getLog(RestfulExceptionResolver.class);
	
	private RestfulExceptionHandler restfulExceptionHandler;
	
	public RestfulExceptionHandler getRestfulExceptionHandler() {
		return restfulExceptionHandler;
	}

	public void setRestfulExceptionHandler(RestfulExceptionHandler restfulExceptionHandler) {
		this.restfulExceptionHandler = restfulExceptionHandler;
	}


	@Override
    public void afterPropertiesSet() throws Exception {
		//ensureMessageConverters();
    }
	
//	private void ensureMessageConverters() {
//		// 加载默认的:
//		new HttpMessageConverterHelper().addDefaults(messageConverters);
//	}
//	
//	  //利用spring的默认加载bean:
//    private static final class HttpMessageConverterHelper extends WebMvcConfigurationSupport {
//        public void addDefaults(List<HttpMessageConverter<?>> converters) {
//            addDefaultHttpMessageConverters(converters);
//        }
//    }
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) {
		
		ServletWebRequest webRequest = new ServletWebRequest(request, response);
		RestError error = restfulExceptionHandler.resolveError(webRequest, handler, ex);
		
		String status = error.getStatus();
		restfulExceptionHandler.applyStatusCodeIfPossible(request, response, Integer.parseInt(status));
		Object body = error;
		try{
			restfulExceptionHandler.handleResponseBody(body, webRequest);
		} catch (Exception invocationEx) {
	        logger.error("Acquiring ModelAndView for Exception [" + ex + "] resulted in an exception.", invocationEx);
	    }
		//控制台打印详细的错误
		ex.printStackTrace();
		logger.error(Arrays.toString(ex.getStackTrace())); 
		return new ModelAndView();
	}
}
