package com.bestsonic.integrate.service;

import java.util.List;

import com.bestsonic.integrate.core.model.User;

public interface IUserService {

	void registUser(User user);
	
	void updateUserInfo(User user);
	
	void deleteUser(String id);
	
	User get(String id);
	
	User login(String username, String password);
	
	List<User> getUserPage();
}
