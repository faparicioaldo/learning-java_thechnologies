package com.springframework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springframework.service.UserService;

@Controller
@RequestMapping("/home")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/users")
	public String getUsers(Model model){				
		model.addAttribute("usersList",userService.getAllUsers());
		return "Home";
	}
}
