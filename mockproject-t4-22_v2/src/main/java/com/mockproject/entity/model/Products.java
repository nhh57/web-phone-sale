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
@Table(name = "products")
public class Products extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 8817710984654205495L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "img_url")
	private String imgUrl;

	@Column(name = "description")
	private String description;

	@Column(name = "slug")
	private String slug;

	@Column(name = "is_deleted")
	private Boolean isDeleted;
	
	@ManyToOne
	@JoinColumn(name = "type_id", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private ProductTypes productType;
	
	@ManyToOne
	@JoinColumn(name = "unit_id", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private UnitTypes unitType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Boolean getDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean deleted) {
		isDeleted = deleted;
	}

	public ProductTypes getProductType() {
		return productType;
	}

	public void setProductType(ProductTypes productType) {
		this.productType = productType;
	}

	public UnitTypes getUnitType() {
		return unitType;
	}

	public void setUnitType(UnitTypes unitType) {
		this.unitType = unitType;
	}
}
