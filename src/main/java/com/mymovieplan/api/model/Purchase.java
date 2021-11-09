package com.mymovieplan.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="purchase")
public class Purchase {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private final Long id;
		
		@OneToMany(mappedBy="purchase", fetch=FetchType.LAZY)
		@JsonIgnore
		private List<PurchaseItem> purchaseItems = new ArrayList<PurchaseItem>();
		
		@ManyToOne(fetch=FetchType.LAZY)
		private User purchaseUser;
		
		@Column(name="order_date")
		private Date orderDate;

		public Purchase() {
			this.id = null;
		}

		public Purchase(User user, Date orderDate) {
			this();
			this.purchaseUser = user;
			this.orderDate = orderDate;
		}

		public User getUser() {
			return purchaseUser;
		}

		public void setUser(User user) {
			this.purchaseUser = user;
		}

		public Date getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(Date orderDate) {
			this.orderDate = orderDate;
		}

		public Long getId() {
			return id;
		}

		public List<PurchaseItem> getPurchaseItems() {
			return purchaseItems;
		}

		public void setPurchaseItems(PurchaseItem item) {
			this.purchaseItems.add(item);
		}

		public User getPurchaseUser() {
			return purchaseUser;
		}

		public void setPurchaseUser(User purchaseUser) {
			this.purchaseUser = purchaseUser;
		}

		@Override
		public String toString() {
			return "Purchase [id=" + id + ", purchaseItems=" + purchaseItems + ", purchaseUser=" + purchaseUser
					+ ", orderDate=" + orderDate + "]";
		}
		
		
		
		
		
		
}
