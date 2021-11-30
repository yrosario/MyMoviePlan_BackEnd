package com.mymovieplan.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymovieplan.api.model.Category;
import com.mymovieplan.api.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findById(Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		return (category.isEmpty()) ? null : category.get();
	}

	@Override
	public void delete(Category category) {
		categoryRepository.delete(category);
		
	}

	

}
