package com.haige.luban.service;

import java.util.List;

import com.haige.luban.pojo.Message;
import com.haige.luban.pojo.User;

public interface MessageService {
	
	Message addMessage(Message message);
	
	void deleteMessage(Message message);
	
	Message updateMessage(Message message);
	
	Message getMessageById(Long id);
	
	List<Message> searchMessage(Message message);
	
	List<Message> getMessagesByReceiver(User user);
	
}
