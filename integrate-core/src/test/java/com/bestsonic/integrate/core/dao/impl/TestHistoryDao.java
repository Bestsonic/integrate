package com.bestsonic.integrate.core.dao.impl;
import static org.hamcrest.Matchers.*;
import static com.bestsonic.integrate.core.utils.AssertObject.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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

import com.bestsonic.integrate.core.dao.IHistoryDao;
import com.bestsonic.integrate.core.model.History;
import com.bestsonic.integrate.core.model.Movie;
import com.bestsonic.integrate.core.model.Recommend;
import com.bestsonic.integrate.core.model.User;
import com.bestsonic.integrate.core.utils.AbstractDbUnitTestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:beans.xml"})
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class TestHistoryDao extends AbstractDbUnitTestCase {

	private ApplicationContext atx;
	private IHistoryDao historyDao;
	private History baseHistory = new History("1",
				new User("1","小黄","123456","20","男","浙江宁波","Bestsonic@sina.com","喜剧片/动作片","http://www.baidu.com",new HashSet<History>(),new HashSet<Recommend>()),
				new Movie("1","http://www.baidu.com","2015-08-23","中国","空知英秋","喜剧","10","银魂","坂田银时/志村新八/神乐","日语","银魂","24分钟","http://www.baidu.com"),
				"2015-08-23 00:00:00.0","10","非常好！"
			);
	
	@Before
	public void setUp() throws DataSetException, IOException, SQLException{
		atx = new ClassPathXmlApplicationContext("beans.xml");
		historyDao =  (IHistoryDao) atx.getBean("historyDao");
		backupCustomTable(new String[]{"history","movie","user"});
	}
	
	@After
	public void tearDown() throws DatabaseUnitException, SQLException, IOException{
		resumeTable();
	}
	
	@Test
	public void testAdd() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("history");
		DatabaseOperation.TRUNCATE_TABLE.execute(dbunitCon, dataSet);
		
		
		History history = new History(
				new User("1","小黄","123456","20","男","浙江宁波","Bestsonic@sina.com","喜剧片/动作片","http://www.baidu.com",new HashSet<History>(),new HashSet<Recommend>()),
				new Movie("1","http://www.baidu.com","2015-08-23","中国","空知英秋","喜剧","10","银魂","坂田银时/志村新八/神乐","日语","银魂","24分钟","http://www.baidu.com"),
				"2015-08-23 00:00:00.0","10","非常好！"
			);
		historyDao.add(history);
		assertThat(history.getId(), notNullValue());
		
		history = historyDao.load(history.getId());
		assertEquals(baseHistory.getComment(), history.getComment());
		assertEquals(baseHistory.getDate(), history.getDate());
		assertEquals(baseHistory.getRate(), history.getRate());
		assertObject(User.class, baseHistory.getUser(), history.getUser());
		assertObject(Movie.class, baseHistory.getMovie(), history.getMovie());
		historyDao.delete(history.getId());
	}
	
	@Test
	public void testLoad() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("history");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		History history = historyDao.load("1");
		assertObject(History.class,baseHistory, history);
	}
	@Test
	public void testUpdate() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("history");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		
		History history = historyDao.load("1");
		history.setRate("100");
		historyDao.update(history);
		
		assertEquals(baseHistory.getComment(), history.getComment());
		assertEquals(baseHistory.getDate(), history.getDate());
		assertEquals("100", history.getRate());
		
		history.setRate("10");
		historyDao.update(history);
		assertObject(History.class,baseHistory, history);
	}
	@Test
	public void testDelete() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("history");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		History history = historyDao.load("1");
		assertObject(History.class,baseHistory, history);
		historyDao.delete("1");
		assertThat(historyDao.load().size(), equalTo(0));
	}
	@Test
	public void testLoadAll() throws IOException, DatabaseUnitException, SQLException{
		IDataSet dataSet = createDateSet("history");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		
		List<History> list = historyDao.load();
		assertThat(list.size(),not(0));
		historyDao.delete("1");
		assertThat(historyDao.load().size(), equalTo(0));
	}
	
	@Test
	public void testGetHistory() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("history");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		
		History history = historyDao.getHistory("1","1");
		assertObject(History.class, baseHistory, history);
	}
	
	@Test
	public void testGetHistoryPage() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("history");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		
		List<History> list = historyDao.getHistoryPage();
		assertObject(History.class, baseHistory, list.get(0));
	}
	
	@Test
	public void testGetHistoryForUserId() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("history");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		List<History> list = historyDao.getHistoryForUserId("1");
		assertObject(History.class, baseHistory, list.get(0));
	}
	
	@Test
	public void testGetHistoryForMovie() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("history");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		
		List<History> list = historyDao.getHistoryForMovie("1");
		assertObject(History.class, baseHistory, list.get(0));
	}
	
	@Test
	public void testGetHistoryForUserName() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("history");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		
		List<History> list = historyDao.getHistoryForUserName("小黄");
		assertObject(History.class, baseHistory, list.get(0));
	}
	
	@Test
	public void testGetHistoryForMovieName() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("history");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		
		List<History> list = historyDao.getHistoryForMovieName("银魂");
		assertObject(History.class, baseHistory, list.get(0));
	}
}
