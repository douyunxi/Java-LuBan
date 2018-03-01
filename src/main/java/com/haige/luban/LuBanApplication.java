package com.haige.luban;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:tiles.xml"}) 
public class LuBanApplication {
	public static void main(String[] args) throws Exception {
        SpringApplication.run(LuBanApplication.class, args);
    }
}
