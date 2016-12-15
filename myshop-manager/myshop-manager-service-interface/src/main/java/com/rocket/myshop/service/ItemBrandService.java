package com.rocket.myshop.service;

import java.util.Map;

import com.rocket.myshop.dto.common.ShopQueryResult;

public interface ItemBrandService {

	ShopQueryResult listItemBrands(Map<String, Object> params);
}
