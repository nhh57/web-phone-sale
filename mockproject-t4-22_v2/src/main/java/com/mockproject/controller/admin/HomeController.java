package com.mockproject.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mockproject.service.StatsService;

@Controller(value = "HomeControllerOfAdmin")
@RequestMapping("/admin")
public class HomeController {
	
	@Autowired
	private StatsService statsService;
	
	@GetMapping("")
	public String doGetIndex(Model model) throws Exception{
		String[][] chartData = statsService.getTotalPriceLast6Months();
		int i=0;
		while (i<= 5){
			System.out.println("trước: "+chartData[0][i]);
			System.out.println("sau: "+chartData[1][i]);
			i++;
		}
		model.addAttribute("chartData", chartData);
		return "admin/index";
	}
}
