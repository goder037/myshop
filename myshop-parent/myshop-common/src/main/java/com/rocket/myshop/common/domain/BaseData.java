package com.rocket.myshop.common.domain;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class BaseData implements Serializable {

	private static final long serialVersionUID = -3082839953347120582L;

	private int total;

	private List<Object> data;
	
}
