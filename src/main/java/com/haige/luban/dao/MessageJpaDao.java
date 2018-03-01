package com.haige.luban.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.haige.luban.pojo.Message;
import com.haige.luban.pojo.User;

public interface MessageJpaDao extends JpaRepository<Message, Long>,JpaSpecificationExecutor<Message> {
	
	List<Message> findByReceiver(User receiver);
	
	Long countByReceiver(User receiver);
}
