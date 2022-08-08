package com.mockproject.api;

import java.util.List;

import com.mockproject.entity.model.ProductTypes;
import com.mockproject.entity.model.UnitTypes;
import com.mockproject.service.ProductTypesService;
import com.mockproject.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.entity.model.Products;
import com.mockproject.service.ProductsService;

@RestController
@RequestMapping("/api/products")
public class ProductsApi {
	
	@Autowired
	private ProductsService productService;

	@Autowired
	private ProductTypesService productTypesService;

	@Autowired
	private UnitService unitService;
	
	// ENDPOINT: localhost:8080/api/products
	@GetMapping("")
	public ResponseEntity<?> doGetAll() {
		List<Products> products = productService.findAll();
		return ResponseEntity.ok(products);
	}

	@GetMapping("/product-type")
	public ResponseEntity<?> doGetAllProductTypes() {
		List<ProductTypes> productTypes = productTypesService.getALl();
		return ResponseEntity.ok(productTypes);
	}

	@GetMapping("/product-unit")
	public ResponseEntity<?> doGetAllUnitTypes() {
		List<UnitTypes> unitTypes = unitService.getALl();
		return ResponseEntity.ok(unitTypes);
	}



}
