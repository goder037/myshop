package com.rocket.myshop.mapper;

import com.rocket.myshop.domain.Item;

public interface ItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_item
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_item
     *
     * @mbg.generated
     */
    int insert(Item record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_item
     *
     * @mbg.generated
     */
    int insertSelective(Item record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_item
     *
     * @mbg.generated
     */
    Item selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_item
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Item record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_item
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Item record);
}