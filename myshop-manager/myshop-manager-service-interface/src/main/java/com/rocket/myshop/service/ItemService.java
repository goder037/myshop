package com.rocket.myshop.service;


import com.rocket.myshop.domain.Item;
import com.rocket.myshop.dto.common.DataTablesOutput;

public interface ItemService {

	DataTablesOutput<Item> listItem();
}
