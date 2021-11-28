package com.mymovieplan.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

import com.mymovieplan.api.model.Movie;
import com.mymovieplan.api.model.Purchase;
import com.mymovieplan.api.model.PurchaseItem;
import com.mymovieplan.api.model.User;
import com.mymovieplan.api.service.MovieService;
import com.mymovieplan.api.service.PurchaseItemService;
import com.mymovieplan.api.service.PurchaseService;
import com.mymovieplan.api.service.UserService;

@RestController()
@RequestMapping("/purchase")
public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private MovieService movieService;

	
	@Autowired
	private PurchaseItemService purchaseItemService;
	
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getCartItem(@PathVariable("id") Long id){
		
		User user = userService.findUserById(id);
		if(user == null)
			return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);

		
		List<Purchase> purchases = user.getPurchases();
		
		if(purchases.isEmpty())
			return new ResponseEntity<String>("No Purchases found", HttpStatus.OK);
		
		return new ResponseEntity<>(user.getPurchases(), HttpStatus.OK);
	}
	
	@PostMapping("/user/{id}")
	public ResponseEntity<?> addToCart(@PathVariable("id") Long id, @RequestBody HashMap<String, Long> request){
		User user = userService.findUserById(id);
		if(user == null)
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		
		List<Purchase> purchases = user.getPurchases();
		if(purchases.isEmpty())
			return new ResponseEntity<>("Cart not found", HttpStatus.NOT_FOUND);
		
		Long movieId = request.get("movieId");
		
		if(movieId == null)
			return new ResponseEntity<>("Invalid data passed", HttpStatus.BAD_REQUEST);
		
		Movie movie = movieService.findMovieById(movieId);
		Purchase purchase = new Purchase(user, new Date());
		PurchaseItem purchaseItem = new PurchaseItem(purchase, movie);
		purchases.add(purchase);
		userService.save(user);
		purchaseService.save(purchase);
		purchaseItemService.save(purchaseItem);
		
		
		return new ResponseEntity<>(movie, HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> RemoveItemFromCart(@PathVariable("id") Long id, @RequestBody HashMap<String, Long> request){
		User user = userService.findUserById(id);
		if(user == null)
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		
		Long purchaseId = request.get("purchaseId");
		
		if(purchaseId == null)
			return new ResponseEntity<>("Invalid data passed", HttpStatus.BAD_REQUEST);
		
		purchaseService.deletePurchaseByPurchaseId(purchaseId);
		
		
		return new ResponseEntity<>("Sucecss", HttpStatus.OK);
	}
	
	
	@PutMapping("/user/{id}")
	public ResponseEntity<?>updateCartItem(@PathVariable("id") Long id, @RequestBody HashMap<String, Long> request){
		
		Long purchaseItemId = request.get("purchaseItemId");
		if(purchaseItemId == null)
			return new ResponseEntity<>("Bad parameter passed", HttpStatus.BAD_REQUEST);
		
		PurchaseItem purchaseItem = purchaseItemService.findById(purchaseItemId);
		if(purchaseItem == null)
			return new ResponseEntity<>("No purchase found", HttpStatus.OK);
		
		Long movieId = request.get("movieId");
		if(movieId == null)
			return new ResponseEntity<>("Bad parameter passed", HttpStatus.BAD_REQUEST);
		
		Movie movie = movieService.findMovieById(purchaseItemId);
		if(movie == null)
			return new ResponseEntity<>("No moive found", HttpStatus.OK);
		
		
		purchaseItem.setMovie(movie);
		purchaseItemService.save(purchaseItem);
		return new ResponseEntity<>("Purchase item update", HttpStatus.OK);
			
	}
	
	

}
