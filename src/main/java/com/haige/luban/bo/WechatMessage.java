package com.haige.luban.bo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class WechatMessage {
	
	private Long id;
	
	private String type;
	
	private String title;
	
	private String content;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date time;
}
