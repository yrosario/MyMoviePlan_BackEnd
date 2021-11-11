package com.mymovieplan.api.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymovieplan.api.model.Movie;
import com.mymovieplan.api.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getListOfMovies(){
		
		List<Movie> movies = movieService.findAll();
		
		if(movies.isEmpty())
			return new ResponseEntity<>("No Movies were found on the list", HttpStatus.OK);
		
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable("id") Long id){
		
		Movie movie = movieService.findMovieById(id);
		
		if(movie == null)
			return new ResponseEntity<>("Movie was not found", HttpStatus.OK);
		
		return new ResponseEntity<>(movie, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addMovie(@RequestBody Movie movie){
		
		Movie saveMovie = movieService.save(movie);
		
		if(saveMovie == null)
			return new ResponseEntity<>("Movie was not saved", HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(saveMovie, HttpStatus.CREATED);
	
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie){
		
		Movie retrieveMovie = movieService.findMovieById(movie.getId());
		if(retrieveMovie == null)
			return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
		
		movieService.save(movie);
		return new ResponseEntity<>(movie, HttpStatus.OK);
		}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteMovie(@RequestBody HashMap<String, Long> request){
		Long id = request.get("id");
		Movie movie = movieService.findMovieById(id);
		if(movie == null)
			return new ResponseEntity<>("Movie not found", HttpStatus.BAD_REQUEST);
		
		movieService.delete(movie);
		
		return new ResponseEntity<>("Movie Delete Successfully", HttpStatus.OK);
	}

}
