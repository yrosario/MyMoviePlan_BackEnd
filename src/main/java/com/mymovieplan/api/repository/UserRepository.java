package com.mymovieplan.api.repository;

import org.springframework.stereotype.Repository;

import com.mymovieplan.api.model.User;

public interface UserRepository {
	
	public User getUser(Long id);

}
