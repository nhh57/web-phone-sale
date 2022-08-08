package com.mockproject.service;

import com.mockproject.entity.model.Roles;

import java.util.List;

public interface RolesService {

	Roles findByName(String name);

	List<Roles> findByNameRoles(String name);
}
