package edu.mayo.dss.sqldemo;

import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
	public Optional<Movie> findByTitle(String title);
	public Iterable<Movie> findAll(Sort sort);
}
