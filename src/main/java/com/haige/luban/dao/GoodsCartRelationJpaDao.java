package com.haige.luban.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.haige.luban.pojo.GoodsCartRelation;

public interface GoodsCartRelationJpaDao extends JpaRepository<GoodsCartRelation, Long>,JpaSpecificationExecutor<GoodsCartRelation> {
	
	/**
	 * 清空某个用户的购物车
	 * @param cartId
	 */
	@Modifying
    @Query("delete from GoodsCartRelation r where r.cart.id = ?1")
	void deleteByCartId(Long cartId);
	
	/**
	 * 删除某个用户购物车中的某件商品
	 * @param cartId
	 * @param goodsId
	 */
	@Modifying
	@Query("delete from GoodsCartRelation r where r.cart.id = ?1 and r.goods.id=?2")
	void deleteByCartIdAndGoodsId(Long cartId,Long goodsId);
}
