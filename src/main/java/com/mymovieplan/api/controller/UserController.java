package com.mymovieplan.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mymovieplan.api.model.User;
import com.mymovieplan.api.service.*;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/get-user/{id}")
	public User getUser(@PathVariable("id") Long id){
		return userService.getUser(id);
	}
}
