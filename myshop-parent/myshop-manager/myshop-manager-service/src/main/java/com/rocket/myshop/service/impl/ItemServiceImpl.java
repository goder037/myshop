package com.rocket.myshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rocket.myshop.dto.common.ShopResult;
import com.rocket.myshop.domain.Demo;
import com.rocket.myshop.mapper.ItemMapper;
import com.rocket.myshop.service.ItemService;

@Service("ItemService")
public class ItemServiceImpl implements ItemService{

	@Resource
	ItemMapper itemMapper;

	@Override
	public ShopResult listItem() {
		int count = itemMapper.getCount();
		ShopResult result = new ShopResult();
		result.setTotal(count);
		if(count>0){
			List<Demo> listAllDemo = itemMapper.listAllDemo();
			result.setList(listAllDemo);
		}
		return result;
	}
	
	

}
