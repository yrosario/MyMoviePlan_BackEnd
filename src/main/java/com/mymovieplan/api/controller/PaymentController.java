package com.mymovieplan.api.controller;

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

import com.mymovieplan.api.model.Payment;
import com.mymovieplan.api.model.User;
import com.mymovieplan.api.service.PaymentService;
import com.mymovieplan.api.service.UserService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/user/{id}")
	public ResponseEntity<?>getPaymentMethods(@PathVariable("id") Long id){
		User user = userService.findUserById(id);
		if(user == null)
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		
		List<Payment> payments = user.getPayments();
		return new ResponseEntity<>(payments, HttpStatus.OK);
	}
	
	@GetMapping("/{paymentId}/user/{userId}")
	public ResponseEntity<?> getPaymentById(@PathVariable("paymentId") Long paymentId, @PathVariable("userId") Long userId){
		User user = userService.findUserById(userId);
		if(user == null)
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		
		Payment payment = userService.findPaymentById(paymentId, user.getPayments());
		if(payment == null)
			return new ResponseEntity<>("Payment method not found", HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(payment, HttpStatus.OK);
	
		}
	
	@PostMapping("/user/{id}")
	public ResponseEntity<?> addPayment(@PathVariable("id") Long id, @RequestBody Payment payment){
		User user = userService.findUserById(id);
		if(user == null)
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		
		user.setPayment(payment);
		userService.save(user);
		payment.setUser(user);
		paymentService.save(payment);
		return new ResponseEntity<>("Added payment successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deletePayment(@PathVariable("id") Long id, @RequestBody HashMap<String, Long> request){
		
		User user = userService.findUserById(id);
		if(user == null)
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		
		Long paymentId = request.get("id");
		if(paymentId == null)
			return new ResponseEntity<>("Wrong parameter passed", HttpStatus.BAD_REQUEST);	
		
		
		Payment retrievePayment = paymentService.findById(request.get("id"));
		if(retrievePayment == null)
			return new ResponseEntity<>("Payment not found", HttpStatus.NOT_FOUND);		
		
		paymentService.delete(retrievePayment);
		
		return new ResponseEntity<>("Delete payment successfully", HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/user/{userId}")
	public ResponseEntity<?> updatePayment(@PathVariable("userId") Long userId, @RequestBody Payment payment){
		
		User user = userService.findUserById(userId);
		if(user == null)
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		
		if(payment.getId() == null)
			return new ResponseEntity<>("Bad parameter passed", HttpStatus.NOT_FOUND);
		
		Payment retrievePayment = userService.findPaymentById(payment.getId(), user.getPayments());
		if(retrievePayment == null)
			return new ResponseEntity<>("Payment not found", HttpStatus.NOT_FOUND);
		
		payment.setUser(user);
		paymentService.save(payment);
		return new ResponseEntity<>("Update Successful", HttpStatus.OK);
					
		
	}

}
