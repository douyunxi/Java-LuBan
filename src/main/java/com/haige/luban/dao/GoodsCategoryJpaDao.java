package com.haige.luban.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.haige.luban.pojo.GoodsCategory;

public interface GoodsCategoryJpaDao extends JpaRepository<GoodsCategory, Long>, JpaSpecificationExecutor<GoodsCategory> {

}
