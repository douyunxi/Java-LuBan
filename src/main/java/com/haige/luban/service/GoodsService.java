package com.haige.luban.service;

import java.lang.reflect.InvocationTargetException;

import org.springframework.data.domain.Page;

import com.haige.luban.pojo.Goods;

public interface GoodsService {
	
	Goods addGoods(Goods goods);
	
	void deleteGoods(Goods goods);
	
	Goods updateGoods(Goods goods) throws IllegalAccessException, InvocationTargetException;
	
	Goods getGoodsById(Long id);
	
	Page<Goods> findAllGoods(int start, int size);
}
