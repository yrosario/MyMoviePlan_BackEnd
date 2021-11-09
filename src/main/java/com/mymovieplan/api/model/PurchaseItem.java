package com.mymovieplan.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="purchase_item")
public class PurchaseItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Purchase purchase;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Movie movie;

	public PurchaseItem() {
		this.id = null;
	}

	public PurchaseItem(Purchase purchase, Movie movie) {
		this();
		this.purchase = purchase;
		this.movie = movie;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
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
		return "PurchaseItem [id=" + id + ", purchase=" + purchase + ", movie=" + movie + "]";
	}
	
	
	
	

}
