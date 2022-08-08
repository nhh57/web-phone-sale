package com.mockproject.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.mockproject.entity.modeljson.ProductDataModel;
import com.mockproject.repository.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mockproject.entity.model.Products;
import com.mockproject.repository.ProductsRepo;
import com.mockproject.service.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService {
	
	@Autowired
	private ProductsRepo repo;

	@Autowired
	private Test repos;

	@Override
	public List<Products> findAll() {
		return repo.findByIsDeletedAndQuantityGreaterThan(Boolean.FALSE, 0);
	}

	@Override
	public Products findBySlug(String slug) {
		return repo.findBySlug(slug);
	}

	@Override
	public Products findById(Long id) {
		Optional<Products> optional = repo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void updateQuantity(Integer newQuantity, Long productId) {
		repo.updateQuantity(newQuantity, productId);
	}

	@Override
	public List<ProductDataModel> SpGetListProduct(int isDelete, String keySearch) throws Exception {
		return repos.getAllProduct(isDelete,keySearch);
	}

	@Override
	public Products save(Products product) throws SQLException {
		return repo.save(product);
	}

	@Override
	public void update(Products product) {
		repo.update(product.getName(),product.getProductType().getId(), product.getQuantity(), product.getPrice(), product.getUnitType().getId(), product.getImgUrl(), product.getDescription(), product.getSlug(), product.getDeleted(), product.getId());
	}

	@Override
	public Page<Products> findAll(int pageSize, int pageNumber) throws Exception {
		if (pageNumber >= 1) {
			return repo.findByIsDeletedAndQuantityGreaterThan(Boolean.FALSE, 0, PageRequest.of(pageNumber - 1, pageSize));
		} else {
			throw new Exception("Page number must be greater than 0");
		}
	}
}
