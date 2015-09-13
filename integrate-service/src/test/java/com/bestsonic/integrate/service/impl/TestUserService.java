package com.bestsonic.integrate.service.impl;

import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;



import com.bestsonic.integrate.core.dao.IUserDao;
import com.bestsonic.integrate.core.model.History;
import com.bestsonic.integrate.core.model.Recommend;
import com.bestsonic.integrate.core.model.User;
import com.bestsonic.integrate.core.utils.AssertObject;
import com.bestsonic.integrate.service.IUserService;
import com.bestsonic.integrate.service.exception.UserExistException;
import com.bestsonic.integrate.service.exception.UserNotExistException;
import com.bestsonic.integrate.service.exception.WrongUserInfoException;

public class TestUserService {
	private User baseUser;
	private List<User> baseList;
	private IUserDao userDao;
	private IUserService userService;
	
	@Before
	public void setUp(){
		baseUser = new User("1","小黄","123456","20","男","浙江宁波","Bestsonic@sina.com","喜剧片/动作片","http://www.baidu.com",new HashSet<History>(),new HashSet<Recommend>());
		baseList = new ArrayList<User>();
		baseList.add(baseUser);
		//创建Dao对象的Mock对象，进入record阶段
		userDao = createMock(IUserDao.class);
		//创建Service, 和Dao关联
		userService = new UserService(userDao);
	}
	
	@Test
	public void testRegistNotExistUser(){
		expect(userDao.getForReg(baseUser.getUsername())).andReturn(null);
		userDao.add(baseUser);
		expectLastCall();
		replay(userDao);
		userService.registUser(baseUser);
		verify(userDao);
	}
	
	@Test(expected=UserExistException.class)
	public void testRegistExistUser(){
		expect(userDao.getForReg(baseUser.getUsername())).andReturn(baseUser);
		userDao.add(baseUser);
		expectLastCall();
		replay(userDao);
		userService.registUser(baseUser);
		verify(userDao);
	}
	
	
	
	@Test
	public void testDeleteExistUser(){
		expect(userDao.load("1")).andReturn(baseUser);
		userDao.delete("1");
		expectLastCall();
		replay(userDao);
		userService.deleteUser("1");
		verify(userDao);
	}

	@Test(expected=UserNotExistException.class)
	public void testDeleteNotExistUser(){
		expect(userDao.load("1")).andReturn(null);
		userDao.delete("1");
		expectLastCall();
		replay(userDao);
		userService.deleteUser("1");
		verify(userDao);
	}
	
	@Test
	public void testUpdateExistUser(){
		expect(userDao.load(baseUser.getId())).andReturn(baseUser);
		userDao.update(baseUser);
		expectLastCall();
		replay(userDao);
		userService.updateUserInfo(baseUser);
		verify(userDao);
	}
	
	@Test(expected=UserNotExistException.class)
	public void testUpdateNotExistUser(){
		expect(userDao.load(baseUser.getId())).andReturn(null);
		userDao.update(baseUser);
		expectLastCall();
		replay(userDao);
		userService.updateUserInfo(baseUser);
		verify(userDao);
	}
	
	@Test
	public void testGet(){
		//记录userDao可能会发生的操作的结果。这与代码指的是当在userDao中调用了load方法并参数为"1"时，会返回baseUser对象。
		expect(userDao.load("1")).andReturn(baseUser);
		//进入测试(replay)阶段
		replay(userDao);
		//完成测试
		User user = userService.get("1");
		//验证测试结果
		AssertObject.assertObject(User.class, baseUser, user);
		//验证交互关系是否正确
		verify(userDao);
	}
	
	@Test
	public void testLoginCorrect(){
		expect(userDao.getForLogin(baseUser.getUsername(), baseUser.getPassword())).andReturn(baseUser);
		replay(userDao);
		User user = userService.login("小黄", "123456");
		AssertObject.assertObject(User.class, baseUser, user);
		verify(userDao);
	}
	
	@Test(expected=WrongUserInfoException.class)
	public void testLoginNotCorrect(){
		expect(userDao.getForLogin(baseUser.getUsername(), baseUser.getPassword())).andReturn(null);
		replay(userDao);
		userService.login("小黄", "123456");
		verify(userDao);
	}
	
	@Test
	public void testGetUserPage(){
		expect(userDao.load()).andReturn(baseList);
		replay(userDao);
		List<User> list = userService.getUserPage();
		AssertObject.assertObject(User.class, baseUser, list.get(0));
		verify(userDao);
	}
}
