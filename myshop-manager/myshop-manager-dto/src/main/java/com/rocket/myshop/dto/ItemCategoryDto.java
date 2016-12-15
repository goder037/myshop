package com.rocket.myshop.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rocket.myshop.common.validattion.FirstValid;
import com.rocket.myshop.common.validattion.SecondValid;

@Data
public class ItemCategoryDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotBlank(message="{itemcategory.name.empty}", groups=FirstValid.class)
    private String name;

    private Boolean status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @NotNull(message="{itemcategory.parentId.empty}", groups=SecondValid.class)
    private Integer parentId;
    
    private Integer itemsNum;

    private Short rank;

    private Byte level;
    
    private Short subCategoryNum;

}