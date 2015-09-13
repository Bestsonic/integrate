package com.bestsonic.integrate.core.dao.impl;

import static com.bestsonic.integrate.core.utils.AssertObject.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

import com.bestsonic.integrate.core.dao.IUserDao;
import com.bestsonic.integrate.core.model.History;
import com.bestsonic.integrate.core.model.Recommend;
import com.bestsonic.integrate.core.model.User;
import com.bestsonic.integrate.core.utils.AbstractDbUnitTestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:beans.xml"})
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class TestUserDao extends AbstractDbUnitTestCase {

	private ApplicationContext atx;
	private IUserDao userDao;
	private User baseUser = new User("1","小黄","123456","20","男","浙江宁波","Bestsonic@sina.com","喜剧片/动作片","http://www.baidu.com",new HashSet<History>(),new HashSet<Recommend>());
	
	@Before
	public void setUp() throws DataSetException, IOException, SQLException{
		atx = new ClassPathXmlApplicationContext("beans.xml");
		userDao =  (IUserDao) atx.getBean("userDao");
		backupOneTable("user");
	}
	
	@After
	public void tearDown() throws DatabaseUnitException, SQLException, IOException{
		resumeTable();
	}
	
	@Test
	public void testAdd() throws IOException, DatabaseUnitException, SQLException{
		IDataSet dataSet = createDateSet("user");
		DatabaseOperation.TRUNCATE_TABLE.execute(dbunitCon, dataSet);
		User user = new User("小黄","123456","20","男","浙江宁波","Bestsonic@sina.com","喜剧片/动作片","http://www.baidu.com",new HashSet<History>(),new HashSet<Recommend>());
		userDao.add(user);
		assertThat(user.getId(), notNullValue());
		
		user = userDao.load(user.getId());
		assertEquals(baseUser.getUsername(), user.getUsername());
		assertEquals(baseUser.getPassword(), user.getPassword());
		assertEquals(baseUser.getAge(), user.getAge());
		assertEquals(baseUser.getSex(), user.getSex());
		assertEquals(baseUser.getLocation(), user.getLocation());
		assertEquals(baseUser.getMail(), user.getMail());
		assertEquals(baseUser.getTags(), user.getTags());
		assertEquals(baseUser.getPortrait(), user.getPortrait());
		userDao.delete(user.getId());
	}
	
	@Test
	public void testLoad() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("user");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		User user = userDao.load("1");
		assertObject(User.class, baseUser, user);
	}
	@Test
	public void testUpdate() throws IOException, DatabaseUnitException, SQLException{
		IDataSet dataSet = createDateSet("user");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		User user = userDao.load("1");
		user.setUsername("小黑");
		userDao.update(user);
		assertEquals(user.getId(), baseUser.getId());
		assertEquals(user.getPassword(), baseUser.getPassword());
		assertEquals(user.getUsername(), "小黑");
		assertEquals(baseUser.getAge(), user.getAge());
		assertEquals(baseUser.getSex(), user.getSex());
		assertEquals(baseUser.getLocation(), user.getLocation());
		assertEquals(baseUser.getMail(), user.getMail());
		assertEquals(baseUser.getTags(), user.getTags());
		assertEquals(baseUser.getPortrait(), user.getPortrait());
		user.setUsername("小黄");
		userDao.update(user);
		assertObject(User.class, baseUser, user);
	}
	@Test
	public void testDelete() throws IOException, DatabaseUnitException, SQLException{
		IDataSet dataSet = createDateSet("user");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		User user = userDao.load("1");
		assertObject(User.class, baseUser, user);
		userDao.delete("1");
		assertThat(userDao.load().size(), equalTo(0));
		
	}
	@Test
	public void testLoadAll() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("user");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		List<User> list = userDao.load();
		assertThat(list.size(), not(0));
		userDao.delete("1");
		assertThat(userDao.load().size(), equalTo(0));
	}
	
	@Test
	public void testGetForLogin() throws DatabaseUnitException, SQLException, IOException{
		IDataSet dataSet = createDateSet("user");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		User user = userDao.getForLogin("小黄","123");
		assertNull(user);
		user = userDao.getForLogin("小黄", "123456");
		assertNotNull(user);
	}
	
	@Test
	public void testGetForReg() throws IOException, DatabaseUnitException, SQLException{
		IDataSet dataSet = createDateSet("user");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, dataSet);
		User user = userDao.getForReg("小黄");
		assertNotNull(user);
	}
}
