package com.mymovieplan.api.model;

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
@Table(name="description")
public class Description {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	private Movie movie;
	
	@Column(name="description")
	private String description;

	public Description() {
		this.id = null;
		this.description = "Default text";
	}

	public Description(Movie movie, String description) {
		this();
		this.movie = movie;
		this.description = description;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Description [id=" + id + ", movie=" + movie + ", description=" + description + "]";
	}
	
	
	
}
