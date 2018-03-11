package com.haige.luban.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.haige.luban.enums.EnumMessageStatus;
import com.haige.luban.pojo.Message;
import com.haige.luban.pojo.User;
import com.haige.luban.pojo.UserMessageRelation;

public interface UserMessageRelationJpaDao extends JpaRepository<UserMessageRelation, Long>,JpaSpecificationExecutor<UserMessageRelation> {
	
	List<UserMessageRelation> findByUser(User user);
	
	List<UserMessageRelation> findByUserAndStatus(User user,EnumMessageStatus status);
	
	Long countByUserAndStatus(User receiver,EnumMessageStatus status);
	
	List<UserMessageRelation> findByMessageAndStatus(Message message,EnumMessageStatus status);
	
	Long countByMessageAndStatus(Message message,EnumMessageStatus status);
	
	@Modifying
    @Query("delete from UserMessageRelation r where r.message.id = ?1")
	void deleteByMessageId(Long messageId);
}
