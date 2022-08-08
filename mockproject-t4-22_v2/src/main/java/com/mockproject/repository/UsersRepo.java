package com.mockproject.repository;

import java.util.List;

import com.mockproject.entity.modeljson.UserDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mockproject.entity.model.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
	
	Users findByUsername(String username);
	List<Users> findByIsDeleted(Boolean isDeleted);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE users SET is_deleted = 1 WHERE username = ?", nativeQuery = true)
	void deleteLogical(String username);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE users SET email = ?1, password = ?2, fullname = ?3 WHERE username = ?4", nativeQuery = true)
	void update(String email, String password, String fullname, String username);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE users SET email = ?1, fullname = ?2 WHERE username = ?3", nativeQuery = true)
	void updateNonPass(String email, String fullname, String username);
}
