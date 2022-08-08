package com.mockproject.entity.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_types")
public class ProductTypes extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1545677034445217508L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "description")
	private String description;

	@Column(name = "slug")
	private String slug;

	@Column(name = "is_deleted")
	private Boolean isDeleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
