package com.bestsonic.integrate.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bestsonic.integrate.core.dao.IMovieDao;
import com.bestsonic.integrate.core.model.Movie;
import com.bestsonic.integrate.service.IMovieService;
import com.bestsonic.integrate.service.exception.MovieCRUDFailedException;

@Service("movieService")
public class MovieService implements IMovieService {
	
	private IMovieDao movieDao;
	
	public MovieService() {
	}

	public MovieService(IMovieDao movieDao) {
		this.movieDao = movieDao;
	}

	@Override
	public void addMovie(Movie movie) {
		try{
			movieDao.add(movie);
		}
		catch(Exception e){
			throw new MovieCRUDFailedException("电影信息添加错误！请检查电影信息！");
		}
	}

	@Override
	public void updateMovieInfo(Movie movie) {
		try{
			movieDao.update(movie);
		}
		catch(Exception e){
			throw new MovieCRUDFailedException("电影信息更新错误！请检查电影信息！");
		}
	}

	@Override
	public void deleteMovie(String id) {
		try{
			movieDao.delete(id);
		}
		catch(Exception e){
			throw new MovieCRUDFailedException("电影信息删除失败！");
		}
	}

	@Override
	public Movie getMovieById(String id) {
		Movie movie = null;
		try {
			movie = movieDao.load(id);
		} catch (Exception e) {
			throw new MovieCRUDFailedException("电影信息获取失败！");
		}
		return movie;
	}

	@Override
	public List<Movie> getMoviePage() {
		List<Movie> list = null;
		try {
			list = movieDao.getMoviePages();
		} catch (Exception e) {
			throw new MovieCRUDFailedException("电影信息获取失败！");
		}
		return list;
	}

	@Override
	public List<Movie> getMoviePageByName(String name) {
		List<Movie> list = null;
		try {
			list = movieDao.getMovieByName(name);
		} catch (Exception e) {
			throw new MovieCRUDFailedException("电影信息获取失败！");
		}
		return list;
	}

	@Override
	public List<Movie> getMoviePageByType(String type) {
		List<Movie> list = null;
		try {
			list = movieDao.getMovieByType(type);
		} catch (Exception e) {
			throw new MovieCRUDFailedException("电影信息获取失败！");
		}
		return list;
	}

}
