package com.mockproject.service;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mockproject.entity.model.ProductTypes;
import com.mockproject.entity.model.Products;
import com.mockproject.entity.model.UnitTypes;
import com.mockproject.repository.ProductsRepo;
import com.mockproject.service.impl.ProductsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductsServiceTest {
	
	@InjectMocks
	private ProductsServiceImpl productsService;
	
	@Mock
	private ProductsRepo repo;

	@Test
	public void test_findAll_Success() {
		List<Products> expected = new ArrayList<>();
		expected.add(new Products(1L, "product1", 1, 1D, "", "", "", Boolean.FALSE, new ProductTypes(), new UnitTypes() ));
		expected.add(new Products(2L, "product2", 2, 2D, "", "", "", Boolean.FALSE, new ProductTypes(), new UnitTypes() ));
		
		when(repo.findByIsDeletedAndQuantityGreaterThan(Boolean.FALSE, 0)).thenReturn(expected);
		
		List<Products> actual = new ArrayList<>();
		
		try {
			actual = productsService.findAll();
			assertEquals(expected.size(), actual.size());
		} catch (Exception ex) {
			fail("Should not throws exception");
		}
	}
}
