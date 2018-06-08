package com.haige.luban.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * 订单
 * @author linwei
 *
 */
@Entity
@Table(name="t_order")//order为mysql关键字
@DynamicInsert(true)  
@DynamicUpdate(true)
@Data
public class Order implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//上架时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    
    //修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    
    //商品-订单映射
    @JsonIgnore
    @OneToMany(mappedBy="order",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<GoodsOrderRelation> goodsOrderRelation=new HashSet<GoodsOrderRelation>();
    
}
