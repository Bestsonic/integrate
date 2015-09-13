package com.bestsonic.integrate.service;

import java.util.List;

import com.bestsonic.integrate.core.model.History;

public interface IHistoryService {
	void addHistory(History history);
	
	void updateHistoryInfo(History history);
	
	void deleteHistoryInfo(String id);
	
	History getHistoryInfo(String id);
	
	History getOneHistoryInfo(String user_id, String movie_id);
	
	List<History> getHistoryPage();
	
	List<History> getHistoryForUserId(String user_id);
	
	List<History> getHistoryForMovieId(String movie_id);
	
	List<History> getHistoryForUserName(String username);
	
	List<History> getHistoryForMovieName(String name);
}
