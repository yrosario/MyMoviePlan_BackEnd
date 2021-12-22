package com.mymovieplan.api.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="image")
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	
	@ManyToOne
	@JsonIgnore
	private Movie movie;
	
	@Column(name="image")
	@Lob
	private byte[] image;

	
	public Image() {
		this.id = null;
	}

	public Image(Movie movie, byte[] image) {
		this();
		this.movie = movie;
		this.image = image;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", movie=" + movie + ", image=" + Arrays.toString(image) + "]";
	}
	
	
	
	
	
	
}
