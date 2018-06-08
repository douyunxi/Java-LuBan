package com.haige.luban.pojo;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * 购物车
 * @author linwei
 *
 */
@Entity
@Table(name="cart")
@DynamicInsert(true)  
@DynamicUpdate(true)
@Data
public class Cart implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    //商品-购物车映射
    @JsonIgnore
    @OneToMany(mappedBy="cart",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<GoodsCartRelation> goodsCartRelation=new HashSet<GoodsCartRelation>();
}
