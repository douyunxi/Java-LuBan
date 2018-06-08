package com.haige.luban.service.impl;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.haige.luban.dao.GoodsCategoryJpaDao;
import com.haige.luban.pojo.GoodsCategory;
import com.haige.luban.service.GoodsCategoryService;

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
	
	@Autowired
	private GoodsCategoryJpaDao goodsCategoryJpaDao;

	@Override
	public GoodsCategory addGoodsCategory(GoodsCategory category) {
		return goodsCategoryJpaDao.save(category);
	}

	@Override
	public void deleteGoodsCategory(GoodsCategory category) {
		goodsCategoryJpaDao.delete(category);
	}

	@Override
	public GoodsCategory updateGoodsCategory(GoodsCategory category)
			throws IllegalAccessException, InvocationTargetException {
		return goodsCategoryJpaDao.saveAndFlush(category);
	}

	@Override
	public GoodsCategory getGoodsCategoryById(Long id) {
		return goodsCategoryJpaDao.getOne(id);
	}

	@Override
	public Page<GoodsCategory> findAllGoodsCategory(int start, int size) {
		return goodsCategoryJpaDao.findAll(PageRequest.of(start/size, size));
	}

}
