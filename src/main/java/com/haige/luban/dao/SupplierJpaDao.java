package com.haige.luban.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.haige.luban.pojo.Supplier;

public interface SupplierJpaDao extends JpaRepository<Supplier, Long>, JpaSpecificationExecutor<Supplier> {

}
