package com.haige.luban.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.haige.luban.dao.CartJpaDao;
import com.haige.luban.dao.GoodsCartRelationJpaDao;
import com.haige.luban.pojo.Cart;
import com.haige.luban.pojo.Goods;
import com.haige.luban.pojo.GoodsCartRelation;
import com.haige.luban.service.CartService;
import com.haige.luban.util.UpdateUtil;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
    private CartJpaDao cartJpaDao;
	
	@Autowired
	private GoodsCartRelationJpaDao goodsCartRelationJpaDao;
	
	@Override
	public Cart addCart(Cart cart) {
		return cartJpaDao.save(cart);
	}

	@Override
	@Transactional
	public void deleteCart(Cart cart) {
		cartJpaDao.delete(cart);
	}

	@Override
	public Cart updateCart(Cart cart) throws IllegalAccessException, InvocationTargetException {
		Cart oldCart=cartJpaDao.getOne(cart.getId());
		UpdateUtil.copyNonNullProperties(cart, oldCart);
		return cartJpaDao.saveAndFlush(oldCart);
	}

	@Override
	public Cart getCartById(Long id) {
		return cartJpaDao.getOne(id);
	}

	@Override
	public Page<Cart> findAllCart(int start, int size) {
		return cartJpaDao.findAll(PageRequest.of(start/size, size));
	}

	@Override
	public GoodsCartRelation addGoods(Cart cart, Goods goods,Long amount) {
		GoodsCartRelation goodsCartRelation=new GoodsCartRelation();
		goodsCartRelation.setCart(cart);
		goodsCartRelation.setGoods(goods);
		goodsCartRelation.setAmount(amount);
		goodsCartRelation.setPrice(goods.getPrice());//加入时价格
		goodsCartRelation.setAddTime(new Date());
		return goodsCartRelationJpaDao.save(goodsCartRelation);
	}

	@Override
	public Set<GoodsCartRelation> getGoodsDetail(Cart cart) {
		return cart.getGoodsCartRelation();
	}

}
