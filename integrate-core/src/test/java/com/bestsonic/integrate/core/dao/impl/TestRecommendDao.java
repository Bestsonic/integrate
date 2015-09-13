package com.bestsonic.integrate.core.dao.impl;

import static com.bestsonic.integrate.core.utils.AssertObject.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
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

import com.bestsonic.integrate.core.dao.IRecommendDao;
import com.bestsonic.integrate.core.model.History;
import com.bestsonic.integrate.core.model.Movie;
import com.bestsonic.integrate.core.model.Recommend;
import com.bestsonic.integrate.core.model.User;
import com.bestsonic.integrate.core.utils.AbstractDbUnitTestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:beans.xml"})
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class TestRecommendDao extends AbstractDbUnitTestCase {

	private ApplicationContext atx;
	private IRecommendDao recommendDao;
	private Recommend baseRecommend = new Recommend("1", "99", 
			new User("1","小黄","123456","20","男","浙江宁波","Bestsonic@sina.com","喜剧片/动作片","http://www.baidu.com",new HashSet<History>(),new HashSet<Recommend>()), 
			new Movie("1","http://www.baidu.com","2015-08-23","中国","空知英秋","喜剧","10","银魂","坂田银时/志村新八/神乐","日语","银魂","24分钟","http://www.baidu.com")
	);
	
	@Before
	public void setUp() throws DataSetException, IOException, SQLException{
		atx = new ClassPathXmlApplicationContext("beans.xml");
		recommendDao =  (IRecommendDao) atx.getBean("recommendDao");
		backupCustomTable(new String[]{"recommend","user","movie"});
	}
	
	@After
	public void tearDown() throws DatabaseUnitException, SQLException, IOException{
		resumeTable();
	}
	
	@Test
	public void testAdd() throws IOException, DatabaseUnitException, SQLException{
		IDataSet dataSet = createDateSet("recommend");
		DatabaseOperation.TRUNCATE_TABLE.execute(dbunitCon, dataSet);
		Recommend recommend = new Recommend("99", 
				new User("1","小黄","123456","20","男","浙江宁波","Bestsonic@sina.com","喜剧片/动作片","http://www.baidu.com",new HashSet<History>(),new HashSet<Recommend>()), 
				new Movie("1","http://www.baidu.com","2015-08-23","中国","空知英秋","喜剧","10","银魂","坂田银时/志村新八/神乐","日语","银魂","24分钟","http://www.baidu.com")
		); 
		recommendDao.add(recommend);
		assertThat(recommend.getId(), notNullValue());
		
		recommend = recommendDao.load(recommend.getId());
		assertEquals(baseRecommend.getRate(),recommend.getRate());
		assertObject(User.class, baseRecommend.getUser(), recommend.getUser());
		assertObject(Movie.class, baseRecommend.getMovie(), recommend.getMovie());
		
		recommendDao.delete(recommend.getId());
	}
	
	@Test
	public void testLoad() throws IOException, DatabaseUnitException, SQLException{
		IDataSet dataSet = createDateSet("recommend");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		
		Recommend recommend = recommendDao.load("1");
		assertObject(Recommend.class, baseRecommend, recommend);
	}
	@Test
	public void testUpdate() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("recommend");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		Recommend recommend = recommendDao.load("1");
		recommend.setRate("198");
		recommendDao.update(recommend);
		assertEquals("198",recommend.getRate());
	
		recommend.setRate("99");
		recommendDao.update(recommend);
		assertObject(Recommend.class, baseRecommend, recommend);
	}
	@Test
	public void testDelete() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("recommend");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		Recommend recommend = recommendDao.load("1");
		assertObject(Recommend.class, baseRecommend, recommend);
		
		recommendDao.delete("1");
		assertThat(recommendDao.load().size(), equalTo(0));
	}
	@Test
	public void testLoadAll() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("recommend");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		List<Recommend> list = recommendDao.load();
		assertThat(list.size(), not(0));
		recommendDao.delete("1");
		assertThat(recommendDao.load().size(), equalTo(0));
	}
	
	@Test()
	public void testGetRecommendForUser() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("recommend");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		
		List<Recommend> list = recommendDao.getRecommendForUser("1");
		assertObject(Recommend.class, baseRecommend, list.get(0));
	}
}
