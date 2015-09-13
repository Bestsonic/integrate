package com.bestsonic.integrate.core.dao.impl;

import static com.bestsonic.integrate.core.utils.AssertObject.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.bestsonic.integrate.core.dao.IMovieDao;
import com.bestsonic.integrate.core.model.Movie;
import com.bestsonic.integrate.core.utils.AbstractDbUnitTestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:beans.xml"})
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class TestMovieDao extends AbstractDbUnitTestCase {

	private ApplicationContext atx;
	private IMovieDao movieDao;
	private Movie baseMovie = new Movie("1","http://www.baidu.com","2015-08-23","中国","空知英秋","喜剧","10","银魂","坂田银时/志村新八/神乐","日语","银魂","24分钟","http://www.baidu.com");
	
	@Before
	public void setUp() throws DataSetException, IOException, SQLException{
		atx = new ClassPathXmlApplicationContext("beans.xml");
		movieDao =  (IMovieDao) atx.getBean("movieDao");
		backupOneTable("movie");
	}
	
	@After
	public void tearDown() throws DatabaseUnitException, SQLException, IOException{
		resumeTable();
	}
	
	@Test
	public void testAdd() throws IOException, DatabaseUnitException, SQLException{
		IDataSet dataSet = createDateSet("movie");
		DatabaseOperation.TRUNCATE_TABLE.execute(dbunitCon, dataSet);
		Movie movie = new Movie("http://www.baidu.com","2015-08-23","中国","空知英秋","喜剧","10","银魂","坂田银时/志村新八/神乐","日语","银魂","24分钟","http://www.baidu.com");
		movieDao.add(movie);
		assertThat(movie.getId(), notNullValue());
		
		movie = movieDao.load(movie.getId());
		assertEquals(baseMovie.getUrl(), movie.getUrl());
		assertEquals(baseMovie.getDate(), movie.getDate());
		assertEquals(baseMovie.getCountry(), movie.getCountry());
		assertEquals(baseMovie.getDirectors(), movie.getDirectors());
		assertEquals(baseMovie.getType(), movie.getType());
		assertEquals(baseMovie.getRate(), movie.getRate());
		assertEquals(baseMovie.getName(), movie.getName());
		assertEquals(baseMovie.getActors(), movie.getActors());
		assertEquals(baseMovie.getLanguage(), movie.getLanguage());
		assertEquals(baseMovie.getIntro(), movie.getIntro());
		assertEquals(baseMovie.getLength(), movie.getLength());
		assertEquals(baseMovie.getPicture(), movie.getPicture());
		movieDao.delete(movie.getId());
	}
	
	@Test
	public void testLoad() throws IOException, DatabaseUnitException, SQLException{
		IDataSet dataSet = createDateSet("movie");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		Movie movie = movieDao.load("1");
		assertObject(Movie.class, baseMovie, movie);
	}
	@Test
	public void testUpdate() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("movie");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		
		Movie movie = movieDao.load("1");
		movie.setName("银魂2");
		movieDao.update(movie);
		
		assertEquals(baseMovie.getId(), movie.getId());
		assertEquals(baseMovie.getUrl(), movie.getUrl());
		assertEquals(baseMovie.getDate(), movie.getDate());
		assertEquals(baseMovie.getCountry(), movie.getCountry());
		assertEquals(baseMovie.getDirectors(), movie.getDirectors());
		assertEquals(baseMovie.getType(), movie.getType());
		assertEquals(baseMovie.getRate(), movie.getRate());
		assertEquals("银魂2", movie.getName());
		assertEquals(baseMovie.getActors(), movie.getActors());
		assertEquals(baseMovie.getLanguage(), movie.getLanguage());
		assertEquals(baseMovie.getIntro(), movie.getIntro());
		assertEquals(baseMovie.getLength(), movie.getLength());
		assertEquals(baseMovie.getPicture(), movie.getPicture());
		movie.setName("银魂");
		movieDao.update(movie);
		assertObject(Movie.class, baseMovie, movie);
	}
	@Test
	public void testDelete() throws IOException, DatabaseUnitException, SQLException{
		IDataSet dataSet = createDateSet("movie");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		Movie movie = movieDao.load("1");
		int size = movieDao.load().size();
		assertObject(Movie.class, baseMovie, movie);
		movieDao.delete("1");
		assertThat(movieDao.load().size(), equalTo(size - 1));
	}
	@Test
	public void testLoadAll() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("movie");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		List<Movie> list = movieDao.load();
		assertThat(list.size(), not(0));
		movieDao.delete("1");
		assertThat(movieDao.load().size(), equalTo(list.size() - 1));
	}
	
	@Test
	public void testGetMovieByName() throws IOException, DatabaseUnitException, SQLException{
		IDataSet dataSet = createDateSet("movie");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		List<Movie> list = movieDao.getMovieByName("银");
		assertThat(list.size(),not(0));
		assertObject(Movie.class, baseMovie, list.get(0));
	}
	
	@Test
	public void testGetMovieByType() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("movie");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		List<Movie> list = movieDao.getMovieByType("喜");
		assertThat(list.size(),not(0));
		assertObject(Movie.class, baseMovie, list.get(0));
	}
	
}
