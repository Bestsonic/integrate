package com.bestsonic.integrate.service.impl;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.bestsonic.integrate.core.dao.IMovieDao;
import com.bestsonic.integrate.core.model.Movie;
import com.bestsonic.integrate.core.utils.AssertObject;
import com.bestsonic.integrate.service.IMovieService;

public class TestMovieService {
	private Movie baseMovie;
	private List<Movie> baseList;
	private IMovieDao movieDao;
	private IMovieService movieService;

	@Before
	public void setUp(){
		baseMovie = new Movie("1","http://www.baidu.com","2015-08-23","中国","空知英秋","喜剧","10","银魂","坂田银时/志村新八/神乐","日语","银魂","24分钟","http://www.baidu.com");
		baseList = new ArrayList<Movie>();
		baseList.add(baseMovie);
		movieDao = createMock(IMovieDao.class);
		movieService = new MovieService(movieDao);
	}
	
	@Test
	public void testAddMovie(){
		movieDao.add(baseMovie);
		expectLastCall();
		replay(movieDao);
		movieService.addMovie(baseMovie);
		verify(movieDao);
	}
	
	@Test
	public void testUpdateMovieInfo(){
		movieDao.update(baseMovie);
		expectLastCall();
		replay(movieDao);
		movieService.updateMovieInfo(baseMovie);
		verify(movieDao);
	}
	
	@Test
	public void testDeleteMovieInfo(){
		movieDao.delete("1");
		expectLastCall();
		replay(movieDao);
		movieService.deleteMovie("1");
		verify(movieDao);
	}
	
	@Test
	public void testGetMovieById(){
		expect(movieDao.load("1")).andReturn(baseMovie);
		replay(movieDao);
		Movie movie = movieService.getMovieById("1");
		AssertObject.assertObject(Movie.class, baseMovie, movie);
		verify(movieDao);
	}
	
	@Test
	public void testGetMoviePage(){
		expect(movieDao.getMoviePages()).andReturn(baseList);
		replay(movieDao);
		List<Movie> list = movieService.getMoviePage();
		AssertObject.assertObject(Movie.class, baseMovie, list.get(0));
		verify(movieDao);
	}
	
	@Test
	public void testGetMoviePageByName(){
		expect(movieDao.getMovieByName("银魂")).andReturn(baseList);
		replay(movieDao);
		List<Movie> list = movieService.getMoviePageByName("银魂");
		AssertObject.assertObject(Movie.class, baseMovie, list.get(0));
		verify(movieDao);
	}
	
	@Test
	public void testGetMoviePageByType(){
		expect(movieDao.getMovieByType("喜剧")).andReturn(baseList);
		replay(movieDao);
		List<Movie> list = movieService.getMoviePageByType("喜剧");
		AssertObject.assertObject(Movie.class, baseMovie, list.get(0));
		verify(movieDao);
	}
}
