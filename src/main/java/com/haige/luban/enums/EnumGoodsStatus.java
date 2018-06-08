package com.haige.luban.enums;

/**
 * 商品状态枚举
 * @author linwei
 *
 */
public enum EnumGoodsStatus {
	
	NORMAL(0,"正常"),OUT_OF_STOCK(1,"缺货"),REMOVED(2,"已下架");
	
	private Integer code;
	
	private String desc;
	
	private EnumGoodsStatus(Integer code, String desc) {
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
		for(EnumGoodsStatus status : EnumGoodsStatus.values()){
			if(status.getCode().equals(code)){
				return status.getDesc();
			}
		}
		return null;
	}
}
