package com.rocket.myshop.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rocket.myshop.common.domain.ShopResult;
import com.rocket.myshop.domain.Demo;
import com.rocket.myshop.mapper.DemoMapper;

@Service
public class DemoSerciceImpl implements DemoService{

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
