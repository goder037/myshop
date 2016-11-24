package com.rocket.myshop.exception;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.util.Assert;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.util.WebUtils;

public class RestfulExceptionResolver extends AbstractHandlerExceptionResolver implements MessageSourceAware, InitializingBean{

	protected static final Log logger = LogFactory.getLog(RestfulExceptionResolver.class);
	
	private static final String PROPERTIES_SUFFIX = ".properties";
	
	private static final String XML_SUFFIX = ".xml";
	
	private static final String EXCEPTIONMSGCODE_PREFIX = "Error.";
	
	public static final String DEFAULT_MESSAGE_VALUE = "系统出现未知问题，请联系管理员!";
	    
	public static final String DEFAULT_MESSAGE_CODE = "Error.0";
	
	private String moreInfoUrl;
	
	private boolean showDeveloperMessage = false;
	
	private MessageSource messageSource;
	
	private String propertiesFileEncoding = "utf-8";

	private Properties exceptionStatusMapping;
	
	 private LocaleResolver localeResolver;
	
	private List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	
	private String[] exceptionMappingPropertiesLocations = new String[0];
	
	private ResourceLoader resourceLoader = new DefaultResourceLoader();
	
	private PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
	
	
	public List<HttpMessageConverter<?>> getMessageConverters() {
		return messageConverters;
	}

	public void setMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
		this.messageConverters = messageConverters;
	}

	public LocaleResolver getLocaleResolver() {
		return localeResolver;
	}

	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}

	public boolean isShowDeveloperMessage() {
		return showDeveloperMessage;
	}

	public void setShowDeveloperMessage(boolean showDeveloperMessage) {
		this.showDeveloperMessage = showDeveloperMessage;
	}

	public String getMoreInfoUrl() {
		return moreInfoUrl;
	}

	public void setMoreInfoUrl(String moreInfoUrl) {
		this.moreInfoUrl = moreInfoUrl;
	}

	public String[] getExceptionMappingPropertiesLocations() {
		return exceptionMappingPropertiesLocations;
	}

	public String getPropertiesFileEncoding() {
		return propertiesFileEncoding;
	}

	public void setPropertiesFileEncoding(String propertiesFileEncoding) {
		this.propertiesFileEncoding = propertiesFileEncoding;
	}

	public void setExceptionMappingPropertiesLocations(String[] exceptionMappingPropertiesLocations) {
		if (exceptionMappingPropertiesLocations != null) {
			for (int i = 0; i < exceptionMappingPropertiesLocations.length; i++) {
				String filename = exceptionMappingPropertiesLocations[i];
				Assert.hasText(filename, "exceptionMappingPropertiesLocations中每一个文件路径都不能为空！");
				exceptionMappingPropertiesLocations[i] = filename.trim();
			}
		}
		else {
			this.exceptionMappingPropertiesLocations = new String[0];
		}
		this.exceptionMappingPropertiesLocations = exceptionMappingPropertiesLocations;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@Override
    public void afterPropertiesSet() throws Exception {
		ensureMessageConverters();
		createDefaultExceptionMapping();
    }
	
	 private void createDefaultExceptionMapping() {
		Properties mergedProps = new Properties();
		for (int i = this.exceptionMappingPropertiesLocations.length - 1; i >= 0; i--) {
			for (int j = exceptionMappingPropertiesLocations.length - 1; j >= 0; j--) {
				String filename = exceptionMappingPropertiesLocations[j];
				Properties properties = getProperties(filename);
				if (properties != null) {
					mergedProps.putAll(properties);
				}
			}
		}
		this.exceptionStatusMapping = mergedProps;
		
	}

	private Properties getProperties(String filename) {
		Properties props = null;
		Resource resource = this.resourceLoader.getResource(filename + PROPERTIES_SUFFIX);
		if (resource.exists()) {
			try {
				props = loadProperties(resource, filename);
			}catch (IOException ex) {
				if (logger.isWarnEnabled()) {
					logger.warn("无法解析properties文件： [" + resource.getFilename() + "]", ex);
				}
				props = new Properties();
			}
		}
		return props;
	}

	protected Properties loadProperties(Resource resource, String filename) throws IOException {
		InputStream is = resource.getInputStream();
		Properties props = new Properties();
		try {
			if (resource.getFilename().endsWith(XML_SUFFIX)) {
				if (logger.isDebugEnabled()) {
					logger.debug("加载properties [" + resource.getFilename() + "]");
				}
				this.propertiesPersister.loadFromXml(props, is);
			}
			else {
				if (propertiesFileEncoding != null) {
					if (logger.isDebugEnabled()) {
						logger.debug("加载 properties [" + resource.getFilename() + "] with encoding '" + propertiesFileEncoding + "'");
					}
					this.propertiesPersister.load(props, new InputStreamReader(is, propertiesFileEncoding));
				}
				else {
					if (logger.isDebugEnabled()) {
						logger.debug("加载 properties [" + resource.getFilename() + "]");
					}
					this.propertiesPersister.load(props, is);
				}
			}
			return props;
		}
		finally {
			is.close();
		}
	}

	private void ensureMessageConverters() {
		// 加载默认的:
		new HttpMessageConverterHelper().addDefaults(messageConverters);
	}
	
	  //利用spring的默认加载bean:
    private static final class HttpMessageConverterHelper extends WebMvcConfigurationSupport {
        public void addDefaults(List<HttpMessageConverter<?>> converters) {
            addDefaultHttpMessageConverters(converters);
        }
    }
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) {
		
		ServletWebRequest webRequest = new ServletWebRequest(request, response);
		RestError error = resolveError(webRequest, handler, ex);
		
		String status = error.getStatus();
		applyStatusCodeIfPossible(request, response, Integer.parseInt(status));
		Object body = error;
		ModelAndView mav = null;
		try{
			mav = handleResponseBody(body, webRequest);
		} catch (Exception invocationEx) {
	        logger.error("Acquiring ModelAndView for Exception [" + ex + "] resulted in an exception.", invocationEx);
	    }
		ex.printStackTrace();
		logger.error(Arrays.toString(ex.getStackTrace()));  
		return mav;
	}
	
	private ModelAndView handleResponseBody(Object body, ServletWebRequest webRequest) throws ServletException, IOException {

        HttpInputMessage inputMessage = new ServletServerHttpRequest(webRequest.getRequest());

        List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();
        if (acceptedMediaTypes.isEmpty()) {
            acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
        }

        MediaType.sortByQualityValue(acceptedMediaTypes);

        HttpOutputMessage outputMessage = new ServletServerHttpResponse(webRequest.getResponse());

        Class<?> bodyType = body.getClass();

        List<HttpMessageConverter<?>> converters = this.messageConverters;

        if (converters != null) {
            for (MediaType acceptedMediaType : acceptedMediaTypes) {
                for (HttpMessageConverter messageConverter : converters) {
                    if (messageConverter.canWrite(bodyType, acceptedMediaType)) {
                        messageConverter.write(body, acceptedMediaType, outputMessage);
                        return new ModelAndView();
                    }
                }
            }
        }

        if (logger.isWarnEnabled()) {
            logger.warn("Could not find HttpMessageConverter that supports return type [" + bodyType +
                    "] and " + acceptedMediaTypes);
        }
        return null;
    }
	
	protected void applyStatusCodeIfPossible(HttpServletRequest request, HttpServletResponse response, int statusCode) {
		if (!WebUtils.isIncludeRequest(request)) {
			if (logger.isDebugEnabled()) {
				logger.debug("Applying HTTP status code " + statusCode);
			}
			response.setStatus(statusCode);
			request.setAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE, statusCode);
		}
	}
	
	 private String getRestErrorStatus(Exception ex) {
	        Properties exceptionStatusMappings = this.exceptionStatusMapping;
	        if (exceptionStatusMappings.isEmpty()) {
	            return null;
	        }
	        String statusCode = null;
	        String dominantMapping = null;
	        int deepest = Integer.MAX_VALUE;
	        Set<Object> keySet = exceptionStatusMappings.keySet();
	        for (Object key : keySet) {
	        	String keyStr = (String) key;
	        	int depth = getDepth(keyStr, ex);
	        	 if (depth >= 0 && depth < deepest) {
		                deepest = depth;
		                dominantMapping = keyStr;
		                statusCode = (String)exceptionStatusMappings.get(key);
		            }
			}
	        if (statusCode != null && logger.isDebugEnabled()) {
	        	logger.debug("获取restful风格错误模板 '" + statusCode + "' 对于异常类型 [" + ex.getClass().getName() + "], 基于异常类型映射 [" + dominantMapping + "]");
	        }
	        return statusCode;
	    }

	private RestError resolveError(ServletWebRequest webRequest, Object handler, Exception ex) {
		String errorStatus = getRestErrorStatus(ex);
		RestError restError = new RestError(errorStatus);
		restError = parseCodeAndMessage(restError, webRequest, ex);
		restError.setMoreInfoUrl(getMoreInfoUrl());
		restError.setDeveloperMessage(getDeveloperMessage(ex));
		return restError;
	}
	
	 private String getDeveloperMessage(Exception ex) {
		if(showDeveloperMessage){
			return Arrays.toString(ex.getStackTrace());
		}
		return null;
	}

	private RestError parseCodeAndMessage(RestError restError, ServletWebRequest webRequest, Exception ex) {
		String exCode = ex.getMessage();
		if(StringUtils.hasText(exCode)){
			if(exCode.startsWith(EXCEPTIONMSGCODE_PREFIX)){
				restError.setCode(exCode);
				String message = getMessage(webRequest, ex);
				restError.setMessage(message);
			}else{
				restError.setCode(DEFAULT_MESSAGE_CODE);
				restError.setMessage(exCode); 
			}
		}else{//系统抛出异常或者用户没有指定messageCode
			restError.setCode(DEFAULT_MESSAGE_CODE);
			String message = getMessage(webRequest, ex);
			restError.setMessage(message);
		}
		return restError;
		
	}

	/**
     * @MethodName: getMessage 
     * @Description: 返回客户端相应的message
     * @Author  Liu Jie
     * @Date 2016年11月22日 下午2:35:06
      */
    protected String getMessage(ServletWebRequest request, Exception ex) {
    	String msg = null;
    	if (messageSource != null) {
             Locale locale = null;
             if (localeResolver != null) {
                 locale = localeResolver.resolveLocale(request.getRequest());
             }
             // 国际化消息
             msg = messageSource.getMessage(ex.getMessage(), null, DEFAULT_MESSAGE_VALUE, locale);
         }
        return msg;
    }

	/**
	 * Return the depth to the superclass matching.
	 * <p>0 means ex matches exactly. Returns -1 if there's no match.
	 * Otherwise, returns depth. Lowest depth wins.
	 */
	protected int getDepth(String exceptionMapping, Exception ex) {
		return getDepth(exceptionMapping, ex.getClass(), 0);
	}

	private int getDepth(String exceptionMapping, Class<?> exceptionClass, int depth) {
		if (exceptionClass.getName().contains(exceptionMapping)) {
			// Found it!
			return depth;
		}
		// If we've gone as far as we can go and haven't found it...
		if (exceptionClass.equals(Throwable.class)) {
			return -1;
		}
		return getDepth(exceptionMapping, exceptionClass.getSuperclass(), depth + 1);
	}

}
