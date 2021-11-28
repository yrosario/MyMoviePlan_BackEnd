package com.mymovieplan.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymovieplan.api.model.PurchaseItem;
import com.mymovieplan.api.repository.PurchaseItemRepository;

@Service
public class PurchaseItemServiceImpl implements PurchaseItemService {
	
	@Autowired
	private PurchaseItemRepository purchaseItemRepository;

	@Override
	public PurchaseItem save(PurchaseItem purchaseItem) {
		return purchaseItemRepository.save(purchaseItem);
	}

	@Override
	public void removeCartItemById(PurchaseItem purchaseItem) {
		// TODO Auto-generated method stub

	}

	@Override
	public PurchaseItem findById(Long purchaseItemId) {
		return purchaseItemRepository.getById(purchaseItemId);
	}

}
