package com.haige.luban.bo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WechatLoginBo {
	
	@JsonProperty("session_key") 
	private String sessionKey;
	
	@JsonProperty("openid") 
	private String openId;
}
