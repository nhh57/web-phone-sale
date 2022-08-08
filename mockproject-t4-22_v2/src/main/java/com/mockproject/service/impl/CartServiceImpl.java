package com.mockproject.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.dto.CartDetailDto;
import com.mockproject.dto.CartDto;
import com.mockproject.entity.model.Orders;
import com.mockproject.entity.model.Products;
import com.mockproject.entity.model.Users;
import com.mockproject.service.CartService;
import com.mockproject.service.OrderDetailsService;
import com.mockproject.service.OrdersService;
import com.mockproject.service.ProductsService;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private ProductsService productService;
	
	@Autowired
	private OrdersService orderService;
	
	@Autowired
	private OrderDetailsService orderDetailsService;

	@Override
	public CartDto updateCart(CartDto cart, Long productId, Integer quantity, boolean isReplace) {
		Products product = productService.findById(productId);
			
		HashMap<Long, CartDetailDto> details = cart.getDetails();
		
		// 1 - them moi sp
		// 2 - update: 
		//            2-1. cong don
		//            2.2. replace
		// 3 - delete: update quantity ve 0

		if (!details.containsKey(productId)) {
			// them moi
			CartDetailDto newDetail = createNewCartDetail(product, quantity);
			details.put(productId, newDetail);
		} else if (quantity > 0) {
			if (isReplace) {
				details.get(productId).setQuantity(quantity);
			} else {
				Integer oldQuantity = details.get(productId).getQuantity();
				Integer newQuantity = oldQuantity + quantity;
				details.get(productId).setQuantity(newQuantity);
			}
		} else {
			details.remove(productId);
		}
		cart.setTotalQuantity(getTotalQuantity(cart));
		cart.setTotalPrice(getTotalPrice(cart));
		return cart;
	}
	
	@Override
	public Integer getTotalQuantity(CartDto cart) {
		Integer totalQuantity = 0;
		HashMap<Long, CartDetailDto> details = cart.getDetails();
		for (CartDetailDto cartDetail : details.values()) {
			totalQuantity += cartDetail.getQuantity();
		}
		return totalQuantity;
	}

	@Override
	public BigDecimal getTotalPrice(CartDto cart) {
		BigDecimal totalPrice = BigDecimal.valueOf(0);
		HashMap<Long, CartDetailDto> details = cart.getDetails();
		for (CartDetailDto cartDetail : details.values()) {
//			BigDecimal a = cartDetail.getPrice().multiply(BigDecimal.valueOf(cartDetail.getQuantity()));
			totalPrice = totalPrice.add(cartDetail.getPrice().multiply(BigDecimal.valueOf(cartDetail.getQuantity())));
		}
		return totalPrice;
	}

	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	@Override
	public void insert(CartDto cart, Users user, String address, String phone) throws Exception {
		
		if (StringUtils.isAnyBlank(address, phone)) {
			throw new Exception("Address or phone must be not null or empty or whitespace");
		}
		
		// insert vao ORDER
		Orders order = new Orders();
		order.setUser(user);
		order.setAddress(address);
		order.setPhone(phone);
		
		Orders orderResponse = orderService.insert(order);
		
		// duyet hashmap de insert lan luot vao ORDER_DETAILS
		// trong luc duyet hashmap qua tung SP -> di update quantity cho tung SP do
		for (CartDetailDto cartDetail : cart.getDetails().values()) {
			Products product = productService.findById(cartDetail.getProductId());
			if (checkQuantity(product, cartDetail)) {
				cartDetail.setOrderId(orderResponse.getId());
				orderDetailsService.insert(cartDetail);
				
				Integer newQuantity = product.getQuantity() - cartDetail.getQuantity();
				productService.updateQuantity(newQuantity, cartDetail.getProductId());
			} else {
				throw new Exception("Order quantity must be less than current product quantity");
			}
		}
	}
	
	private CartDetailDto createNewCartDetail(Products product, Integer quantity) {
		CartDetailDto cartDetail = new CartDetailDto();
		cartDetail.setProductId(product.getId());
		cartDetail.setPrice(product.getPrice());
		cartDetail.setQuantity(quantity);
		cartDetail.setSlug(product.getSlug());
		cartDetail.setName(product.getName());
		cartDetail.setImgUrl(product.getImgUrl());
		return cartDetail;
	}
	
	private boolean checkQuantity(Products product, CartDetailDto cartDetail) {
		return product.getQuantity() >= cartDetail.getQuantity(); 
	}
}
