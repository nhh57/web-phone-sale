package com.mockproject.repository;

import com.mockproject.entity.model.Products;
import com.mockproject.entity.modeljson.ProductDataModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Test extends JpaRepository<ProductDataModel, Long> {


    @Query(value = "{CALL sp_getAllProduct(:isDelete, :keySearch)}", nativeQuery = true)
    List<ProductDataModel> getAllProduct(@Param("isDelete") Integer isDelete, @Param("keySearch") String keySearch) throws  Exception;



}
