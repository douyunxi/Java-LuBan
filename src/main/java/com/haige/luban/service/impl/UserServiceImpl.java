package com.haige.luban.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.haige.luban.bo.MyStatus;
import com.haige.luban.dao.UserMessageRelationJpaDao;
import com.haige.luban.dao.TaskJpaDao;
import com.haige.luban.dao.UserJpaDao;
import com.haige.luban.enums.EnumMessagePublishStatus;
import com.haige.luban.enums.EnumMessageStatus;
import com.haige.luban.enums.EnumTaskStatus;
import com.haige.luban.enums.EnumUserType;
import com.haige.luban.pojo.User;
import com.haige.luban.service.UserService;
import com.haige.luban.util.UpdateUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserJpaDao userJpaDao;
	
	@Autowired
	private UserMessageRelationJpaDao userMessageRelationJpaDao;
	
	@Autowired
	private TaskJpaDao taskJpaDao;

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
	public User updateUser(User user) throws IllegalAccessException, InvocationTargetException {
		User oldUser=userJpaDao.getOne(user.getId());
		UpdateUtil.copyNonNullProperties(user, oldUser);
		oldUser.setUpdateTime(new Date());
		return userJpaDao.saveAndFlush(oldUser);
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
	public Page<User> findAllUser(int start, int size) {
		return userJpaDao.findAll(PageRequest.of(start/size, size));
	}

	@Override
	public MyStatus getMyStatus(User user) {
		MyStatus myStatus=new MyStatus();
		if(user!=null) {
			//获取用户未读通知数
			Long messagesCount=userMessageRelationJpaDao.countByUserAndStatusAndMessage_PublishStatus(user, EnumMessageStatus.UNREAD,EnumMessagePublishStatus.PUBLISHED);
			//获取用户未接任务数
			Long tasksCount=taskJpaDao.countByWorkerAndStatus(user, EnumTaskStatus.DISPATCHED);
			myStatus.setMessages(messagesCount+tasksCount);
			//未完成=已接单+进行中
			Long taskReceiptCount=taskJpaDao.countByWorkerAndStatus(user, EnumTaskStatus.RECEIPT);
			Long taskProcessingCount=taskJpaDao.countByWorkerAndStatus(user, EnumTaskStatus.PROCESSING);
			Long taskUnfinishiedCount=taskReceiptCount+taskProcessingCount;
			myStatus.setTasks(taskUnfinishiedCount);
			//未入账=已接单+审核中
			//myStatus.setSalaries(salaries);
		}
		return myStatus;
	}

	@Override
	public List<User> findAllEmployer() {
		return userJpaDao.findByType(EnumUserType.EMPLOYER);
	}

	@Override
	public List<User> findAllWorker() {
		return userJpaDao.findByType(EnumUserType.WORKER);
	}

}
