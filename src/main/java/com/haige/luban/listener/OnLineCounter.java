package com.haige.luban.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class OnLineCounter implements HttpSessionListener {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static Long onLineCount=0L;

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		onLineCount++;
		logger.info("当前在线人数:"+onLineCount);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		onLineCount--;
		logger.info("当前在线人数:"+onLineCount);
	}

}
