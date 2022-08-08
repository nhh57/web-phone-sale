package com.mockproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.mockproject.constant.SessionConstant;
import com.mockproject.entity.model.Products;
import com.mockproject.entity.model.Users;
import com.mockproject.service.AuthenticationService;
import com.mockproject.service.ProductsService;
import com.mockproject.service.UsersService;

@Controller
public class HomeController {
	
	@Autowired
	private ProductsService productService;
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	private static final int MAX_SIZE = 4;
	
	// localhost:8080/index?page={...} || localhost:8080 || localhost:8080/
	@GetMapping(value = {"/index", "", "/"})
	public String doGetIndex(@RequestParam(value = "page", required = false, defaultValue = "1") int page, 
			Model model) {
		List<Products> products = new ArrayList<>();
		try {
			Page<Products> pageProducts = productService.findAll(MAX_SIZE, page);
			products = pageProducts.getContent();
			model.addAttribute("totalPages", pageProducts.getTotalPages());
			model.addAttribute("currentPage", page);
		} catch (Exception ex) {
			products = productService.findAll();
		}
		model.addAttribute("products", products);
		return "user/index";
	}

	@GetMapping("/detail/{slug}")
	public String detailProduct(@PathVariable("slug") String slug,
								Model model) {
		Products products = productService.findBySlug(slug);
		model.addAttribute("productdetail",products);
		System.out.println(products.getName());
		return "user/details";}

	
	@GetMapping("/login")
	public String doGetLogin(Model model) {
		model.addAttribute("userRequest", new Users());
		return "user/login";
	}
	
	@GetMapping("/register")
	public String doGetRegister(Model model) {
		model.addAttribute("userRequest", new Users());
		return "user/register";
	}
	
	@PostMapping("/login")
	public String doPostLogin(@ModelAttribute("userRequest") Users userRequest,
			HttpSession session) {
		try {
			Users userResponse = authenticationService.doLogin(userRequest.getUsername(), userRequest.getPassword(), session);
			session.setAttribute(SessionConstant.CURRENT_USER, userResponse);
			return "redirect:/index";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "redirect:/login";
		}
	}
	
	@PostMapping("/register")
	public String doPostRegister(@ModelAttribute("userRequest") Users userRequest,
			HttpSession session) {
		try {
			Users userResponse = userService.save(userRequest);
			if (userResponse != null) {
				session.setAttribute(SessionConstant.CURRENT_USER, userResponse);
				return "redirect:/index";
			} else {
				return "redirect:/register";
			}
		} catch (Exception e) {
			return "redirect:/register";
		}
	}
}
