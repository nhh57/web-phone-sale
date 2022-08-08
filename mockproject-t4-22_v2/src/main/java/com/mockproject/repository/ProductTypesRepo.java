package com.mockproject.repository;

import com.mockproject.entity.model.ProductTypes;
import com.mockproject.entity.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTypesRepo extends JpaRepository<ProductTypes, Long> {
    List<ProductTypes> findByIsDeleted(Boolean isDeleted);
}
