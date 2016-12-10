package com.rocket.myshop.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rocket.myshop.dto.ItemCategoryDto;
import com.rocket.myshop.dto.common.ShopResult;
import com.rocket.myshop.mapper.ItemCategoryMapper;
import com.rocket.myshop.service.ItemCategoryService;

@Service("ItemCategoryService")
public class ItemCategoryServiceImpl implements ItemCategoryService {

	@Resource
	ItemCategoryMapper itemCategoryMapper;

	@Override
	public ShopResult listItemCategory(Map<String, Object> params) {
		int count = itemCategoryMapper.getCount(params);
		ShopResult result = new ShopResult();
		result.setTotal(count);
		if (count > 0) {
			Integer length = (Integer) params.get("length");
			if(length == -1){
				 params.put("length", Integer.MAX_VALUE);
			}
			List<ItemCategoryDto> data = itemCategoryMapper.listItemCategory(params);
			result.setList(data);
		}
		return result;
	}

}
