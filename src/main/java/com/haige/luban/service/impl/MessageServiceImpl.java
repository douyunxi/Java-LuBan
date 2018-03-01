package com.haige.luban.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haige.luban.dao.MessageJpaDao;
import com.haige.luban.pojo.Message;
import com.haige.luban.pojo.User;
import com.haige.luban.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageJpaDao messageJpaDao;

	@Override
	public Message addMessage(Message message) {
		return messageJpaDao.save(message);
	}

	@Override
	public void deleteMessage(Message message) {
		messageJpaDao.delete(message);
	}

	@Override
	public Message updateMessage(Message message) {
		return messageJpaDao.saveAndFlush(message);
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
		return messageJpaDao.findByReceiver(user);
	}

}
