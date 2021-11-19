package com.mymovieplan.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymovieplan.api.model.Cart;
import com.mymovieplan.api.model.CartItem;
import com.mymovieplan.api.repository.CartItemRepository;
import com.mymovieplan.api.repository.CartRepository;

@Service
public class CartImpl implements CartService{
	
	@Autowired 
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;

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

	@Override
	public Cart findAllCartItemsByUserId(Long id) {
		
		List<Cart> cartItems = cartRepository.findAll();
		
		for(Cart cart:cartItems) {
			if(cart.getCartUser().getId() == id)
				return cart;
		}
		
		return null;
	}

	@Override
	public void removeCartItemById(Cart cart) {
		List<CartItem> cartItems = cart.getCartItems();
		
		cartItemRepository.delete(null);
		
	}



}
