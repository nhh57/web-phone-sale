package com.mockproject.repository;

import com.mockproject.entity.model.OrderDetails;
import com.mockproject.entity.model.Users;
import com.mockproject.entity.modeljson.UserDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDataModelRepo extends JpaRepository<UserDataModel, Long> {

    List<UserDataModel> findByIsDeleted(Boolean isDeleted);
}
