package com.mymovieplan.api.service;

import java.util.List;

import com.mymovieplan.api.model.Movie;

public interface MovieService {
	
	public List<Movie> findAll();

	public Movie findMovieById(Long id);

	public Movie save(Movie movie);

	public void delete(Movie movie);
}
