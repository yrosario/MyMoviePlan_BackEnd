package com.mymovieplan.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymovieplan.api.model.Payment;
import com.mymovieplan.api.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public Payment save(Payment payment) {
		return paymentRepository.save(payment);
	}

	@Override
	public Payment findById(Long id) {
		return paymentRepository.findById(id).get();
	}

	@Override
	public void delete(Payment payment) {
		paymentRepository.delete(payment);
		
	}



}
