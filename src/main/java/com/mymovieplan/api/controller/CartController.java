package com.mymovieplan.api.controller;

import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymovieplan.api.model.Cart;
import com.mymovieplan.api.model.CartItem;
import com.mymovieplan.api.model.Movie;
import com.mymovieplan.api.model.User;
import com.mymovieplan.api.service.CartItemService;
import com.mymovieplan.api.service.CartService;
import com.mymovieplan.api.service.MovieService;
import com.mymovieplan.api.service.UserService;

@RestController()
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getCartItem(@PathVariable("id") Long id){
		
		User user = userService.findUserById(id);
		if(user == null)
			return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);

		
		Cart cart = user.getCart();
		
		if(cart == null)
			return new ResponseEntity<String>("Cart not found", HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(user.getCart().getCartItems(), HttpStatus.OK);
	}
	
	@PostMapping("/user/{id}")
	public ResponseEntity<?> addToCart(@PathVariable("id") Long id, @RequestBody HashMap<String, Long> request){
		User user = userService.findUserById(id);
		if(user == null)
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		
		Cart cart = user.getCart();
		if(cart == null)
			return new ResponseEntity<>("Cart not found", HttpStatus.NOT_FOUND);
		
		Long movieId = request.get("movieId");
		
		if(movieId == null)
			return new ResponseEntity<>("Invalid data passed", HttpStatus.BAD_REQUEST);
		
		Movie movie = movieService.findMovieById(movieId);
		
		CartItem cartItem = new CartItem(cart, movie);
		cartService.save(cart);
		cartItemService.save(cartItem);
		
		
		return new ResponseEntity<>(movie, HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{id}/item/{itemId}")
	public ResponseEntity<?> RemoveItemFromCart(@PathVariable("id") Long id, @PathVariable("itemId") Long itemId){
		User user = userService.findUserById(id);
		if(user == null)
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		
		Cart cart = user.getCart();
		if(cart == null)
			return new ResponseEntity<>("Cart not found", HttpStatus.NOT_FOUND);
		
		
		
		if(itemId == null)
			return new ResponseEntity<>("Invalid data passed", HttpStatus.BAD_REQUEST);
		
		cartService.removeCartItemById(itemId,cart);
		
		
		return new ResponseEntity<>("Sucecss", HttpStatus.OK);
	}
	
	
	@PutMapping("/user/{id}")
	public ResponseEntity<?>updateCartItem(@PathVariable("id") Long id, @RequestBody CartItem cartItem){
		
		if(cartService.findAllCartItemsByUserId(cartItem.getId()) == null)
			return new ResponseEntity<>("Cart item not found", HttpStatus.NOT_FOUND);
		
		cartItemService.save(cartItem);
		return new ResponseEntity<>("Cart item update", HttpStatus.OK);
			
	}
	
	

}
