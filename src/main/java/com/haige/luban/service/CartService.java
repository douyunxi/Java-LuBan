package com.haige.luban.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.haige.luban.pojo.Cart;
import com.haige.luban.pojo.Goods;
import com.haige.luban.pojo.GoodsCartRelation;

public interface CartService {
	
	Cart addCart(Cart cart);
	
	void deleteCart(Cart cart);
	
	Cart updateCart(Cart cart) throws IllegalAccessException, InvocationTargetException;
	
	Cart getCartById(Long id);
	
	Page<Cart> findAllCart(int start, int size);
	
	/**
	 * 向某个用户的购物车中添加商品
	 * @param cart 购物车
	 * @param goods 商品
	 * @param amount 加入数量
	 * @return
	 */
	GoodsCartRelation addGoods(Cart cart,Goods goods,Long amount);
	
	/**
	 * 获取购物车中所有商品详情
	 * @param cart
	 * @return
	 */
	Set<GoodsCartRelation> getGoodsDetail(Cart cart);
}
