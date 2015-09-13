package com.bestsonic.integrate.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bestsonic.integrate.core.dao.IRecommendDao;
import com.bestsonic.integrate.core.model.Recommend;
import com.bestsonic.integrate.service.IRecommendService;
import com.bestsonic.integrate.service.exception.RecommendCRUDFailedException;

@Service("recommendService")
public class RecommendService implements IRecommendService {

	private IRecommendDao recommendDao;
	
	public RecommendService() {
	}
	
	public RecommendService(IRecommendDao recommendDao) {
		this.recommendDao = recommendDao;
	}

	@Override
	public Recommend getRecommend(String id) {
		Recommend recommend = null;
		try {
			recommend = recommendDao.load(id);
		} catch (Exception e) {
			throw new RecommendCRUDFailedException("获取推荐信息失败！");
		}
		return recommend;
	}

	@Override
	public List<Recommend> getRecommendPageForUser(String user_id) {
		List<Recommend> list;
		try {
			list = recommendDao.getRecommendForUser(user_id);
		} catch (Exception e) {
			throw new RecommendCRUDFailedException("获取推荐列表失败！");
		}
		return list;
	}

}
