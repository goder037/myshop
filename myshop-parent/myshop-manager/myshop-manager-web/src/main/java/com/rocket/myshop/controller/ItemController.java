package com.rocket.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rocket.myshop.dto.common.ShopResult;
import com.rocket.myshop.service.ItemService;

@Controller
public class ItemController {

	@Reference
	ItemService itemService;
	
	@RequestMapping(value = "/listItem", method = RequestMethod.GET)
	public ModelAndView listItem() {
		ShopResult result = itemService.listItem();
		ModelAndView mav = new ModelAndView("blank", "data", result);
		return mav;
	}
	
	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public ModelAndView addItem(ModelMap map){
		ShopResult result = itemService.listItem();
		ModelAndView mav = new ModelAndView("blank", "data", result);
		return mav;
	} 
	
	@RequestMapping(value = "/deleteItem", method = RequestMethod.POST)
	public ModelAndView deleteItem(ModelMap map){
		ShopResult result = itemService.listItem();
		ModelAndView mav = new ModelAndView("blank", "data", result);
		return mav;
	} 
	
	@RequestMapping(value = "/updateItem", method = RequestMethod.POST)
	public ModelAndView updateItem(ModelMap map){
		ShopResult result = itemService.listItem();
		ModelAndView mav = new ModelAndView("blank", "data", result);
		return mav;
	}
}
