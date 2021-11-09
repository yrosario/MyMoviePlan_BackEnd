package com.mymovieplan.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymovieplan.api.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
