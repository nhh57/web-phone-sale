package com.mockproject.repository;

import java.math.BigDecimal;
import java.util.List;

import com.mockproject.entity.modeljson.ProductDataModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mockproject.entity.model.Products;

import javax.persistence.StoredProcedureQuery;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Long> {
	
	List<Products> findByIsDeletedAndQuantityGreaterThan(Boolean isDeleted, Integer quantity);
	Page<Products> findByIsDeletedAndQuantityGreaterThan(Boolean isDeleted, Integer quantity, Pageable pageable);
	Products findBySlug(String slug); // -> SELECT * FROM products WHERE slug = ?

	@Query(value = "{CALL sp_getAllProduct(:isDelete, :keySearch)}", nativeQuery = true)
	List<ProductDataModel> getAllProduct(@Param("isDelete") Integer isDelete, @Param("keySearch") String keySearch) throws  Exception;

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE products SET name = ?1, type_id = ?2, quantity = ?3 , price = ?4, unit_id = ?5 , img_url = ?6, description = ?7 , slug = ?8, is_deleted = ?9 WHERE id = ?10", nativeQuery = true)
	void update(String name, long typeId, int quantity, BigDecimal price, long unitId, String imgUrl, String description, String slug, Boolean isDeleted, Long id);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE products SET quantity = ? WHERE id = ?", nativeQuery = true)
	void updateQuantity(Integer newQuantity, Long productId);
}
