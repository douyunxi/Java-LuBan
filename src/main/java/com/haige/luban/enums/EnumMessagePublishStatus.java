package com.haige.luban.enums;

/**
 * 消息发布状态枚举
 * @author linwei
 *
 */
public enum EnumMessagePublishStatus {
	
	UNPUBLISHED(0,"未发布"),PUBLISHED(1,"已发布");
	
	private Integer code;
	
	private String desc;
	
	private EnumMessagePublishStatus(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static String getDesc(Integer code){
		for(EnumMessagePublishStatus messagePublishStatus : EnumMessagePublishStatus.values()){
			if(messagePublishStatus.getCode().equals(code)){
				return messagePublishStatus.getDesc();
			}
		}
		return null;
	}
}
