package com.haige.luban;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableRedisHttpSession
@EnableTransactionManagement //启用事物管理
@ServletComponentScan	//过滤器、servlet、监听器扫描
@SpringBootApplication
@ImportResource(locations = {"classpath:tiles.xml"})
public class LuBanApplication {
	public static void main(String[] args) throws Exception {
        SpringApplication.run(LuBanApplication.class, args);
    }
}
