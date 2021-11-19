package com.mymovieplan.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymovieplan.api.model.CartItem;
import com.mymovieplan.api.repository.CartItemRepository;

@Service
public class CartItemServiceImpl implements CartItemService {
	
	@Autowired
	private CartItemRepository cartItemRepository;

	@Override
	public CartItem save(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}

	@Override
	public void removeCartItemById(CartItem cartItem) {
		cartItemRepository.delete(cartItem);
		
	}

}
