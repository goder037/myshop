package com.rocket.myshop.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.rocket.myshop.domain.ItemCategory;
import com.rocket.myshop.dto.ItemCategoryDto;
import com.rocket.myshop.dto.common.ShopQueryResult;
import com.rocket.myshop.dto.common.ShopTxResult;
import com.rocket.myshop.mapper.ItemCategoryMapper;
import com.rocket.myshop.service.ItemCategoryService;

@Service("ItemCategoryService")
public class ItemCategoryServiceImpl implements ItemCategoryService {
	
	@Resource
	ItemCategoryMapper itemCategoryMapper;

	@Override
	public ShopQueryResult listItemCategory(Map<String, Object> params) {
		int count = itemCategoryMapper.getCount(params);
		ShopQueryResult result = new ShopQueryResult();
		result.setTotal(count);
		if (count > 0) {
			Integer length = (Integer) params.get("length");
			if(length!=null && length == -1){
				 params.put("length", Integer.MAX_VALUE);
			}
			List<ItemCategoryDto> data = itemCategoryMapper.listItemCategory(params);
			result.setList(data);
		}
		result.setSuccess(true);
		result.setVersion("1.0.0");
		return result;
	}

	@Override
	public ShopQueryResult getSubItemsCategory(Integer parentId) {
		ShopQueryResult result = new ShopQueryResult();
		List<ItemCategoryDto> itemCategorys = itemCategoryMapper.getSubItemsCategory(parentId);
		result.setList(itemCategorys);
		result.setSuccess(true);
		result.setVersion("1.0.0");
		return result;
	}

	@Override
	public ShopTxResult addItemsCategory(ItemCategoryDto itemCategoryDto) throws Exception{
		ShopTxResult result = new ShopTxResult();
		result.setVersion("1.0.0");
		boolean existFlag = checkCategoryNameIsExist(itemCategoryDto.getName(), null);
		if(existFlag){
			result.setSuccess(false);
			Map<String, Object> reasons = new HashMap<>();
			reasons.put("name", "商品类别名称已存在！");
			result.setReasons(reasons);
		}else{
			ItemCategory itemCategory = new ItemCategory();
			BeanUtils.copyProperties(itemCategory, itemCategoryDto);
			Integer parentId = itemCategory.getParentId();
			byte level = 1;
			if(parentId!=null){
				//更新父类别中的子类别数目，子类别更新时间
				ItemCategory itemCategoryParent = itemCategoryMapper.selectByPrimaryKey(parentId);
				short subCategoryNum = itemCategoryParent.getSubCategoryNum().shortValue();
				subCategoryNum +=level;
				itemCategoryParent.setSubCategoryNum(subCategoryNum);
				Byte levelParent = itemCategoryParent.getLevel();
				level += levelParent.byteValue();
				itemCategoryParent.setUpdateTime(new Date());
				itemCategoryMapper.updateByPrimaryKey(itemCategoryParent);
			}
			itemCategory.setLevel(level);
			int insertCount = itemCategoryMapper.insertSelective(itemCategory);
			result.setMessage("成功添加"+insertCount+"记录");
			result.setSuccess(true);
		}
		return result;
	}

	private boolean checkCategoryNameIsExist(String name, Integer categoryId) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", name);
		params.put("categoryId", categoryId);
		int count = itemCategoryMapper.checkCategoryNameIsExist(params);
		return count > 0 ? true : false;
	}

	@Override
	public ShopTxResult deleteItemsCategory(Integer categoryId) {
		ItemCategory itemCategory = itemCategoryMapper.selectByPrimaryKey(categoryId);
		Integer parentId = itemCategory.getParentId();
		if(parentId!=null){
			ItemCategory itemCategoryParent = itemCategoryMapper.selectByPrimaryKey(parentId);
			itemCategoryParent.setUpdateTime(new Date());
			Short subCategoryNum = itemCategoryParent.getSubCategoryNum();
			subCategoryNum--;
			itemCategoryParent.setSubCategoryNum(subCategoryNum);
			itemCategoryMapper.updateByPrimaryKey(itemCategoryParent);
		}
		itemCategory.setStatus(false);
		int updateCount = itemCategoryMapper.updateByPrimaryKey(itemCategory);
		ShopTxResult result = new ShopTxResult();
		result.setMessage("成功删除"+updateCount+"记录");
		result.setSuccess(true);
		result.setVersion("1.0.0");
		return result;
	}

	@Override
	public ShopTxResult updateItemsCategory(ItemCategoryDto itemCategoryDto) throws Exception{
		ShopTxResult result = new ShopTxResult();
		boolean existFlag = checkCategoryNameIsExist(itemCategoryDto.getName(), itemCategoryDto.getId());
		result.setVersion("1.0.0");
		if(existFlag){
			result.setSuccess(false);
			Map<String, Object> reasons = new HashMap<>();
			reasons.put("name", "商品类别名称已存在！");
			result.setReasons(reasons);
		}else{
			ItemCategory itemCategory = new ItemCategory();
			BeanUtils.copyProperties(itemCategory, itemCategoryDto);
			int updateCount = itemCategoryMapper.updateByPrimaryKey(itemCategory);
			result.setMessage("成功删除"+updateCount+"记录");
			result.setSuccess(true);
		}
		return result;
	}
}
