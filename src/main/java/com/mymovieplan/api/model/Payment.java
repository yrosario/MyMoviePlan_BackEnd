package com.mymovieplan.api.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	
	@Column(name="user_id")
	@ManyToOne 
	private User user;
	
	@Column(name="card_no")
	private long cardNo;
	
	@Column(name="expiration_date")
	private Date exp_date;
	
	@Column(name="card_vendor")
	private String cardVendor;

	public Payment() {
		this.id = null;
	}

	public Payment(long cardNo, Date exp_date, String cardVendor) {
		this();
		this.cardNo = cardNo;
		this.exp_date = exp_date;
		this.cardVendor = cardVendor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getCardNo() {
		return cardNo;
	}

	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}

	public Date getExp_date() {
		return exp_date;
	}

	public void setExp_date(Date exp_date) {
		this.exp_date = exp_date;
	}

	public String getCardVendor() {
		return cardVendor;
	}

	public void setCardVendor(String cardVendor) {
		this.cardVendor = cardVendor;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", user=" + user + ", cardNo=" + cardNo + ", exp_date=" + exp_date
				+ ", cardVendor=" + cardVendor + "]";
	}
	
	
	
	
	
	
	
	
	
}
