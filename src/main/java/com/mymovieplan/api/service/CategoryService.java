package com.mymovieplan.api.service;

import java.util.List;

import com.mymovieplan.api.model.Category;

public interface CategoryService {
	
	public Category save(Category category);
	public List<Category> findAll();
	public Category findById(Long id);
	public void delete(Category category);

}
