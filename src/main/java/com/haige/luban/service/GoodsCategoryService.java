package com.haige.luban.service;

import java.lang.reflect.InvocationTargetException;

import org.springframework.data.domain.Page;

import com.haige.luban.pojo.GoodsCategory;

public interface GoodsCategoryService {
	
	GoodsCategory addGoodsCategory(GoodsCategory category);
	
	void deleteGoodsCategory(GoodsCategory category);
	
	GoodsCategory updateGoodsCategory(GoodsCategory category) throws IllegalAccessException, InvocationTargetException;
	
	GoodsCategory getGoodsCategoryById(Long id);
	
	Page<GoodsCategory> findAllGoodsCategory(int start, int size);
}
