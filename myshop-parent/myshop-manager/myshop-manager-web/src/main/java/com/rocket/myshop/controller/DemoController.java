package com.rocket.myshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rocket.myshop.dto.common.ShopResult;
import com.rocket.myshop.service.DemoService;

@Controller
public class DemoController {

	@Reference
	DemoService demoService;
	
	@RequestMapping(value = "/listDemo", method = RequestMethod.GET)
	public ModelAndView viewDemo() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("demo2");
		ShopResult result = demoService.listDemo();
		mav.addObject("data", result);
		return mav;
	}
	
	@RequestMapping(value = "/showData", method = RequestMethod.GET)
	public ModelAndView getDemo(ModelMap map){
		ShopResult result = demoService.listDemo();
		ModelAndView mav = new ModelAndView("blank", "data", result);
		return mav;
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView error(Exception error, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        mav.addObject("param", "Exception error");
        //这里异常处理使用了全局消息处理，详见properties配置
        throw new ValidationException("Error.1");
       // return mav;
    }  
    
}
