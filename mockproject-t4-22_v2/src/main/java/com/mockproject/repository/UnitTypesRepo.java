package com.mockproject.repository;

import com.mockproject.entity.model.ProductTypes;
import com.mockproject.entity.model.UnitTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitTypesRepo extends JpaRepository<UnitTypes,Long> {

    List<UnitTypes> findByIsDeleted(Boolean isDeleted);
}
