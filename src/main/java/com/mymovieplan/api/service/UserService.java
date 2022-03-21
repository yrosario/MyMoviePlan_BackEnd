package com.mymovieplan.api.service;

import java.util.List;

import com.mymovieplan.api.model.CartItem;
import com.mymovieplan.api.model.Payment;
import com.mymovieplan.api.model.Purchase;
import com.mymovieplan.api.model.User;

public interface UserService {
	
	public User findUserByUserName(String username);
	public User findUserById(Long id);
	public Long findUserByUsername(String username);
	public User save(User user);
	public List<User> findAll();
	public List<Purchase> findAllPurchases(Long id);
	public List<CartItem> findAllCartItems(Long id);
	public void updateUserPassword(User user, String newPassword);
	public void deleteUser(String username);
	public void deleteUserById(Long id);
	public Payment findPaymentById(Long paymentId, List<Payment> payments);

}
