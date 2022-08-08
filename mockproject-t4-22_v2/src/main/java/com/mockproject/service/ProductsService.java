package com.mockproject.service;

import java.sql.SQLException;
import java.util.List;

import com.mockproject.entity.model.Users;
import com.mockproject.entity.modeljson.ProductDataModel;
import org.springframework.data.domain.Page;

import com.mockproject.entity.model.Products;

public interface ProductsService {

	List<Products> findAll();
	Page<Products> findAll(int pageSize, int pageNumber) throws Exception;
	Products findBySlug(String slug);
	Products findById(Long id);
	void updateQuantity(Integer newQuantity, Long productId);

	List<ProductDataModel> SpGetListProduct(int isDelete, String keySearch) throws Exception;

	Products save(Products product) throws SQLException;

	void update(Products product);
}
