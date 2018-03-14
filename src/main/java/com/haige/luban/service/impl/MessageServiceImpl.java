package com.haige.luban.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.haige.luban.dao.MessageJpaDao;
import com.haige.luban.dao.UserJpaDao;
import com.haige.luban.dao.UserMessageRelationJpaDao;
import com.haige.luban.enums.EnumMessagePublishStatus;
import com.haige.luban.enums.EnumMessageStatus;
import com.haige.luban.enums.EnumUserType;
import com.haige.luban.pojo.Message;
import com.haige.luban.pojo.User;
import com.haige.luban.pojo.UserMessageRelation;
import com.haige.luban.service.MessageService;
import com.haige.luban.util.UpdateUtil;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private UserJpaDao userJpaDao;
	
	@Autowired
	private MessageJpaDao messageJpaDao;
	
	@Autowired
	private UserMessageRelationJpaDao userMessageRelationJpaDao;

	@Override
	public Message addMessage(Message message,String reciver) {
		message.setCreateTime(new Date());
		message.setPublishStatus(EnumMessagePublishStatus.UNPUBLISHED);
		Message newMessage=messageJpaDao.save(message);
		List<UserMessageRelation> relations=new LinkedList<UserMessageRelation>();
		List<User> users=null;
		if(reciver.equals("ALL")) {
			users=userJpaDao.findAll();
		}
		else if(reciver.equals("WORKER")) {
			users=userJpaDao.findByType(EnumUserType.WORKER);
		}
		else if(reciver.equals("EMPLOYER")) {
			users=userJpaDao.findByType(EnumUserType.EMPLOYER);
		}
		else if(reciver.equals("ADMIN")) {
			users=userJpaDao.findByType(EnumUserType.ADMIN);
		}
		for(User user:users) {
			UserMessageRelation relation=new UserMessageRelation();
			relation.setMessage(newMessage);
			relation.setUser(user);
			relation.setStatus(EnumMessageStatus.UNREAD);
			relations.add(relation);
		}
		userMessageRelationJpaDao.saveAll(relations);
		return newMessage;
	}

	@Override
	public void deleteMessage(Message message) {
		messageJpaDao.delete(message);
	}

	@Override
	@Transactional
	public Message updateMessage(Message message,String reciver) {
		Message oldMessage=messageJpaDao.getOne(message.getId());
		UpdateUtil.copyNonNullProperties(message, oldMessage);
		oldMessage.setCreateTime(new Date());
		Message newMessage=messageJpaDao.save(oldMessage);
		//Set<Message> messageSet=new HashSet<Message>();
		//messageSet.add(message);
		userMessageRelationJpaDao.deleteByMessageId(message.getId());;
		List<UserMessageRelation> relations=new LinkedList<UserMessageRelation>();
		List<User> users=null;
		if(reciver.equals("ALL")) {
			users=userJpaDao.findAll();
		}
		else if(reciver.equals("WORKER")) {
			users=userJpaDao.findByType(EnumUserType.WORKER);
		}
		else if(reciver.equals("EMPLOYER")) {
			users=userJpaDao.findByType(EnumUserType.EMPLOYER);
		}
		else if(reciver.equals("ADMIN")) {
			users=userJpaDao.findByType(EnumUserType.ADMIN);
		}
		for(User user:users) {
			UserMessageRelation relation=new UserMessageRelation();
			relation.setMessage(newMessage);
			relation.setUser(user);
			relation.setStatus(EnumMessageStatus.UNREAD);
			relations.add(relation);
			//userMessageRelationJpaDao.save(relation);
		}
		//userMessageRelationJpaDao.flush();
		userMessageRelationJpaDao.saveAll(relations);
		return newMessage;
	}
	
	@Override
	public Message publishMessage(Message message) {
		Message oldMessage=messageJpaDao.getOne(message.getId());
		UpdateUtil.copyNonNullProperties(message, oldMessage);
		oldMessage.setPublishTime(new Date());
		oldMessage.setPublishStatus(EnumMessagePublishStatus.PUBLISHED);
		return messageJpaDao.saveAndFlush(oldMessage);
	}
	
	@Override
	public void readMessage(User user,Message message) {
		UserMessageRelation relation=userMessageRelationJpaDao.findByUserAndMessage(user, message);
		relation.setStatus(EnumMessageStatus.READ);
		userMessageRelationJpaDao.saveAndFlush(relation);
	}

	@Override
	public Message getMessageById(Long id) {
		return messageJpaDao.getOne(id);
	}

	@Override
	public List<Message> searchMessage(Message message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getMessagesByReceiver(User user) {
		List<Message> messages=new LinkedList<Message>();
		List<UserMessageRelation> userMessageRelations=userMessageRelationJpaDao.findByUserAndMessage_PublishStatus(user, EnumMessagePublishStatus.PUBLISHED);
		for(UserMessageRelation relation:userMessageRelations) {
			Message message=relation.getMessage();
			messages.add(message);
		}
		return messages;
	}

	@Override
	public Page<Message> findAllMessages(int start, int size) {
		return messageJpaDao.findAll(PageRequest.of(start/size, size));
	}

}
