package com.rocket.myshop.mapper;

import java.util.List;
import java.util.Map;

import com.rocket.myshop.domain.ItemCategory;
import com.rocket.myshop.dto.ItemCategoryDto;

public interface ItemCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_item_category
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_item_category
     *
     * @mbg.generated
     */
    int insert(ItemCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_item_category
     *
     * @mbg.generated
     */
    int insertSelective(ItemCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_item_category
     *
     * @mbg.generated
     */
    ItemCategory selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_item_category
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ItemCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_item_category
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ItemCategory record);

	int getCount(Map<String, Object> params);

	List<ItemCategoryDto> getSubItemsCategory(Integer parentId);

	List<ItemCategoryDto> listItemCategory(Map<String, Object> params);

	int checkCategoryNameIsExist(Map<String, Object> params);
}