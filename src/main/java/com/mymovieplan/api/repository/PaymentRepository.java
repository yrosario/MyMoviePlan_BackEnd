package com.mymovieplan.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymovieplan.api.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
