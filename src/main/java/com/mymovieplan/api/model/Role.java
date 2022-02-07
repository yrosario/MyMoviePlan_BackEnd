package com.mymovieplan.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	
	@Column(name="role_name")
	private String roleName;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonIgnore
	private User userRole;
	
	public Role() {
		this.id = null;
	}

	public Role(String name) {
		this();
		this.roleName = name;
	}

	public String getName() {
		return roleName;
	}

	public void setName(String name) {
		this.roleName = name;
	}

	public Long getId() {
		return id;
	}

	public User getUserRole() {
		return userRole;
	}

	public void setUserRole(User user) {
		this.userRole = user;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + roleName + ", userRole=" + userRole + "]";
	}
	
	
	
	
	
	
	
	
}


