package com.bestsonic.integrate.core.dao;

import java.util.List;

import com.bestsonic.integrate.core.model.Movie;

public interface IMovieDao extends IBaseDao<Movie> {

	List<Movie> getMovieByName(String name);
	
	List<Movie> getMovieByType(String type);
	
	List<Movie> getMoviePages();
}
