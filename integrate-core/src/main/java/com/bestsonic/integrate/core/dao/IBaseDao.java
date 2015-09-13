package com.bestsonic.integrate.core.dao;

import java.util.List;

public interface IBaseDao<T> {

	void add(T t);
	
	void update(T t);
	
	void delete(String id);
	
	T load(String id);
	
	List<T> load();
	
}
