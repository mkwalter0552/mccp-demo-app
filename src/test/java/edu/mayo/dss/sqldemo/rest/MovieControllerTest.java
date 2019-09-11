package edu.mayo.dss.sqldemo.rest;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import edu.mayo.dss.sqldemo.Movie;
import edu.mayo.dss.sqldemo.MovieRepository;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class MovieControllerTest {
	@Mock
	private MovieRepository movieRepository;
	
	@InjectMocks
	private MovieController movieController;
	

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllMovies() {
		// given a mock MovieRepository
		
		// when MovieController.getAllMovies() is called
		movieController.getAllMovies();
		
		// then MovieRepository.findAll(...) is called once
		verify(movieRepository, times(1)).findAll(any());
	}

	@Test
	public void testAddMovie() {
		// given a mock MovieRepository
		// given a new movie
		Movie movie = new Movie("Mocl Movie", 5);
		
		// when MovieController.addMovie(@RequestBody Movie movie) is called
		movieController.addMovie(movie);
		
		// then MovieRepository.save(Movie) is called once with the new movie
		verify(movieRepository, times(1)).save(eq(movie));
	}

//	@Test
//	public void testUpdateMovie() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeleteMovie() {
//		fail("Not yet implemented");
//	}

}
