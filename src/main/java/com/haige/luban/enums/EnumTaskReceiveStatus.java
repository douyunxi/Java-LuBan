package com.haige.luban.enums;

/**
 * 任务接收状态枚举
 * @author linwei
 *
 */
public enum EnumTaskReceiveStatus {
	
	UNTREATED(0,"尚未处理"),RECEIPT(1,"已接单"),TIMEOUT(2,"超时关单"),REJECT(3,"已拒单"),PROCESSING(4,"进行中"),FINISHED(5,"已完成"),CHECKING(6,"验收中"),PAYOFF(7,"已放款");
	
	private Integer code;
	
	private String desc;
	
	private EnumTaskReceiveStatus(Integer code, String desc) {
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
		for(EnumTaskReceiveStatus status : EnumTaskReceiveStatus.values()){
			if(status.getCode().equals(code)){
				return status.getDesc();
			}
		}
		return null;
	}
}
