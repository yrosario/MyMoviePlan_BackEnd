package com.mymovieplan.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	
	@OneToOne
	private User cartUser;
	
	@Column(name="nums_of_items")
	private Integer numberOfItems;

	public Cart() {
		this.id = null;
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

	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + cartUser + ", numberOfItems=" + numberOfItems + "]";
	}
	
	
	
}
