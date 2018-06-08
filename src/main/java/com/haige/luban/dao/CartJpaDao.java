package com.haige.luban.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.haige.luban.pojo.Cart;

public interface CartJpaDao extends JpaRepository<Cart, Long>, JpaSpecificationExecutor<Cart> {

}
