package com.mymovieplan.api.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mymovieplan.api.model.Cart;
import com.mymovieplan.api.model.CartItem;
import com.mymovieplan.api.model.Payment;
import com.mymovieplan.api.model.Purchase;
import com.mymovieplan.api.model.User;
import com.mymovieplan.api.repository.UserRepository;

@Service
@Transactional
public class UserServiceImp implements UserService, UserDetailsService {

	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Override
	public User findUserByUserName(String username)
	 {
		List<User> users = userRepository.findAll();
		
		if(users.isEmpty())
			return null;
			
		
		for(User user : users)
			if(user.getUsername().equalsIgnoreCase(username))
				return user;
		
		return null;
		
		
	}

	@Override
	public User save(User user) {
		String password = user.getPassword();
		String encryptedPasword = bCryptPasswordEncoder.encode(password);
		
		user.setPassword(encryptedPasword);
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

	@Override
	public User findUserById(Long id) {
		Optional<User> user= userRepository.findById(id);
		if(user.isEmpty())
			return null;
		return user.get();
	}

	@Override
	public void updateUserPassword(User user, String newPassword) {
		String encryptedPassword = bCryptPasswordEncoder.encode(newPassword);
		
		user.setPassword(encryptedPassword);
		userRepository.save(user);
	}

	@Override
	public void deleteUser(String username) {
		User user = findUserByUserName(username);
		
		if(user != null)
			userRepository.delete(user);
		
	}

	@Override
	public Payment findPaymentById(Long paymentId, List<Payment> payments) {
		for(Payment payment:payments)
			if(payment.getId() == paymentId)
				return payment;
		
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    User user = userRepository.findByUsername(username);
	    
	    if(user == null)
	    	throw new UsernameNotFoundException("User not found");
	    
	    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
	    user.getRoles().stream().forEach(role -> { authorities.add(new SimpleGrantedAuthority(role.getName())); });
	    
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), authorities);
	}

	@Override
	public Long findUserByUsername(String username) {
		List<User> users = userRepository.findAll();
		if(users == null) {
			return -1L;
		}
		for(User user : users) {
			if(username.equals(user.getUsername())) {
				return user.getId();
			}
		}
		return -1L;
	}


	
	

}
