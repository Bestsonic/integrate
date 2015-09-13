package com.bestsonic.integrate.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.easymock.EasyMock.*;

import org.junit.Before;
import org.junit.Test;

import com.bestsonic.integrate.core.dao.IHistoryDao;
import com.bestsonic.integrate.core.model.History;
import com.bestsonic.integrate.core.model.Movie;
import com.bestsonic.integrate.core.model.Recommend;
import com.bestsonic.integrate.core.model.User;
import com.bestsonic.integrate.core.utils.AssertObject;
import com.bestsonic.integrate.service.IHistoryService;

public class TestHistoryService {
	private History baseHistory;
	private List<History> baseList;
	private IHistoryDao historyDao;
	private IHistoryService historyService;

	@Before
	public void setUp(){
		baseHistory = new History("1",
				new User("1","小黄","123456","20","男","浙江宁波","Bestsonic@sina.com","喜剧片/动作片","http://www.baidu.com",new HashSet<History>(),new HashSet<Recommend>()),
				new Movie("1","http://www.baidu.com","2015-08-23","中国","空知英秋","喜剧","10","银魂","坂田银时/志村新八/神乐","日语","银魂","24分钟","http://www.baidu.com"),
				"2015-08-23 00:00:00.0","10","非常好！"
			);
		baseList = new ArrayList<History>();
		baseList.add(baseHistory);
		historyDao = createMock(IHistoryDao.class);
		historyService = new HistoryService(historyDao);
	}
	
	@Test
	public void testAddHistory(){
		historyDao.add(baseHistory);
		expectLastCall();
		replay(historyDao);
		
		historyService.addHistory(baseHistory);
		verify(historyDao);
	}
	
	@Test
	public void testUpdateHistory(){
		historyDao.update(baseHistory);
		expectLastCall();
		replay(historyDao);
		
		historyService.updateHistoryInfo(baseHistory);
		verify(historyDao);
	}
	
	@Test
	public void testDeleteHistory(){
		historyDao.delete("1");
		expectLastCall();
		replay(historyDao);
		
		historyService.deleteHistoryInfo("1");
		verify(historyDao);
	}
	
	@Test
	public void testGetHistoryInfo(){
		expect(historyDao.load("1")).andReturn(baseHistory);
		replay(historyDao);
		
		History history = historyService.getHistoryInfo("1");
		AssertObject.assertObject(History.class, baseHistory, history);
		verify(historyDao);
	}
	
	@Test
	public void testGetOneHistoryInfo(){
		expect(historyDao.getHistory(baseHistory.getUser().getId(), baseHistory.getMovie().getId())).andReturn(baseHistory);
		replay(historyDao);
		
		History history = historyService.getOneHistoryInfo(baseHistory.getUser().getId(), baseHistory.getMovie().getId());
		AssertObject.assertObject(History.class, baseHistory, history);
		verify(historyDao);
	}
	
	@Test
	public void testGetHistoryForUserId(){
		expect(historyDao.getHistoryForUserId(baseHistory.getUser().getId())).andReturn(baseList);
		replay(historyDao);
		
		List<History> list = historyService.getHistoryForUserId(baseHistory.getUser().getId());
		AssertObject.assertObject(History.class, baseHistory, list.get(0));
		verify(historyDao);
	}
	@Test
	public void testGetHistoryForMovieId(){
		expect(historyDao.getHistoryForMovie(baseHistory.getMovie().getId())).andReturn(baseList);
		replay(historyDao);
		
		List<History> list = historyService.getHistoryForMovieId(baseHistory.getMovie().getId());
		AssertObject.assertObject(History.class, baseHistory, list.get(0));
		verify(historyDao);
	}
	
	@Test
	public void testGetHistoryForUserName(){
		expect(historyDao.getHistoryForUserName(baseHistory.getUser().getUsername())).andReturn(baseList);
		replay(historyDao);
		
		List<History> list = historyService.getHistoryForUserName(baseHistory.getUser().getUsername());
		AssertObject.assertObject(History.class, baseHistory, list.get(0));
		verify(historyDao);
	}
	
	@Test
	public void testGetHistoryForMovieName(){
		expect(historyDao.getHistoryForMovieName(baseHistory.getMovie().getName())).andReturn(baseList);
		replay(historyDao);
		
		List<History> list = historyService.getHistoryForMovieName(baseHistory.getMovie().getName());
		AssertObject.assertObject(History.class, baseHistory, list.get(0));
		verify(historyDao);
	}
	
	@Test
	public void testGetHistoryPage(){
		expect(historyDao.getHistoryPage()).andReturn(baseList);
		replay(historyDao);
		
		List<History> list = historyService.getHistoryPage();
		AssertObject.assertObject(History.class, baseHistory, list.get(0));
		verify(historyDao);
	}
}
