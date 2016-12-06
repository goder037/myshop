package com.rocket.myshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rocket.myshop.dto.common.ShopResult;
import com.rocket.myshop.domain.Demo;
import com.rocket.myshop.mapper.DemoMapper;
import com.rocket.myshop.service.DemoService;

@Service("DemoService")
public class DemoServiceImpl implements DemoService{

	@Resource
	DemoMapper demoMapper;
	
	@Override
	public ShopResult listDemo() {
		int count = demoMapper.getCount();
		ShopResult result = new ShopResult();
		result.setTotal(count);
		if(count>0){
			List<Demo> listAllDemo = demoMapper.listAllDemo();
			result.setList(listAllDemo);
		}
		return result;
	}

}
