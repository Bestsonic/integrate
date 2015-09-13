package com.bestsonic.integrate.core.dao;

import java.util.List;

import com.bestsonic.integrate.core.model.History;

public interface IHistoryDao extends IBaseDao<History> {

	History getHistory(String user_id, String movie_id);
	
	List<History> getHistoryPage();
	
	List<History> getHistoryForUserId(String user_id);
	
	List<History> getHistoryForMovie(String movie_id);
	
	List<History> getHistoryForUserName(String username);
	
	List<History> getHistoryForMovieName(String name);
}
