package com.haige.luban.enums;

/**
 * 通知状态枚举
 * @author linwei
 *
 */
public enum EnumMessageStatus {
	
	UNREAD(0,"未读"),READ(1,"已读");
	
	private Integer code;
	
	private String desc;
	
	private EnumMessageStatus(Integer code, String desc) {
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
		for(EnumMessageStatus messageStatus : EnumMessageStatus.values()){
			if(messageStatus.getCode().equals(code)){
				return messageStatus.getDesc();
			}
		}
		return null;
	}
}
