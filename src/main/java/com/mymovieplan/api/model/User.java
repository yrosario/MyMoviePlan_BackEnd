package com.mymovieplan.api.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Long id;
	
	@OneToMany(mappedBy="paymentUser", fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Payment> payments = new ArrayList<Payment>();

	@OneToMany(mappedBy="purchaseUser")
	private List<Purchase> purchases = new ArrayList<Purchase>();
	
	@OneToOne(cascade = {CascadeType.ALL}, fetch=FetchType.LAZY)
	@JsonIgnore
	private Cart cart;
	
	@Column(name="first_name")
	private String fName;
	
	@Column(name="last_name")
	private String lName;
	
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city;
	
	@Column(name="email")
	private String email;
	
	@Column(name="birthday")
	private Date birthday;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;
	
	@Column(name="active")
	private Boolean active;

	public User() {
		this.id = null;
		active = true;
		this.cart = new Cart();
		this.cart.setCartUser(this);
	}

	public User(String fName, String lName, String address, String city, String email, 
			    Date birthday, String password, String role, Boolean active) {
		this();
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.city = city;
		this.email = email;
		this.birthday = birthday;
		this.password = password;
		this.role = role;
		this.active = active;
		
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Payment payment) {
		this.payments.add(payment);
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Purchase purchase) {
		this.purchases.add(purchase);
	}

	public Cart getCart() {
		return cart;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", payments=" + payments + ", purchases=" + purchases + ", cart=" + cart + ", fName="
				+ fName + ", lName=" + lName + ", address=" + address + ", city=" + city + ", email=" + email
				+ ", birthday=" + birthday + ", password=" + password + ", role=" + role + ", active=" + active + "]";
	}
	
	
	
	
	
	
	
	
}
