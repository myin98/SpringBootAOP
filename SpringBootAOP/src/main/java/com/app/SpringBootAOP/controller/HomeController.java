package com.app.SpringBootAOP.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.app.SpringBootAOP.dto.HomeDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestController
//@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(HttpServletRequest req) {

		return req.getParameter("msg");
		//return new ModelAndView("home");
	}
	
	@GetMapping("/model")
	public Object model(Model model) {
		return model.getAttribute("msg");
	}
	
	@GetMapping("/dto")
	public HomeDTO dto(HomeDTO homeDTO) {
		return homeDTO;
	}
}
