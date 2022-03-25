package com.mymovieplan.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Long id;
	
	@OneToMany(mappedBy="movie", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PurchaseItem> purchaseItems = new ArrayList<PurchaseItem>();
	
	@OneToMany(mappedBy="movie", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	

	@ManyToMany(fetch=FetchType.EAGER)
	private List<Category> categories = new ArrayList<>();

	@OneToMany(mappedBy="movie", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Image> images = new ArrayList<Image>();
	
	@OneToOne(cascade = CascadeType.ALL)
	private Description description;

	
	@Column(name="movie_name")
	private String movieName;
	
	@Column(name="duration")
	private Integer duration;
	
	@Column(name="price")
	private Float price;

	public Movie() {
		this.id = null;
		this.description = new Description();
		//this.categories = new ArrayList<Category>();
	}

	public Movie(String movieName, Integer duration, Float price, Category category) {
		this();
		this.movieName = movieName;
		this.duration = duration;
		this.price = price;
		this.categories.add(category);
		
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}
	
	@JsonIgnore
	public List<PurchaseItem> getPurchaseItems() {
		return purchaseItems;
	}
    
	@JsonIgnore
	public void setProductItems( PurchaseItem item) {
		this.purchaseItems.add(item);
	}
	
	@JsonIgnore
	public List<CartItem> getCartItems() {
		return cartItems;
	}

	@JsonIgnore
	public void setCartItems(CartItem item) {
		this.cartItems.add(item);
		
	}
	

	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}
	

	public List<Image> getImages() {
		return images;
	}

	public void setImages(Image image) {
		this.images.add(image);
	}
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(Category category) {
		this.categories.add(category);
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", purchaseItems=" + purchaseItems + ", cartItems=" + cartItems + ", movieName="
				+ movieName + ", duration=" + duration + ", price=" + price + "]";
	}
	
	
	
}
