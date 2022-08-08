package com.mockproject.service;

import com.mockproject.dto.CartDto;
import com.mockproject.entity.model.Users;

import java.math.BigDecimal;

public interface CartService {
	
	CartDto updateCart(CartDto cart, Long productId, Integer quantity, boolean isReplace);
	Integer getTotalQuantity(CartDto cart);
	BigDecimal getTotalPrice(CartDto cart);
	void insert(CartDto cart, Users user, String address, String phone) throws Exception;
}
