package com.haige.luban.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.haige.luban.pojo.Message;


public interface MessageJpaDao extends JpaRepository<Message, Long>,JpaSpecificationExecutor<Message> {

}
