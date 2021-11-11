package com.mymovieplan.api.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymovieplan.api.model.Movie;
import com.mymovieplan.api.repository.MovieRepository;


@Service
@Transactional
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieRepository movieRepository;

	@Override
	public List<Movie> findAll() {
		List<Movie> movies = movieRepository.findAll();
		
		return movies;
	}

	@Override
	public Movie findMovieById(Long id) {
		
		Optional<Movie> movie = movieRepository.findById(id);
		
		return (movie.isEmpty()) ? null : movie.get();
	}

	@Override
	public Movie save(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	public void delete(Movie movie) {
		movieRepository.delete(movie);;
	}

}
