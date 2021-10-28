package com.mymovieplan.api.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mymovieplan.api.model.User;
import com.mymovieplan.api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private UserRepository userRepository;
	
	
	@Override
	@Transactional
	public User getUser(Long id) {
		return userRepository.getUser(id);
	}

}
