package com.mymovieplan.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mymovieplan.api.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
