package com.rocket.myshop.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.rocket.myshop.common.validattion.FirstValid;
import com.rocket.myshop.domain.Item;
import com.rocket.myshop.domain.ItemBrand;
import com.rocket.myshop.dto.ItemCategoryDto;
import com.rocket.myshop.dto.common.ShopQueryResult;
import com.rocket.myshop.dto.common.ShopTxResult;
import com.rocket.myshop.service.ItemBrandService;
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
	
	@Reference
	ItemBrandService itemBrandService;
	
	//------------------------- 商品----------------------------------------
	
	@RequestMapping(value = "/listItem.json", method = RequestMethod.POST)
	public ShopQueryResult listItem(Map<String, Object> params) {
		ShopQueryResult result = itemService.listItem(params);
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/deleteItem", method = RequestMethod.POST)
	public Object deleteItem(@Valid Item input){
		return null;
	} 
	
	@RequestMapping(value = "/updateItem", method = RequestMethod.POST)
	public Object updateItem(@Valid Item input){
		return null;
	}
	
	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public Object addItem(@Valid Item input){
		return null;
	}
	
	//------------------------- 商品品牌----------------------------------------
	
	@RequestMapping(value = "/listItemsBrand.json", method = RequestMethod.POST)
	public Object listItemBrand(@RequestBody Map<String, Object> params){
		ShopQueryResult result = itemBrandService.listItemBrands(params);
		result.setSuccess(true);
		result.setVersion("1.0.0");
		return result;
	}
	
	@RequestMapping(value = "/addItemsBrand.json", method = RequestMethod.POST)
	public Object addItemBrand(@RequestBody ItemBrand itemBrand){
		
		return null;
	}
	
	@RequestMapping(value = "/updateItemsBrand.json", method = RequestMethod.POST)
	public Object updateItemsBrand(@RequestBody ItemBrand itemBrand){
		
		return null;
	}
	
	@RequestMapping(value = "/deleteItemsBrand.json", method = RequestMethod.POST)
	public Object deleteItemsBrand(@RequestBody ItemBrand itemBrand){
		
		return null;
	}
	
	//------------------------- 商品类别----------------------------------------
	
	@RequestMapping(value = "/listItemsCategory.json", method = RequestMethod.POST)
	public Object listItemsCategory(@RequestBody Map<String, Object> params) {
		ShopQueryResult result = itemCategoryService.listItemCategory(params);
		return result;
	}
	
	@RequestMapping(value = "/getSubItemsCategory.json", method = RequestMethod.POST)
	public Object getSubItemsCategory(@RequestParam Integer parentId) {
		ShopQueryResult result = itemCategoryService.getSubItemsCategory(parentId);
		return result;
	}
	
	@RequestMapping(value = "/addItemsCategory.json", method = RequestMethod.POST)
	public Object addItemCategory(@RequestBody @Validated(FirstValid.class) ItemCategoryDto itemCategory, BindingResult errors) throws Exception{
		ShopTxResult result = null;
		if(errors.hasErrors()){
			result = new ShopTxResult(errors);
			return result;
		}
		result = itemCategoryService.addItemsCategory(itemCategory);
		return result;
	}
	
	@RequestMapping(value = "/updateItemsCategory.json", method = RequestMethod.POST)
	public Object updateItemsCategory(@RequestBody @Validated(FirstValid.class) ItemCategoryDto itemCategory, BindingResult errors) throws Exception {
		ShopTxResult result = null;
		if(errors.hasErrors()){
			result = new ShopTxResult(errors);
			return result;
		}
		result = itemCategoryService.updateItemsCategory(itemCategory);
		return result;
	} 
	
	@RequestMapping(value = "/deleteItemsCategory.json", method = RequestMethod.POST)
	public Object deleteItemsCategory(@RequestParam Integer categoryId){
		ShopTxResult result = itemCategoryService.deleteItemsCategory(categoryId);
		return result;
	} 
	
}
