package com.bestsonic.integrate.service;

import java.util.List;

import com.bestsonic.integrate.core.model.Recommend;

public interface IRecommendService {

	Recommend getRecommend(String id);
	
	List<Recommend> getRecommendPageForUser(String user_id);
}
