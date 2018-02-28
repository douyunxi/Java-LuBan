package com.haige.luban.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haige.luban.dao.UserJpaDao;
import com.haige.luban.pojo.User;
import com.haige.luban.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserJpaDao userJpaDao;

	@Override
	public User addUser(User user) {
		Date now=new Date();
		user.setCreateTime(now);
		user.setUpdateTime(now);
		return userJpaDao.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userJpaDao.delete(user);
	}

	@Override
	public User updateUser(User user) {
		return userJpaDao.saveAndFlush(user);
	}

	@Override
	public User getUserById(Long id) {
		return userJpaDao.getOne(id);
	}

	@Override
	public User getUserByOpenId(String openId) {
		return userJpaDao.findByOpenId(openId);
	}

	@Override
	public List<User> searchUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
