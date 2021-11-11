package com.mymovieplan.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymovieplan.api.model.Cart;
import com.mymovieplan.api.model.CartItem;
import com.mymovieplan.api.repository.CartRepository;

@Service
public class CartImpl implements CartService{
	
	@Autowired 
	private CartRepository cartRepository;

	@Override
	public List<CartItem> getAllItems() {
		return null;
	}

	@Override
	public void saveCartItem(CartItem cartItem) {
	
		
	}

	@Override
	public Cart save(Cart save) {
		return cartRepository.save(save);
	}

}
