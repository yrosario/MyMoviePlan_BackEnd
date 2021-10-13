package com.mymovieplan.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private final Long id;
		
		@ManyToOne
		@Column(name="movie_id")
		private Movie movie;
		
		@Column(name="category_name")
		private String categoryName;

		public Category() {
			this.id = null;
		}

		public Category(Movie movie, String categoryName) {
			this();
			this.movie = movie;
			this.categoryName = categoryName;
		}

		public Movie getMovie() {
			return movie;
		}

		public void setMovie(Movie movie) {
			this.movie = movie;
		}

		public String getCategoryName() {
			return categoryName;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}

		public Long getId() {
			return id;
		}

		@Override
		public String toString() {
			return "Category [id=" + id + ", movie=" + movie + ", categoryName=" + categoryName + "]";
		}
				
		

}
