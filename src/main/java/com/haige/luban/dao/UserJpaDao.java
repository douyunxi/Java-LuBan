package com.haige.luban.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.haige.luban.enums.EnumUserType;
import com.haige.luban.pojo.User;

public interface UserJpaDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	User findByOpenId(String openId);

	List<User> findByType(EnumUserType type);
}
