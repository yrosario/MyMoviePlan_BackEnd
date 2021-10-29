package com.mymovieplan.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mymovieplan.api.exception.UserNotFoundException;
import com.mymovieplan.api.model.User;
import com.mymovieplan.api.service.*;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/user/{id}")
	public User getUser(@PathVariable("id") Long id){
		
		User user = userService.getUser(id);
		if(user == null)
			throw new UserNotFoundException("id: -" +id);
		return userService.getUser(id);
	}
}
