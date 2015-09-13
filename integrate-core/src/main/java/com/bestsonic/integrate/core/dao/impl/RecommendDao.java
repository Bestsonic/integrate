package com.bestsonic.integrate.core.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bestsonic.integrate.core.dao.IRecommendDao;
import com.bestsonic.integrate.core.model.Recommend;

@Repository("recommendDao")
public class RecommendDao extends BaseDao<Recommend> implements IRecommendDao {

	@Override
	public List<Recommend> getRecommendForUser(String user_id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("user_id", user_id);
		return super.getPage("select rec from Recommend rec join fetch rec.user user where user.id = :user_id", params);
	}
}
