package com.mymovieplan.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymovieplan.api.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
