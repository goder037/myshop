package com.rocket.myshop.dto.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ShopResult implements Serializable {

	private static final long serialVersionUID = 397411232804869934L;

	private int total;

	private List<?> list;

	// 第一条数据的起始位置，比如0代表第一条数据
	private int start;

	/**
	 * 告诉服务器每页显示的条数，这个数字会等于返回的 data集合的记录数，
	 * 可能会大于因为服务器可能没有那么多数据。这个也可能是-1，代表需要返回全部数据
	 */
	private int length;

	private Map<String, Object> queryParam;
	
	private String version;
	
	private boolean success;
	
}
