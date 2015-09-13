package com.bestsonic.integrate.core.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bestsonic.integrate.core.dao.IMovieDao;
import com.bestsonic.integrate.core.model.Movie;

@Repository("movieDao")
public class MovieDao extends BaseDao<Movie> implements IMovieDao {
	
	@Override
	public List<Movie> getMovieByName(String name) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "%"+ name +"%");
		return super.getPage("from Movie where name like :name", params);
	}

	@Override
	public List<Movie> getMovieByType(String type) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", "%"+ type +"%");
		return super.getPage("from Movie where type like :type", params);
	}

	@Override
	public List<Movie> getMoviePages() {
		return super.getPage("from Movie", null);
	}

}
