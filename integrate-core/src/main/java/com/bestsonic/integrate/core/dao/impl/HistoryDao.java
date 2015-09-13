package com.bestsonic.integrate.core.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bestsonic.integrate.core.dao.IHistoryDao;
import com.bestsonic.integrate.core.model.History;

@Repository("historyDao")
public class HistoryDao extends BaseDao<History> implements IHistoryDao {

	@Override
	public History getHistory(String user_id, String movie_id) {
		return (History) getSession().createQuery("select his from History his join his.user user join his.movie movie where user.id = :user_id and movie.id = :movie_id")
			.setParameter("user_id", user_id)
			.setParameter("movie_id", movie_id)
			.uniqueResult();
	}
	
	@Override
	public List<History> getHistoryForUserId(String user_id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("user_id", user_id);
		return super.getPage("from History where user_id = :user_id", params);
	}

	@Override
	public List<History> getHistoryForMovie(String movie_id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("movie_id", movie_id);
		return super.getPage("from History where movie_id = :movie_id", params);
	}

	@Override
	public List<History> getHistoryForUserName(String username) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		return super.getPage("select his from History his join his.user user where user.username = :username", params);
	}

	@Override
	public List<History> getHistoryForMovieName(String name) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", name);
		return super.getPage("select his from History his join his.movie movie where movie.name = :name ", params);
	}

	@Override
	public List<History> getHistoryPage() {
		
		return super.getPage("from History", null);
	}

	
}
