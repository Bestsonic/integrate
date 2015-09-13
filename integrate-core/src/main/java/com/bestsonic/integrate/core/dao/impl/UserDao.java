package com.bestsonic.integrate.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.bestsonic.integrate.core.dao.IUserDao;
import com.bestsonic.integrate.core.model.User;

@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {
	
	@Override
	public User getForLogin(String username, String password) {
		return (User) getSession().createQuery("from User where username = :username and password = :password")
				.setParameter("username", username)
				.setParameter("password", password)
				.uniqueResult();
	}

	@Override
	public User getForReg(String username) {
		return (User) getSession().createQuery("from User where username = :username")
				.setParameter("username", username)
				.uniqueResult();
	}
}
