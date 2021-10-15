package com.mymovieplan.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	
	@OneToMany(mappedBy="movie")
	private List<PurchaseItem> purchaseItems = new ArrayList<PurchaseItem>();
	
	@OneToMany(mappedBy="movie")
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	
	@Column(name="movie_name")
	private String movieName;
	
	@Column(name="duration")
	private Integer duration;
	
	@Column(name="price")
	private Float price;

	public Movie() {
		this.id = null;
	}

	public Movie(String movieName, Integer duration, Float price) {
		this();
		this.movieName = movieName;
		this.duration = duration;
		this.price = price;
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
	
	public List<PurchaseItem> getPurchaseItems() {
		return purchaseItems;
	}

	public void setProductItems( PurchaseItem item) {
		this.purchaseItems.add(item);
	}
	

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(CartItem item) {
		this.cartItems.add(item);
		
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", movieName=" + movieName + ", duration=" + duration + ", price=" + price + "]";
	}
	
	
	
}
