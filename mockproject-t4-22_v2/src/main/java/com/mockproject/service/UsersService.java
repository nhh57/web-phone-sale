package com.mockproject.service;

import java.sql.SQLException;
import java.util.List;

import com.mockproject.entity.model.Users;
import com.mockproject.entity.modeljson.UserDataModel;

public interface UsersService {

	Users findByUsername(String username);
	Users doLogin(String username, String password);
	Users save(Users user) throws SQLException;

	void update(Users user);

	List<Users> findAll();

	List<UserDataModel> findAll_2();

	void deleteLogical(String username);

}
