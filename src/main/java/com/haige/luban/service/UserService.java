package com.haige.luban.service;

import org.springframework.data.domain.Page;

import com.haige.luban.bo.MyStatus;
import com.haige.luban.pojo.User;

public interface UserService {
	
	User addUser(User user);
	
	void deleteUser(User user);
	
	User updateUser(User user);
	
	User getUserById(Long id);
	
	User getUserByOpenId(String openId);
	
	Page<User> findAllUser(int page,int size);
	
	MyStatus getMyStatus(User user);
}
