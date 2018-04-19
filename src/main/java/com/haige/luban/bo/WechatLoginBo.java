package com.haige.luban.bo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WechatLoginBo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("session_key") 
	private String sessionKey;
	
	@JsonProperty("openid") 
	private String openId;
}
