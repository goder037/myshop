package com.rocket.myshop.dto.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @FullClassName com.rocket.myshop.dto.common.ShopTxResult
* @Description: 针对增删改带有事务的操作统一返回数据格式
* @author  Liu Jie
* @date 2016年12月12日 下午9:59:03 
* @version V1.0.0
 */
@Data
@NoArgsConstructor
public class ShopTxResult implements Serializable{
	
	private static final long serialVersionUID = 713192640075470698L;

	private String message = "操作失败！";
	
	private Map<String, Object> reasons = new HashMap<>();
	
	private String version;
	
	private boolean success;
	
	public ShopTxResult(BindingResult errors){
		List<FieldError> fieldErrors = errors.getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			getReasons().put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		this.success = false;
		this.version="1.0.0";
	}
}
