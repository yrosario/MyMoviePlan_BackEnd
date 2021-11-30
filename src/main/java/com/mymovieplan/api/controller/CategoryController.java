package com.mymovieplan.api.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymovieplan.api.model.Category;
import com.mymovieplan.api.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("")
	public ResponseEntity<?> getListOfMovies(){
		
		List<Category> categories = categoryService.findAll();
		if(categories.isEmpty())
			return new ResponseEntity<>("Category List is Empty", HttpStatus.OK);
		
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id){
		Category category = categoryService.findById(id);
		if(category == null)
			return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(category, HttpStatus.OK);
		
	}
	
	@PostMapping("")
	public ResponseEntity<?> addCategory(@RequestBody Category category){
		
		Category saveCategory = categoryService.save(category);
		
		return (saveCategory == null) ? new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST) :
										new ResponseEntity<>("Successly added", HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<?> updateCategory(@RequestBody Category category){
		
		Category retrieveCategory = categoryService.findById(category.getId());
		if(retrieveCategory == null)
			return new ResponseEntity<>("Category not found", HttpStatus.BAD_REQUEST);
		
		categoryService.save(category);
		return new ResponseEntity<>("Category saved successfully", HttpStatus.OK);
			
	}
	
	@DeleteMapping("")
	public ResponseEntity<?> deleteCategory(@RequestBody HashMap<String, Long> request){
		Long id = request.get("id");
		if(id == null)
			return new ResponseEntity<>("Invalid parameter passed", HttpStatus.BAD_REQUEST);
		
		Category category = categoryService.findById(id);
		if(category == null)
			return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
		
		categoryService.delete(category);
		return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
	}
}
