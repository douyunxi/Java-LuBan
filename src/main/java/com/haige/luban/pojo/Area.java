package com.haige.luban.pojo;

import lombok.Data;

@Data
public class Area {
	private Long id;

    private String name;

    private Long parentId;

    //地级市的等级
    private Integer level;
}
