package com.rocket.myshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rocket.myshop.domain.Item;
import com.rocket.myshop.dto.common.DataTablesOutput;
import com.rocket.myshop.mapper.ItemMapper;
import com.rocket.myshop.service.ItemService;

@Service("ItemService")
public class ItemServiceImpl implements ItemService{

	@Resource
	ItemMapper itemMapper;

	@Override
	public DataTablesOutput<Item> listItem() {
		int count = itemMapper.getCount();
		DataTablesOutput<Item> result = new DataTablesOutput<Item>();
		result.setRecordsTotal(count);
		if(count>0){
			List<Item> items = itemMapper.listItem();
			result.setData(items);
		}
		return result;
	}
	
	

}
