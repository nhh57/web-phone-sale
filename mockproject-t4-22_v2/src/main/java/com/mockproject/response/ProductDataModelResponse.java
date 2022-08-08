package com.mockproject.response;

import com.mockproject.entity.modeljson.ProductDataModel;
import com.mockproject.util.Utils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDataModelResponse {

    private Long id;


    private String name;


    private Integer quantity;


    private BigDecimal price;


    private String imgUrl;


    private String description;


    private String slug;


    private Boolean isDeleted;


    private String category;


    private String unit;

    private String createdAt;

    private String updatedAt;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ProductDataModelResponse() {
    }

    public ProductDataModelResponse(ProductDataModel entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.quantity = entity.getQuantity();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
        this.description = entity.getDescription();
        this.slug = entity.getSlug();
        this.isDeleted = entity.getDeleted();
        this.category = entity.getCategory();
        this.unit = entity.getUnit();
        this.createdAt = Utils.getDatetimeString(entity.getCreatedAt());
        this.updatedAt = Utils.getDatetimeString(entity.getUpdatedAt());

    }

    public List<ProductDataModelResponse> maptolistDataModel(List<ProductDataModel> entity) {
        return entity.stream().map(x -> new ProductDataModelResponse(x)).collect(Collectors.toList());
    }


}
