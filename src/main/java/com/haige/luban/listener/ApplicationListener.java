package com.haige.luban.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class ApplicationListener implements ServletContextListener {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("服务已启动");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("服务已停止");
	}

}
