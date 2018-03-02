package com.haige.luban.pojo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * 工人的工资
 * @author linwei
 *
 */
@Entity
@Data
public class Salary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    //计划执行时间
    private Date planTime;
    
    //实际执行时间
    private Date buildingTime;
    
    //金额（分）
    private BigDecimal amount;
    
    //接收者（工人）
    @ManyToOne
    private User receiver;
    
    //放款状态：1.未发放，2.放款中，3.已完成
    private Integer status;
    
    //对应的项目
    @ManyToOne
    private Task task;
    
}
