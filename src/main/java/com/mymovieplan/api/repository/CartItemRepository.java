package com.mymovieplan.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymovieplan.api.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
