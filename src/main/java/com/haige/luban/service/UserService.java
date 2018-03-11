package com.haige.luban.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.haige.luban.bo.MyStatus;
import com.haige.luban.pojo.User;

public interface UserService {
	
	User addUser(User user);
	
	void deleteUser(User user);
	
	User updateUser(User user) throws IllegalAccessException, InvocationTargetException;
	
	User getUserById(Long id);
	
	User getUserByOpenId(String openId);
	
	Page<User> findAllUser(int start, int size);
	
	List<User> findAllEmployer();
	
	List<User> findAllWorker();
	
	MyStatus getMyStatus(User user);
}
