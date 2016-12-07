package com.rocket.myshop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @FullClassName com.rocket.myshop.controller.PageController
* @Description: 1.处理没有映射的ajax请求  2. 所有的页面跳转配置
* @author  Liu Jie
* @date 2016年11月24日 上午8:49:18 
* @version V1.0.0
 */
@Controller
public class PageController {

	@RequestMapping(value="/*.json", method = RequestMethod.POST)
	public @ResponseBody Object unmappedAjaxRequest(HttpServletRequest request,	HttpServletResponse response) {
		Map<String, String> error = new HashMap<>();
		error.put("code", "Error.1");
		error.put("message", "请求的资源没有找到，请检查超链接是否正确");
		error.put("moreInfoUrl", "mailto:support@rocket.com");
		error.put("status", "404");
		response.setStatus(HttpStatus.NOT_FOUND.value());
		return error;
	}
	
	@RequestMapping(value="/{module}/{page}.html", method = RequestMethod.GET)
	public String mappedHtmlRequest(@PathVariable String page, @PathVariable String module) {
		return module+"/"+page+"";
	}
}
