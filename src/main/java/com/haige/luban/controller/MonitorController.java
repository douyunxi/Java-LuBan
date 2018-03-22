package com.haige.luban.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/monitor")
public class MonitorController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 管理员登录后台管理系统--监控页面
	 */
	@RequestMapping("")
	String monitor() {
		logger.debug("监控页面");
		return "monitor";
	}
	
}
