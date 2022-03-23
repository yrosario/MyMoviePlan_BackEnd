package com.mymovieplan.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="role_item")
public class RoleItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Role role;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	public RoleItem() {
		this.id = null;
	}

	public RoleItem(Role role, User user) {
		this();
		this.role = role;
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "RoleItem [id=" + id + ", role=" + role + ", user=" + user + "]";
	}
	
	
	
	
	

}
