package com.haige.luban.pojo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.haige.luban.enums.EnumTaskStatus;

import lombok.Data;

/**
 * 工人的任务
 * @author linwei
 *
 */
@Entity
@Data
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	//标题
    private String title;

    //内容
    private String content;

    //任务创建时间
    private Date createTime=new Date();
    
    //计划执行时间
    private Date planTime;
    
    //实际执行时间
    private Date buildingTime;
    
    //实际完成时间
    private Date finishTime;
    
    //预计金额（分）
    private BigDecimal estimatedAmount;
    
    //实际发生金额（分）
    private BigDecimal actualAmount;
    
    //项目地区
    @ManyToOne
    private Area district;
    
    //具体地址
    private String address;
    
    //创建者（雇主、客户）
    @ManyToOne
    private User employer;
    
    //接收者（工人）
    @ManyToOne
    private User worker;
    
    //施工状态：1.未开始，2.施工中，3.已完成
    @Enumerated(EnumType.ORDINAL)
    private EnumTaskStatus status;
    
}
