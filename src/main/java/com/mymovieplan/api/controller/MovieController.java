package com.mymovieplan.api.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mymovieplan.api.model.Description;
import com.mymovieplan.api.model.Image;
import com.mymovieplan.api.model.Movie;
import com.mymovieplan.api.service.DescriptionService;
import com.mymovieplan.api.service.ImageService;
import com.mymovieplan.api.service.MovieService;

@CrossOrigin
@RestController
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private DescriptionService descriptionService;
	
	@Autowired
	private ImageService imageService;

	
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
		
		Description description = movie.getDescription();
		if(description == null)
			description = new Description();
		
		descriptionService.save(description);
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
	
	@GetMapping("/{id}/desc")
	public ResponseEntity<?> getMovieDesc(@PathVariable("id") Long id){
		Movie movie = movieService.findMovieById(id);
		if(movie == null)
			return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(movie.getDescription(), HttpStatus.OK);
	}
	
	@PostMapping("/{id}/desc")
	public ResponseEntity<?> addMovieDesc(@PathVariable("id") Long id, @RequestBody HashMap<String, String> request){
		Movie movie = movieService.findMovieById(id);
		if(movie == null)
			return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
		
		String strDesc= request.get("desc");
		if(strDesc.isEmpty())
			return new ResponseEntity<>("Bad movie description", HttpStatus.BAD_REQUEST);
		
		
		Description description = new Description(movie, strDesc);
		descriptionService.save(description);
		movie.setDescription(description);
		movieService.save(movie);
		
		return new ResponseEntity<>(strDesc, HttpStatus.OK);
	}
	
	@PutMapping("/{id}/desc")
	public ResponseEntity<?> updateMovieDesc(@PathVariable("id") Long id, @RequestBody HashMap<String, String> request){
		Movie movie = movieService.findMovieById(id);
		if(movie == null)
			return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
		
		Long descId= Long.parseLong(request.get("id"));
		if(descId == null)
			return new ResponseEntity<>("Bad movie description", HttpStatus.BAD_REQUEST);
		
		
		Description description = descriptionService.findById(descId);
		if(description == null)
			return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
		
		String desc = request.get("desc");
		if(desc.isEmpty())
			return new ResponseEntity<>("Bad movie description", HttpStatus.BAD_REQUEST);
		
		description.setDescription(desc);
		descriptionService.save(description);

		
		return new ResponseEntity<>("Description updated successfully", HttpStatus.OK);
	}
	
    @PostMapping("/{id}/upload")
    public ResponseEntity<?> fileUpload(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
    	
		Movie movie = movieService.findMovieById(id);
		if(movie == null)
			return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
    	
    	try {
    	    byte[] byteImage = file.getBytes();
    	    Image image = new Image();
    	    image.setMovie(movie);
    	    image.setImage(byteImage);
    	   
    	    movie.setImages(image);
    	    movieService.save(movie);
    	    imageService.save(image);
    	    
    	    return new ResponseEntity<>("Image Save successfully", HttpStatus.CREATED);
    	}catch (Exception e){
    		return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    	}
    }
    
    @GetMapping("/{id}/img")
    public ResponseEntity<?> getImages(@PathVariable("id") Long id){
    	
    	Movie movie = movieService.findMovieById(id);
		if(movie == null)
			return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
		
		List<Image> images =  movie.getImages();
		byte[] encodeImg = images.get(0).getImage();
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(encodeImg);
    	
    }

}
