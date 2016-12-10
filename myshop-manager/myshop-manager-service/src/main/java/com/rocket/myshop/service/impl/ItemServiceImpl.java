package com.rocket.myshop.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rocket.myshop.dto.ItemDto;
import com.rocket.myshop.dto.common.ShopResult;
import com.rocket.myshop.mapper.ItemMapper;
import com.rocket.myshop.service.ItemService;

@Service("ItemService")
public class ItemServiceImpl implements ItemService {

	@Resource
	ItemMapper itemMapper;

	@Override
	public ShopResult listItem(Map<String, Object> params) {
		int count = itemMapper.getCount(params);
		ShopResult result = new ShopResult();
		result.setTotal(count);
		if (count > 0) {
			Integer length = (Integer) params.get("length");
			if (length == -1) {
				params.put("length", Integer.MAX_VALUE);
			}
			List<ItemDto> items = itemMapper.listItem(params);
			result.setList(items);
		}
		return result;
	}

}
