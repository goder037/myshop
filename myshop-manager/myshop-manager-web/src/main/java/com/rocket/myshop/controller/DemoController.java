package com.rocket.myshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rocket.myshop.dto.common.ShopQueryResult;
import com.rocket.myshop.service.DemoService;

@RestController
public class DemoController {

	@Reference
	DemoService demoService;
	
	@RequestMapping(value = "/listDemo", method = RequestMethod.GET)
	public Object viewDemo() {
		ShopQueryResult data = demoService.listDemo();
		return data;
	}
	
	@RequestMapping(value = "/showData", method = RequestMethod.GET)
	public Object getDemo(ModelMap map){
		ShopQueryResult data = demoService.listDemo();
		return data;
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
    public Object error(Exception error, HttpServletRequest request, HttpServletResponse response) {
		//ShopQueryResult data = demoService.listDemo();
        //这里异常处理使用了全局消息处理，详见properties配置
        throw new ValidationException("Error.1");
       // return mav;
    }  
    
}
