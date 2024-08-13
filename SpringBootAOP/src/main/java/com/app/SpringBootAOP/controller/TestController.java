package com.app.SpringBootAOP.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/test")
	public Object test(Model model) {
		
		return model.getAttribute("msg");

	}
}
