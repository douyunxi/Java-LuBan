package com.haige.luban.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 商品购物车关系映射表
 * @author linwei
 *
 */
@Entity
@Table(name="goods_cart")
@Data
public class GoodsCartRelation implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//商品
	@JsonBackReference
	@ManyToOne
	private Goods goods;
	
	//购物车
	@JsonBackReference
	@ManyToOne
	private Cart cart;

	//数量
	@Column(nullable=false)
	private Long amount;
	
	//加入时商品价格，用于商品比价，提示用户现在商品比加入购物车时已经降价了多少
    @Column(nullable=false)
    @NotBlank
    private BigDecimal price;
	
	//加入时间
    @Column(nullable=false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date addTime;
}
