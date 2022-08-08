package com.mockproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockproject.entity.model.Roles;

import java.util.List;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Long> {

	Roles findByName(String name);

	List<Roles> findAllByName(String name);
}
