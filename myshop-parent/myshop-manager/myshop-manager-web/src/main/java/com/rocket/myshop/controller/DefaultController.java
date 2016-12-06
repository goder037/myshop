package com.rocket.myshop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
* @FullClassName com.rocket.myshop.controller.DefaultController
* @Description: 默认控制器处理没有映射的资源请求
* @author  Liu Jie
* @date 2016年11月24日 上午8:49:18 
* @version V1.0.0
 */
@Controller
public class DefaultController {

    @RequestMapping("/*.json")
    public ModelAndView unmappedRequest(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, String> error = new HashMap<>();
    	error.put("code", "Error.1");
    	error.put("message", "请求的资源没有找到，请检查超链接是否正确");
    	error.put("moreInfoUrl", "mailto:support@rocket.com");
    	error.put("status", "404");
    	response.setStatus(HttpStatus.NOT_FOUND.value());
    	ModelAndView mav = new ModelAndView("error/404", error);
    	return mav;
    }
}
