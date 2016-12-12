package com.rocket.myshop.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto implements Serializable{

	private static final long serialVersionUID = 1190711487113704215L;

	private Integer id;

    private String title;

    private String sellPoint;

    private BigDecimal price;

    private Integer num;

    private String barcode;

    private String img;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    
    private String sellerId;
    
    private BigDecimal currentPrice;
    
    private String shopUrl;
    
    private int itemFavorite;
    
    private String appId;
    
    private String shopLogo;
    
    
}
