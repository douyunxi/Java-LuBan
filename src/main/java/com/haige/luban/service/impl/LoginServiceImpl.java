package com.haige.luban.service.impl;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.haige.luban.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${wechat_login_url}") 
	private String WECHAT_LOGIN_URL;
	/**
	 * 微信小程序登录
	 * @param code
	 * @return
	 */
	public Object login(String code) {
		HttpClient client = HttpClients.createDefault();
		logger.info(WECHAT_LOGIN_URL);
		return null;
	}
}
