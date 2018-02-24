package com.haige.luban.service;

import java.util.List;

import com.haige.luban.pojo.User;

public interface UserService {
	
	User createUser(User user);
	
	Boolean removeUser(User user);
	
	User updateUser(User user);
	
	User getUserById(Long id);
	
	User getUserByOpenId(String openId);
	
	List<User> searchUser(User user);
	
}
