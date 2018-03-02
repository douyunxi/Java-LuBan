package com.haige.luban.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.haige.luban.bo.MyStatus;
import com.haige.luban.dao.MessageJpaDao;
import com.haige.luban.dao.UserJpaDao;
import com.haige.luban.pojo.User;
import com.haige.luban.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserJpaDao userJpaDao;
	
	@Autowired
    private MessageJpaDao messageJpaDao;

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
	public Page<User> findAllUser(int page,int size) {
		return userJpaDao.findAll(PageRequest.of(page, size));
	}

	@Override
	public MyStatus getMyStatus(User user) {
		MyStatus myStatus=new MyStatus();
		myStatus.setMessages(messageJpaDao.countByReceiver(user));
		return myStatus;
	}

}
