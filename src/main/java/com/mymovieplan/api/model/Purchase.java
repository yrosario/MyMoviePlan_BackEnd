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
@Table(name="purchase")
public class Purchase {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private final Long id;
		
		@ManyToOne
		private User user;
		
		@Column(name="order_date")
		private Date orderDate;

		public Purchase() {
			this.id = null;
		}

		public Purchase(User user, Date orderDate) {
			this();
			this.user = user;
			this.orderDate = orderDate;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
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

		@Override
		public String toString() {
			return "Purchase [id=" + id + ", user=" + user + ", orderDate=" + orderDate + "]";
		}
		
		
		
		
		
		
}
