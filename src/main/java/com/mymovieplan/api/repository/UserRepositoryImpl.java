package com.mymovieplan.api.repository;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mymovieplan.api.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
	private EntityManager em;

	@Override
	public User getUser(Long id) {
		
		Session currentSession = em.unwrap(Session.class);
		
		User user = currentSession.get(User.class, id);

		return user;
	}

}
