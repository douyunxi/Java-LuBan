package com.haige.luban.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haige.luban.dao.GoodsJpaDao;
import com.haige.luban.pojo.Goods;
import com.haige.luban.service.GoodsService;
import com.haige.luban.util.UpdateUtil;

@Service
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
    private GoodsJpaDao goodsJpaDao;
	
	@Override
	public Goods addGoods(Goods goods) {
		Date now=new Date();
		goods.setCreateTime(now);
		goods.setUpdateTime(now);
		return goodsJpaDao.save(goods);
	}

	@Override
	@Transactional
	public void deleteGoods(Goods goods) {
		goodsJpaDao.delete(goods);
	}

	@Override
	public Goods updateGoods(Goods goods) throws IllegalAccessException, InvocationTargetException {
		Goods oldGoods=goodsJpaDao.getOne(goods.getId());
		UpdateUtil.copyNonNullProperties(goods, oldGoods);
		oldGoods.setUpdateTime(new Date());
		return goodsJpaDao.saveAndFlush(oldGoods);
	}

	@Override
	public Goods getGoodsById(Long id) {
		return goodsJpaDao.getOne(id);
	}

	@Override
	public Page<Goods> findAllGoods(int start, int size) {
		return goodsJpaDao.findAll(PageRequest.of(start/size, size));
	}

}
