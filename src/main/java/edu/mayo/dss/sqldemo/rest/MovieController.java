package edu.mayo.dss.sqldemo.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import edu.mayo.dss.sqldemo.Movie;
import edu.mayo.dss.sqldemo.MovieRepository;

@Controller
@CrossOrigin
@RequestMapping(path = "/movie")
public class MovieController {
	@Autowired
	private MovieRepository movieRepository;

	
	@GetMapping
	public @ResponseBody Iterable<Movie> getAllMovies() {
		return movieRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));
	}
	
	@PostMapping
	public @ResponseBody Movie addMovie(@RequestBody Movie movie) {
		return movieRepository.save(movie);
	}
	
	@PutMapping
	public @ResponseBody Movie updateMovie(@RequestBody Movie movie) {
		if(movieRepository.existsById(movie.getId())) {
			return movieRepository.save(movie);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
		}
	}
	
	@DeleteMapping
	public @ResponseBody Movie deleteMovie(@RequestParam Long id) {
		Optional<Movie> movieOptional = movieRepository.findById(id);
		if(movieOptional.isPresent()){
			Movie movie = movieOptional.get();
			movieRepository.delete(movie);
			return movie;
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
		}
	}
}
