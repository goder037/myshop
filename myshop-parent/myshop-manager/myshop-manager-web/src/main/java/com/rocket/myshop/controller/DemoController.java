package com.rocket.myshop.controller;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rocket.myshop.common.domain.ShopResult;
import com.rocket.myshop.service.DemoService;

@Controller
public class DemoController {

	@Resource
	DemoService demoService;
	
	@Resource
	ReloadableResourceBundleMessageSource messageSource;
	

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
		//int i = 1/0;
		String message = messageSource.getMessage("Error.0", null, Locale.CHINESE);
		System.out.println(message);
		return mav;
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
    public ModelAndView error(Exception error, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        mav.addObject("param", "Exception error");
        throw new ValidationException();
       // return mav;
    }  
    
}
