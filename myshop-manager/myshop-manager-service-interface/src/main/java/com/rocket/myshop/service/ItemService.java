package com.rocket.myshop.service;


import java.util.Map;

import com.rocket.myshop.dto.common.ShopResult;

public interface ItemService {

	ShopResult listItem(Map<String, Object> params);
}
