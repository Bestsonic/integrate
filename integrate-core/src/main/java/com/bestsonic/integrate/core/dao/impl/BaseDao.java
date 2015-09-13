package com.bestsonic.integrate.core.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bestsonic.integrate.core.dao.IBaseDao;
import com.bestsonic.integrate.core.utils.SystemContext;

import static org.junit.Assert.*;

import java.lang.reflect.*;

@SuppressWarnings("unchecked")
public abstract class BaseDao<T> implements IBaseDao<T> {
	
	@Autowired
	private SessionFactory sessionFactory;

	private Class<T> clazz;
	
	public BaseDao() {
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType genericType = (ParameterizedType)(type);
		clazz = (Class<T>) genericType.getActualTypeArguments()[0];
		assertNotNull(clazz);
	}
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public void add(T t) {
		getSession().save(t);
	}

	public void update(T t) {
		getSession().update(t);
		getSession().flush();
	}

	public void delete(String id) {
		T t = load(id);
		getSession().delete(t);
		getSession().flush();
	}

	public T load(String id) {
		return (T) getSession().load(clazz, id);
	}

	
	public List<T> load() {
		return this.getPage("from "+clazz.getName().substring(clazz.getName().lastIndexOf(".")+1), null);
	}

	protected List<T> getPage(String hql, Map<String, String> params) {
		int pageSize = SystemContext.getPageSize();
		int pageOffset = SystemContext.getPageOffset();
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		if(sort != null)
			hql += " order by " + sort;
		if(order != null)
			hql += " " + order;
		Query query = getSession().createQuery(hql);
		if(params != null){
			for(Map.Entry<String, String> entry : params.entrySet()){
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		if(pageSize == 0 && pageOffset == 0)
			return query.list();
		return query.setFirstResult(pageOffset)
					.setMaxResults(pageSize)
					.list();
	}
}
