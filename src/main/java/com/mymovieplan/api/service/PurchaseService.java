package com.mymovieplan.api.service;

import java.util.List;

import com.mymovieplan.api.model.Purchase;

public interface PurchaseService {
	
	public List<Purchase> findAll();
	public Purchase save(Purchase purcharse);
	public void deletePurchaseByPurchaseId(Long purchaseId);
}
