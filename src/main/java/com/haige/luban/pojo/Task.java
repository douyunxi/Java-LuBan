package com.haige.luban.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.haige.luban.enums.EnumTaskStatus;

import lombok.Data;

/**
 * 工人的任务
 * @author linwei
 *
 */
@Entity
@DynamicInsert(true)  
@DynamicUpdate(true)
@Data
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Task implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	//标题
	@Column(nullable=false)
	@NotBlank
    private String title;

    //内容
    @Column(nullable=false)
    @NotBlank
    private String content;

    //任务创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    
    //计划执行时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Column(nullable=false)
    @Future
    @NotNull
    private Date planTime;
    
    //实际执行时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date buildingTime;
    
    //实际完成时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date finishTime;
    
    //预计金额（分）
    @Column(nullable=false)
    @Min(0)
    @NotNull
    private BigDecimal estimatedAmount;
    
    //实际发生金额（分）
    @Min(0)
    private BigDecimal actualAmount;
    
    //项目地区
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Area province;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Area city;
    
    @ManyToOne
    private Area district;
    
    //具体地址
    @Column(nullable=false)
    @NotBlank
    private String address;
    
    //创建者（雇主、客户）
    @ManyToOne
    @JoinColumn(nullable=false)
    @NotNull
    private User employer;
    
    //接收者（工人）
    //@ManyToOne
    //private User worker;
    
    //施工状态：0.未开始,1.已派单,2.已拒单,3.已接单,4.进行中,5.审核中,6.已完成
    @Enumerated(EnumType.ORDINAL)
    private EnumTaskStatus status;
    
    //任务接收者（工人）,为了保存是否被接单等状态，选择用中间表保存
    @JsonIgnore
    @OneToMany(mappedBy="task",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<UserTaskRelation> userTaskRelation=new HashSet<UserTaskRelation>();
    
}
