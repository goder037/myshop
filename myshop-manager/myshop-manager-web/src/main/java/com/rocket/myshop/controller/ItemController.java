package com.rocket.myshop.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.annotation.JsonView;
import com.rocket.myshop.domain.Item;
import com.rocket.myshop.dto.common.DataTablesInput;
import com.rocket.myshop.dto.common.DataTablesOutput;
import com.rocket.myshop.service.ItemService;

/**
* @FullClassName com.rocket.myshop.controller.ItemController
* @Description: SKU商品管理
* @author  Liu Jie
* @date 2016年12月7日 下午2:45:27 
* @version V1.0.0
 */
@Controller
@RequestMapping(value = "/items")
public class ItemController {

	@Reference
	ItemService itemService;
	
	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(value = "/listItem.json", method = RequestMethod.POST)
	public @ResponseBody Object listItem(@Valid DataTablesInput input) {
		DataTablesOutput<Item> result = itemService.listItem();
		return result;
	}
	
	@RequestMapping(value = "/itemsCategory.json", method = RequestMethod.POST)
	public @ResponseBody Object itemsCategory(DataTablesInput input) {
		DataTablesOutput<Item> result = itemService.listItem();
		return result;
	}
	
	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public @ResponseBody Object addItem(ModelMap map){
		DataTablesOutput<Item> result = itemService.listItem();
		return result;
	} 
	
	@RequestMapping(value = "/deleteItem", method = RequestMethod.POST)
	public @ResponseBody Object deleteItem(ModelMap map){
		DataTablesOutput<Item> result = itemService.listItem();
		return result;
	} 
	
	@RequestMapping(value = "/updateItem", method = RequestMethod.POST)
	public @ResponseBody Object updateItem(ModelMap map){
		DataTablesOutput<Item> result = itemService.listItem();
		return result;
	}
}
