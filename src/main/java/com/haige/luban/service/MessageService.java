package com.haige.luban.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.haige.luban.pojo.Message;
import com.haige.luban.pojo.User;

public interface MessageService {
	
	Message addMessage(Message message,String reciver);
	
	void deleteMessage(Message message);
	
	Message updateMessage(Message message,String reciver);
	
	Message publishMessage(Message message);
	
	void readMessage(User user,Message message);
	
	Message getMessageById(Long id);
	
	List<Message> searchMessage(Message message);
	
	List<Message> getMessagesByReceiver(User user);
	
	/**
	 * 分页获得所有消息
	 * @param page
	 * @param size
	 * @return
	 */
	Page<Message> findAllMessages(int start, int size);
}
