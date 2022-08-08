package com.mockproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockproject.entity.model.Orders;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long>{

}
