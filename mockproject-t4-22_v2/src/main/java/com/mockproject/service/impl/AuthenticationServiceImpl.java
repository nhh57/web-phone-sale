package com.mockproject.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mockproject.constant.SessionConstant;
import com.mockproject.entity.model.Users;
import com.mockproject.jwt.CustomUser;
import com.mockproject.jwt.JwtTokenProvider;
import com.mockproject.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AuthenticationManager authenManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

	@Override
	public Users doLogin(String username, String password, HttpSession session) throws Exception {
		UsernamePasswordAuthenticationToken authenInfo = new UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication = authenManager.authenticate(authenInfo);

		CustomUser customUser = (CustomUser) authentication.getPrincipal();
		Users userResponse = customUser.getUser();

		SecurityContextHolder.getContext().setAuthentication(authentication);

    	session.setAttribute(SessionConstant.JWT, tokenProvider.generateToken(customUser));
		return userResponse;
	}
}
