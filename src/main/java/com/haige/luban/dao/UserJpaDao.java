package com.haige.luban.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.haige.luban.pojo.User;

public interface UserJpaDao extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

}
