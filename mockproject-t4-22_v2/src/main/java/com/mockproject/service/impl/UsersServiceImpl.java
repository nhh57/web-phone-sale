package com.mockproject.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.mockproject.entity.modeljson.RoleDataModel;
import com.mockproject.entity.modeljson.UserDataModel;
import com.mockproject.repository.UserDataModelRepo;
import com.mockproject.util.Utils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.config.EncoderConfig;
import com.mockproject.entity.model.Roles;
import com.mockproject.entity.model.Users;
import com.mockproject.repository.UsersRepo;
import com.mockproject.service.RolesService;
import com.mockproject.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private EncoderConfig encoderConfig;
	
	@Autowired
	private UsersRepo repo;
	
	@Autowired
	private RolesService roleService;

	@Autowired
	UserDataModelRepo userDataModelRepo;

	@Override
	public Users doLogin(String username, String password) {
		Users user = repo.findByUsername(username);
		
		if (user != null) {
			boolean checkPassword = encoderConfig.passwordEncoder().matches(password, user.getPassword());
			return checkPassword ? user : null;
		} else {
			return null;
		}
	}

	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	@Override
	public Users save(Users user) throws SQLException {
		Roles role = roleService.findByName("CUSTOMER");
		List<RoleDataModel> roleDataModels = new ArrayList<>();
		RoleDataModel dataModel = new RoleDataModel(role);
		roleDataModels.add(dataModel);
		user.setRoles(Utils.convertListObjectToJsonArray(roleDataModels));
		user.setDeleted(Boolean.FALSE);
		String password = encoderConfig.passwordEncoder().encode(user.getPassword());
		user.setPassword(password);
		return repo.saveAndFlush(user);
	}

	@Override
	public List<Users> findAll() {
		return repo.findByIsDeleted(Boolean.FALSE);
	}
	@Override
	public List<UserDataModel> findAll_2() {
		return userDataModelRepo.findByIsDeleted(Boolean.FALSE);
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	public void deleteLogical(String username) {
		repo.deleteLogical(username);
	}

	@Override
	public Users findByUsername(String username) {
		return repo.findByUsername(username);
	}

	@Override
	@Transactional(rollbackOn = {Exception.class, Throwable.class})
	public void update(Users user) {
		if (ObjectUtils.isNotEmpty(user) && StringUtils.isEmpty(user.getPassword())) {
			repo.updateNonPass(user.getEmail(), user.getFullname(), user.getUsername());
		} else {
			String password = encoderConfig.passwordEncoder().encode(user.getPassword());
			user.setPassword(password);
			repo.update(user.getEmail(), password, user.getFullname(), user.getUsername());
		}
	}
}
