package com.mymovieplan.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User cartUser;
	
	@OneToMany(mappedBy="cart", fetch=FetchType.LAZY)
    @JsonIgnore
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	
	@Column(name="nums_of_items")
	private Integer numberOfItems;

	public Cart() {
		this.id = null;
		this.numberOfItems=0;
	}

	public Cart(User user) {
		this();
		this.cartUser = user;
	}

	public User getUser() {
		return cartUser;
	}

	public void setUser(User user) {
		this.cartUser = user;
	}

	public Long getId() {
		return id;
	}
	

	public Integer getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(Integer numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
	

	public User getCartUser() {
		return cartUser;
	}

	public void setCartUser(User cartUser) {
		this.cartUser = cartUser;
	}

	@JsonIgnore
	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(CartItem item) {
		this.cartItems.add(item);
		
		if(this.numberOfItems ==null)
			this.numberOfItems = 0;
		
		this.numberOfItems++;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", cartUser=" + cartUser + ", cartItems=" + cartItems + ", numberOfItems="
				+ numberOfItems + "]";
	}
	
	
	
}
