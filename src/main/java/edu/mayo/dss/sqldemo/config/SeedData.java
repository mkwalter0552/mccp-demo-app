package edu.mayo.dss.sqldemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.mayo.dss.sqldemo.Movie;
import edu.mayo.dss.sqldemo.MovieRepository;

@Configuration
public class SeedData {
	@Autowired
	private MovieRepository movieRepository;
	
	@Bean
	public void populateDatabase() {
		if(!movieRepository.findByTitle("Coraline").isPresent()) {
			movieRepository.save(new Movie("Coraline", 4));
		}	
		if(!movieRepository.findByTitle("Spider-Man: Homecoming").isPresent()) {
			movieRepository.save(new Movie("Spider-Man: Homecoming", 4));
		}
		if(!movieRepository.findByTitle("Pacific Rim").isPresent()) {
			movieRepository.save(new Movie("Pacific Rim", 4));
		}
		if(!movieRepository.findByTitle("Ghost in the Shell (1995)").isPresent()) {
			movieRepository.save(new Movie("Ghost in the Shell (1995)", 5));
		}
		if(!movieRepository.findByTitle("Virtuosity").isPresent()) {
			movieRepository.save(new Movie("Virtuosity", 3));
		}
		if(!movieRepository.findByTitle("Spaceballs").isPresent()) {
			movieRepository.save(new Movie("Spaceballs", 5));
		}
	}
}
