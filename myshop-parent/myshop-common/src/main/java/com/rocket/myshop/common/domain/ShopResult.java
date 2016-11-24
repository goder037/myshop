package com.rocket.myshop.common.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(content=Include.NON_NULL)
public class ShopResult implements Serializable{

	private static final long serialVersionUID = 2029049093970770253L;

	private int total;

	private List<?> list;
	
	private int page;
	
	private int limit;
	
	private Map<String, Object> queryParam;
	
}
