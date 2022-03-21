package com.mymovieplan.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="category_item")
public class CategoryItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	
	public CategoryItem() {
		this.id = null;
	}
	
	public CategoryItem(Category category, Movie user) {
		this();
		this.category = category;
		this.movie = user;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Category category;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Movie movie;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie user) {
		this.movie = user;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "CategoryItem [id=" + id + ", category=" + category + ", movie=" + movie + "]";
	}
	
	


	
	

}
