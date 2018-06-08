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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.haige.luban.enums.EnumGoodsStatus;

import lombok.Data;

/**
 * 商品
 * @author linwei
 *
 */
@Entity
@DynamicInsert(true)  
@DynamicUpdate(true)
@Data
public class Goods implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//商品名称
	@JsonProperty("name")
	@NotBlank
	@Column(nullable=false)
    private String name;
    
	//描述  desc是mysql的内部关键字，之前在这上面吃了亏，怎么建表都不成功
	@NotBlank
	@Column(nullable=false)
    private String description;
    
    //商品价格
    @NotNull //@NotBlank无法应用于BigDecimal
    @Column(nullable=false)
    private BigDecimal price;
    
    //品类
    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private GoodsCategory category;
    
    //用户类型:正常、缺货、已下架
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private EnumGoodsStatus status;
    
    //是否在Banner上展示
    private Boolean isOnBanner=false;

    //图片保存地址
    private String[] imagesPath;
    
    //供应商
    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @NotNull //@NotBlank无法应用于Supplier
    private Supplier supplier;
    
    //上架时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    
    //修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    
    //商品-购物车映射
    @JsonIgnore
    @OneToMany(mappedBy="goods",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<GoodsCartRelation> goodsCartRelation=new HashSet<GoodsCartRelation>();
    
    //商品-订单映射
    @JsonIgnore
    @OneToMany(mappedBy="goods",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<GoodsOrderRelation> goodsOrderRelation=new HashSet<GoodsOrderRelation>();
}
