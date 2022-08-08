package com.mockproject.entity.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "order_details")

public class OrderDetails extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Orders order;
	
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Products product;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean deleted) {
		isDeleted = deleted;
	}
}
