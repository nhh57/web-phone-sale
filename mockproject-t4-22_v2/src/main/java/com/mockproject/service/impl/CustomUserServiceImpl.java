package com.mockproject.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mockproject.entity.model.Users;
import com.mockproject.jwt.CustomUser;
import com.mockproject.service.UsersService;

@Service
public class CustomUserServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsersService userService;

	@Override
    public UserDetails loadUserByUsername(String username) {
		Users user = userService.findByUsername(username);
    	if (user == null || user.getDeleted()) {
    		System.out.println("User not found: " + username);
            throw new UsernameNotFoundException(username);
        }
    	try {
    		List<GrantedAuthority> grantList = new ArrayList<>();
			System.out.println(user.getRoles().stream().map(x->x.getName()).collect(Collectors.toList()));
    		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRoles().stream().map(x->x.getName()).collect(Collectors.toList()).toString());
    		grantList.add(authority);
            return new CustomUser(user, grantList);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}
