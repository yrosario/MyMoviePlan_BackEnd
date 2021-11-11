package com.mymovieplan.api.service;

import java.util.List;

import com.mymovieplan.api.model.Cart;
import com.mymovieplan.api.model.CartItem;

public interface CartService {
	
	public List<CartItem> getAllItems();
	public void saveCartItem(CartItem cartItem);
	public Cart save(Cart save);
	

}
