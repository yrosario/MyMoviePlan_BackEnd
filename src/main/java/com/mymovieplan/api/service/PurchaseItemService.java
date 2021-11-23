package com.mymovieplan.api.service;


import com.mymovieplan.api.model.PurchaseItem;

public interface PurchaseItemService {
	
	public PurchaseItem save(PurchaseItem purchaseItem);

	public void removeCartItemById(PurchaseItem purchaseItem);

	public PurchaseItem findById(Long purchaseItemId);

}
