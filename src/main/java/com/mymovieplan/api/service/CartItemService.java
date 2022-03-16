package com.mymovieplan.api.service;

import com.mymovieplan.api.model.CartItem;

public interface CartItemService {
	
	public CartItem save(CartItem cartItem);

	public void removeCartItemById(CartItem cartItem);
	
	public CartItem findCartItemById(Long id);

}
