package com.haige.luban.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.haige.luban.pojo.Order;

public interface OrderJpaDao extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

}
