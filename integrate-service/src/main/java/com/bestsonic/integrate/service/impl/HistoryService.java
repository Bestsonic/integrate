package com.bestsonic.integrate.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bestsonic.integrate.core.dao.IHistoryDao;
import com.bestsonic.integrate.core.model.History;
import com.bestsonic.integrate.service.IHistoryService;
import com.bestsonic.integrate.service.exception.HistoryCRUDFailedException;

@Service("historyService")
public class HistoryService implements IHistoryService {

	private IHistoryDao historyDao;
	
	public HistoryService() {
		
	}
	
	public HistoryService(IHistoryDao historyDao) {
		this.historyDao = historyDao;
	}
	
	@Override
	public void addHistory(History history) {
		try{
			historyDao.add(history);
		}
		catch(Exception e){
			throw new HistoryCRUDFailedException("历史记录添加失败！");
		}
	}

	@Override
	public void updateHistoryInfo(History history) {
		try{
			historyDao.update(history);
		}
		catch(Exception e){
			throw new HistoryCRUDFailedException("历史记录更新失败！");
		}
	}

	@Override
	public void deleteHistoryInfo(String id) {
		try{
			historyDao.delete(id);
		}
		catch(Exception e){
			throw new HistoryCRUDFailedException("历史记录删除失败！");
		}
	}

	@Override
	public History getHistoryInfo(String id) {
		History history = null;
		try {
			history = historyDao.load(id);
		} catch (Exception e) {
			throw new HistoryCRUDFailedException("历史记录获取失败！");
		}
		return history;
	}

	@Override
	public History getOneHistoryInfo(String user_id, String movie_id) {
		History history = null;
		try {
			history = historyDao.getHistory(user_id, movie_id);
		} catch (Exception e) {
			throw new HistoryCRUDFailedException("历史记录获取失败！");
		}
		return history;
	}

	@Override
	public List<History> getHistoryForUserId(String user_id) {
		List<History> list = null;
		try {
			list = historyDao.getHistoryForUserId(user_id);
		} catch (Exception e) {
			throw new HistoryCRUDFailedException("历史记录获取失败！");
		}
		return list;
	}

	@Override
	public List<History> getHistoryForMovieId(String movie_id) {
		List<History> list = null;
		try {
			list = historyDao.getHistoryForMovie(movie_id);
		} catch (Exception e) {
			throw new HistoryCRUDFailedException("历史记录获取失败！");
		}
		return list;
	}

	@Override
	public List<History> getHistoryForUserName(String username) {
		List<History> list = null;
		try {
			list = historyDao.getHistoryForUserName(username);
		} catch (Exception e) {
			throw new HistoryCRUDFailedException("历史记录获取失败！");
		}
		return list;
	}

	@Override
	public List<History> getHistoryForMovieName(String name) {
		List<History> list = null;
		try {
			list = historyDao.getHistoryForMovieName(name);
		} catch (Exception e) {
			throw new HistoryCRUDFailedException("历史记录获取失败！");
		}
		return list;
	}
	
	@Override
	public List<History> getHistoryPage(){
		List<History> list = null;
		try{
			list = historyDao.getHistoryPage();
		}catch (Exception e) {
			throw new HistoryCRUDFailedException("历史记录获取失败！");
		}
		return list;
	}
}
