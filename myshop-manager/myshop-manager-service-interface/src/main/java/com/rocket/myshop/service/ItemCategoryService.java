package com.rocket.myshop.service;


import java.util.Map;

import com.rocket.myshop.dto.common.ShopResult;

public interface ItemCategoryService {

	ShopResult listItemCategory(Map<String, Object> params);
}
