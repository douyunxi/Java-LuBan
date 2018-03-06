package com.haige.luban.pojo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@Column(nullable=false)
    private String title;

    //内容
    @Column(nullable=false)
    private String content;

    //任务创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime=new Date();
    
    //计划执行时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable=false)
    private Date planTime;
    
    //实际执行时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date buildingTime;
    
    //实际完成时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;
    
    //预计金额（分）
    @Column(nullable=false)
    private BigDecimal estimatedAmount;
    
    //实际发生金额（分）
    private BigDecimal actualAmount;
    
    //项目地区
    @ManyToOne
    @JoinColumn(nullable = false)
    private Area province;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Area city;
    
    @ManyToOne
    private Area district;
    
    //具体地址
    @Column(nullable=false)
    private String address;
    
    //创建者（雇主、客户）
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User employer;
    
    //接收者（工人）
    @ManyToOne
    private User worker;
    
    //施工状态：1.未开始，2.施工中，3.已完成
    @Enumerated(EnumType.ORDINAL)
    private EnumTaskStatus status;
    
}
