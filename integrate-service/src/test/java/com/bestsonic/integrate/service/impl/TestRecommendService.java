package com.bestsonic.integrate.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;

import com.bestsonic.integrate.core.dao.IRecommendDao;
import com.bestsonic.integrate.core.model.History;
import com.bestsonic.integrate.core.model.Movie;
import com.bestsonic.integrate.core.model.Recommend;
import com.bestsonic.integrate.core.model.User;
import com.bestsonic.integrate.core.utils.AssertObject;
import com.bestsonic.integrate.service.IRecommendService;

public class TestRecommendService {

	private Recommend baseRecommend;
	private List<Recommend> baseList;
	private IRecommendDao recommendDao;
	private IRecommendService recommendService;
	
	@Before
	public void setUp(){
		baseRecommend = new Recommend("1", "99", 
				new User("1","小黄","123456","20","男","浙江宁波","Bestsonic@sina.com","喜剧片/动作片","http://www.baidu.com",new HashSet<History>(),new HashSet<Recommend>()), 
				new Movie("1","http://www.baidu.com","2015-08-23","中国","空知英秋","喜剧","10","银魂","坂田银时/志村新八/神乐","日语","银魂","24分钟","http://www.baidu.com")
		);
		
		baseList = new ArrayList<Recommend>();
		baseList.add(baseRecommend);
		recommendDao = createMock(IRecommendDao.class);
		recommendService = new RecommendService(recommendDao);
	}
	
	@Test
	public void testGetRecommend(){
		expect(recommendDao.load("1")).andReturn(baseRecommend);
		replay(recommendDao);
		
		Recommend recommend = recommendService.getRecommend("1");
		AssertObject.assertObject(Recommend.class, baseRecommend, recommend);
		verify(recommendDao);
	}
	
	@Test
	public void testGetRecommendPageForUser(){
		expect(recommendDao.getRecommendForUser(baseRecommend.getUser().getId())).andReturn(baseList);
		replay(recommendDao);
		
		List<Recommend> list = recommendService.getRecommendPageForUser(baseRecommend.getUser().getId());
		AssertObject.assertObject(Recommend.class, baseRecommend, list.get(0));
		verify(recommendDao);
	}
}
