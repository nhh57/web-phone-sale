package com.mockproject.entity.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.mockproject.entity.modeljson.UserDataModel;
import com.mockproject.util.Utils;

@Entity
@Table(name = "users")
public class Users extends BaseEntity implements Serializable{

	private static final long serialVersionUID = -8005275174150207433L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username")
	@Size(max = 20, message = "Username must be less than 20 characters")
	private String username;
	
	@Column(name = "fullname")
	@Size(max = 50, message = "Fullname must be less than 50 characters")
	private String fullname;
	
	@Column(name = "password")
	private String password;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	@Email
	private String email;

	@Column(name = "img_url")
	private String imgUrl;

	@Column(name = "roles")
	private String roles;

	@Column(name = "permissions")
	private String permissions;
	
	@Column(name = "is_deleted")
	private Boolean isDeleted;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Boolean getDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean deleted) {
		isDeleted = deleted;
	}

	public List<Roles> getRoles() {
		try {
			return Utils.convertJsonStringToListObject(roles, Roles[].class);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Roles>();
		}
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}



	public List<Permission> getPermissions() {
		try {
			return Utils.convertJsonStringToListObject(roles, Permission[].class);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Permission>();
		}
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
}
