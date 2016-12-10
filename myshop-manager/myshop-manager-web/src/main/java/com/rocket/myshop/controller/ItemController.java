package com.rocket.myshop.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rocket.myshop.dto.ItemDto;
import com.rocket.myshop.dto.common.ShopResult;
import com.rocket.myshop.service.ItemCategoryService;
import com.rocket.myshop.service.ItemService;

/**
* @FullClassName com.rocket.myshop.controller.ItemController
* @Description: SKU商品管理
* @author  Liu Jie
* @date 2016年12月7日 下午2:45:27 
* @version V1.0.0
 */
@RestController
@RequestMapping(value = "/items")
public class ItemController {

	@Reference
	ItemService itemService;
	
	@Reference
	ItemCategoryService itemCategoryService;
	
	@RequestMapping(value = "/listItem.json", method = RequestMethod.POST)
	public ShopResult listItem(Map<String, Object> params) {
		ShopResult result = itemService.listItem(params);
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/itemsCategory.json", method = RequestMethod.POST)
	public ShopResult itemsCategory(@RequestBody Map<String, Object> params) {
		ShopResult result = itemCategoryService.listItemCategory(params);
		result.setSuccess(true);
		result.setVersion("1.0.0");
		return result;
	}
	
	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public Object addItem(@Valid ItemDto input){
		return null;
	} 
	
	@RequestMapping(value = "/deleteItem", method = RequestMethod.POST)
	public Object deleteItem(@Valid ItemDto input){
		return null;
	} 
	
	@RequestMapping(value = "/updateItem", method = RequestMethod.POST)
	public Object updateItem(@Valid ItemDto input){
		return null;
	}
}
