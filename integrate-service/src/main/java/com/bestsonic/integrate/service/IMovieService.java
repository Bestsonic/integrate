package com.bestsonic.integrate.service;

import java.util.List;

import com.bestsonic.integrate.core.model.Movie;

public interface IMovieService {

	void addMovie(Movie movie);
	
	void updateMovieInfo(Movie movie);
	
	void deleteMovie(String id);
	
	Movie getMovieById(String id);
	
	List<Movie> getMoviePage();
	
	List<Movie> getMoviePageByName(String name);
	
	List<Movie> getMoviePageByType(String type);
	
}
