package com.mymovieplan.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymovieplan.api.model.Purchase;
import com.mymovieplan.api.repository.PurchaseRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService{
	
	@Autowired
	private PurchaseRepository purchaseRepository;

	@Override
	public List<Purchase> findAll() {
		return purchaseRepository.findAll();
	}

	@Override
	public Purchase save(Purchase purchase) {
		return purchaseRepository.save(purchase);
	}

	@Override
	public void deletePurchaseByPurchaseId(Long purchaseId) {
		Optional<Purchase> purchase = purchaseRepository.findById(purchaseId);
		if(purchase.isEmpty())
			return;
		
		purchaseRepository.delete(purchase.get());;
		
	}

	
}
