package com.mymovieplan.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart_item")
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	
	@ManyToOne
	private Cart cart;
	
	@ManyToOne
	private Movie movie;

	public CartItem() {
		this.id = null;
	}

	public CartItem(Cart cart, Movie movie) {
		this();
		this.cart = cart;
		this.movie = movie;
	}

	public Cart getPurchase() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Long getId() {
		return id;
	}
	
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", cart=" + cart + ", movie=" + movie + "]";
	}
	
	
	
	

}
