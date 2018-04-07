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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.haige.luban.enums.EnumTaskReceiveStatus;

import lombok.Data;

@Entity
@Table(name="user_task")
@Data
public class UserTaskRelation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonBackReference
	@ManyToOne
	private User user;
	
	@JsonBackReference
	@ManyToOne
	private Task task;
	
	//消息状态：0.尚未处理，1.接单，2.超时关单，3.拒单，4.进行中,5.已完成,6.审核中，7.已放款
	@Enumerated(EnumType.ORDINAL)
	@NotNull
    private EnumTaskReceiveStatus status;
    
	//处理时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date processingTime;
	
	//预计金额（分）
	private BigDecimal estimatedAmount;
	
	//实际金额（分）
    private BigDecimal actualAmount;
    
    //用户打分
    private Integer score;
}
