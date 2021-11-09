package com.mymovieplan.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mymovieplan.api.exception.UserNotFoundException;
import com.mymovieplan.api.model.Cart;
import com.mymovieplan.api.model.CartItem;
import com.mymovieplan.api.model.Movie;
import com.mymovieplan.api.model.Payment;
import com.mymovieplan.api.model.Purchase;
import com.mymovieplan.api.model.User;
import com.mymovieplan.api.repository.MovieRepository;
import com.mymovieplan.api.repository.PaymentRepository;
import com.mymovieplan.api.service.CartItemService;
import com.mymovieplan.api.service.CartService;
import com.mymovieplan.api.service.PaymentService;
import com.mymovieplan.api.service.PurchaseService;
import com.mymovieplan.api.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	
	
	
	@GetMapping(path = "/user/{id}")
	public User getUser(@PathVariable("id") Long id){
		
		Optional<User> user = userService.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id: -" +id);
		return user.get();
	}
	
	@GetMapping(path = "/user")
	public List<User> getAllUsers(){
		return userService.findAll();
	}
	
	@PostMapping(path = "/user")
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		
		User saveUser = userService.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}").
				buildAndExpand(saveUser.getId())
				.toUri();
				   
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path = "/user/{id}/purchase")
	public List<Purchase> getAllPurchases(@PathVariable("id") Long id){
		
		return userService.findAllPurchases(id);
	}
	
	@PostMapping(path = "/user/{id}/purchase")
	public  void addPurchase(@PathVariable Long id, @RequestBody Purchase purchase){
		
		Optional<User> saveUser = userService.findById(id);
		
		if(saveUser.isEmpty())
			throw new UserNotFoundException("id: -" +id);
		
		saveUser.get().setPurchases(purchase);
		purchaseService.save(purchase);
		
	}
	
	@GetMapping(path = "/user/{id}/cart")
	public List<CartItem> getCartItems(@PathVariable("id") Long id){
	
		Optional<User> user = userService.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id: -" + id);
		
		return user.get().getCart().getCartItems();
	}
	
	@PostMapping(path = "/user/{id}/cart")
	public void addItemToCart(@PathVariable("id") Long id, @Validated @RequestBody CartItem cartItem) {
		
		//cartItem = cartItemService.save(cartItem);
		
		//retrieve user
		Optional<User> user = userService.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id: -" +id);
		
		
		//get the cart that belongs to the user
		Cart cart = user.get().getCart();
		//add item to cart
		cart.setCartItems(cartItem);
		//save cart
		cartService.save(cart);
		//set cart
		
		
		//cartItem.setMovie(movie);
		cartItem.setCart(cart);
		cartItemService.save(cartItem);
				
	}
	
	@GetMapping("/user/{id}/payment")
	public List<Payment> getUserPayment(@PathVariable("id") Long id) {
		
		List<Payment> payments = userService.findById(id).get().getPayments();
		
		return payments;
		
	}
	
	@PostMapping("/user/{id}/payment")
	public void addPayment(@PathVariable("id") Long id, @RequestBody Payment payment) {
		
		Optional<User> user = userService.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id: -" + id);
		
		user.get().setPayments(payment);
		userService.save(user.get());
		payment.setUser(user.get());
		paymentRepository.save(payment);
	}
}
