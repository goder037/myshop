package com.rocket.myshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rocket.myshop.domain.Demo;
import com.rocket.myshop.dto.common.DataTablesOutput;
import com.rocket.myshop.mapper.DemoMapper;
import com.rocket.myshop.service.DemoService;

@Service("DemoService")
public class DemoServiceImpl implements DemoService{

	@Resource
	DemoMapper demoMapper;
	
	@Override
	public DataTablesOutput<Demo> listDemo() {
		int count = demoMapper.getCount();
		DataTablesOutput<Demo> result = new DataTablesOutput<Demo>();
		result.setRecordsTotal(count);
		if(count>0){
			List<Demo> data = demoMapper.listAllDemo();
			result.setData(data);
		}
		return result;
	}

}
