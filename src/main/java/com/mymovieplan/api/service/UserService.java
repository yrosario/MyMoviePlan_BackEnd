package com.mymovieplan.api.service;

import java.util.List;
import java.util.Optional;

import com.mymovieplan.api.model.CartItem;
import com.mymovieplan.api.model.Purchase;
import com.mymovieplan.api.model.User;

public interface UserService {
	
	public Optional<User> findById(Long id);
	public User save(User user);
	public List<User> findAll();
	public List<Purchase> findAllPurchases(Long id);
	public List<CartItem> findAllCartItems(Long id);

}
