package com.rocket.myshop.service;


import com.rocket.myshop.domain.Demo;
import com.rocket.myshop.dto.common.DataTablesOutput;

public interface DemoService {

	DataTablesOutput<Demo> listDemo();
}
