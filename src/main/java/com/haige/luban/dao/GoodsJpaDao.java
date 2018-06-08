package com.haige.luban.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.haige.luban.pojo.Goods;

public interface GoodsJpaDao extends JpaRepository<Goods, Long>, JpaSpecificationExecutor<Goods> {

}
