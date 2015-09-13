package com.bestsonic.integrate.core.dao;

import java.util.List;

import com.bestsonic.integrate.core.model.Recommend;

public interface IRecommendDao extends IBaseDao<Recommend> {

	List<Recommend> getRecommendForUser(String user_id);
}
