package com.mymovieplan.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymovieplan.api.model.Cart;
import com.mymovieplan.api.model.CartItem;
import com.mymovieplan.api.model.Purchase;
import com.mymovieplan.api.model.User;
import com.mymovieplan.api.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired 
	private UserRepository userRepository;
	
	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User save(User user) {
		User saveUser = userRepository.save(user);
		return saveUser;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<Purchase> findAllPurchases(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty())
			return null;
		
		
		return user.get().getPurchases();
	}

	@Override
	public List<CartItem> findAllCartItems(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty())
			return null;
		
		Cart cart = user.get().getCart();
		
		return cart.getCartItems();
	}

}
