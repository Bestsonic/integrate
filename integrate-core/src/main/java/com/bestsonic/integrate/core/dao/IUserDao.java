package com.bestsonic.integrate.core.dao;

import com.bestsonic.integrate.core.model.User;

public interface IUserDao extends IBaseDao<User> {

	User getForLogin(String username, String password);
	
	User getForReg(String username);
}
