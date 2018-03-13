package com.haige.luban.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.haige.luban.enums.EnumMessagePublishStatus;
import com.haige.luban.enums.EnumMessageStatus;
import com.haige.luban.pojo.Message;
import com.haige.luban.pojo.User;
import com.haige.luban.pojo.UserMessageRelation;

public interface UserMessageRelationJpaDao extends JpaRepository<UserMessageRelation, Long>,JpaSpecificationExecutor<UserMessageRelation> {
	/**
	 * 根据用户查找所有已发布的消息
	 * @param receiver
	 * @param status
	 * @param publishStatus
	 * @return
	 */
	List<UserMessageRelation> findByUserAndMessage_PublishStatus(User user,EnumMessagePublishStatus publishStatus);
	
	/**
	 * 根据用户，消息是否已读，消息的发布状态查找消息
	 * @param receiver
	 * @param status
	 * @param publishStatus
	 * @return
	 */
	List<UserMessageRelation> findByUserAndStatusAndMessage_PublishStatus(User user,EnumMessageStatus status,EnumMessagePublishStatus publishStatus);
	
	/**
	 * 根据用户，消息是否已读，消息的发布状态统计数量
	 * @param receiver
	 * @param status
	 * @param publishStatus
	 * @return
	 */
	Long countByUserAndStatusAndMessage_PublishStatus(User receiver,EnumMessageStatus status,EnumMessagePublishStatus publishStatus);
	
	/**
	 * 根据消息ID,消息是否已读查找消息
	 * @param receiver
	 * @param status
	 * @param publishStatus
	 * @return
	 */
	List<UserMessageRelation> findByMessageAndStatus(Message message,EnumMessageStatus status);
	
	/**
	 * 根据消息ID,消息是否已读统计数量
	 * @param receiver
	 * @param status
	 * @param publishStatus
	 * @return
	 */
	Long countByMessageAndStatus(Message message,EnumMessageStatus status);
	
	@Modifying
    @Query("delete from UserMessageRelation r where r.message.id = ?1")
	void deleteByMessageId(Long messageId);
}
