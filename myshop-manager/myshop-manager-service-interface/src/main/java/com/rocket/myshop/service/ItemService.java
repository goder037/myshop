package com.rocket.myshop.service;


import java.util.Map;

import com.rocket.myshop.dto.common.ShopQueryResult;

public interface ItemService {

	ShopQueryResult listItem(Map<String, Object> params);
}
