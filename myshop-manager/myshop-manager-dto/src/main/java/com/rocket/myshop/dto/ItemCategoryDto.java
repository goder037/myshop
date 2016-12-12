package com.rocket.myshop.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class ItemCategoryDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;

    private Boolean status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private Integer parentId;
    
    private Integer itemsNum;

    private Short rank;

    private Byte level;

}