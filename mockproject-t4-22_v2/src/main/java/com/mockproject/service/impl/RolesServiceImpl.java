package com.mockproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.entity.model.Roles;
import com.mockproject.repository.RolesRepo;
import com.mockproject.service.RolesService;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {
	
	@Autowired
	private RolesRepo repo;

	@Override
	public Roles findByName(String name) {
		return repo.findByName(name);
	}

	@Override
	public List<Roles> findByNameRoles(String name) {
		return repo.findAllByName(name);
	}
}
