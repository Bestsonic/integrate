package com.bestsonic.integrate.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bestsonic.integrate.core.dao.IUserDao;
import com.bestsonic.integrate.core.model.User;
import com.bestsonic.integrate.service.IUserService;
import com.bestsonic.integrate.service.exception.UserCRUDFailedException;
import com.bestsonic.integrate.service.exception.UserExistException;
import com.bestsonic.integrate.service.exception.UserNotExistException;
import com.bestsonic.integrate.service.exception.WrongUserInfoException;

@Service("userService")
public class UserService implements IUserService {
	
	private IUserDao userDao;
	
	public UserService() {
		super();
	}

	public UserService(IUserDao userDao){
		this.userDao = userDao;
	}

	public void registUser(User user) {
		User u = userDao.getForReg(user.getUsername());
		if(u != null) throw new UserExistException("注册用户已存在！");
		try{
			userDao.add(user);
		}
		catch(Exception e){
			throw new UserCRUDFailedException("用户注册失败！");
		}
	}

	public void updateUserInfo(User user) {
		User u = userDao.load(user.getId());
		if(u == null) throw new UserNotExistException("用户不存在");
		try{
			userDao.update(user);
		}
		catch(Exception e){
			throw new UserCRUDFailedException("更新用户信息失败！");
		}
	}

	public void deleteUser(String id) {
		User u = userDao.load(id);
		if(u == null) throw new UserNotExistException("用户不存在");
		try{
			userDao.delete(id);
		}
		catch(Exception e){
			throw new UserCRUDFailedException("用户删除失败！");
		}
	}
	
	@Override
	public User get(String id) {
		User u = null;
		try{
			u = userDao.load(id);
		}
		catch(Exception e){
			throw new UserCRUDFailedException("用户查询失败！");
		}
		return u;
	}

	public User login(String username, String password) {
		User u = userDao.getForLogin(username, password);
		if(u == null) throw new WrongUserInfoException("用户名密码错误！");
		return u;
	}

	public List<User> getUserPage() {
		List<User> list = null;
		try{
			list = userDao.load();
		}
		catch(Exception e){
			throw new UserCRUDFailedException("获取用户信息失败！");
		}
		return list;
	}

	
}
