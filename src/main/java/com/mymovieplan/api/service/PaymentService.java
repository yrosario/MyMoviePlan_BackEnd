package com.mymovieplan.api.service;

import com.mymovieplan.api.model.Payment;

public interface PaymentService {
	
	public Payment save(Payment payment);
	public Payment findById(Long id);
	public void delete(Payment payment);

}
