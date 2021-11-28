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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Long id;
	
	@OneToMany(mappedBy="movie")
	@JsonIgnore
	private List<PurchaseItem> purchaseItems = new ArrayList<PurchaseItem>();
	
	@OneToMany(mappedBy="movie")
	@JsonIgnore
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

	@Override
	public String toString() {
		return "Movie [id=" + id + ", purchaseItems=" + purchaseItems + ", cartItems=" + cartItems + ", movieName="
				+ movieName + ", duration=" + duration + ", price=" + price + "]";
	}
	
	
	
}
