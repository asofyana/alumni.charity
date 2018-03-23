package com.alumni.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession(){
		Session session = null;
		
		if(sessionFactory != null){
			session = sessionFactory.getCurrentSession();
		}
		
		return session;
	}
	
	public boolean saveObject(Object object){
		boolean status  = true;
		try{
			getCurrentSession().save(object);
		}catch (Exception e){
			e.printStackTrace();
			status = false;
		}
		return status;
	}
	
	public boolean updateObject(Object object){
		boolean status  = true;
		try{
			getCurrentSession().update(object);
		}catch (Exception e){
			e.printStackTrace();
			status = false;
		}
		return status;
	}
	
	public boolean deleteObject(Object object){
		boolean status  = true;
		try{
			getCurrentSession().delete(object);
		}catch (Exception e){
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	public <T> Object getObjectById(Class<T> className, int id) {
		return getCurrentSession().get(className, id);
	}

	@SuppressWarnings({ "unchecked" })
	public <T> List<Object> getObjectsByCriteria(Class<T> className,
			Map<String, Object> criteria) {
		
		Criteria crit = getCriteria(className, criteria);
		return crit.list();
	}

	@SuppressWarnings({ "rawtypes" })
	protected <T> Criteria getCriteria(Class<T> className,
			Map<String, Object> criteria){
		
		Session session = getCurrentSession();
		Criteria crit = session.createCriteria(className);
		Set<String> keySet = criteria.keySet();
		
		Iterator it = keySet.iterator();
		
		while(it.hasNext()){
			String key = (String)it.next();
			crit.add(Restrictions.eq(key, criteria.get(key)));
		}
		
		return crit;
	}

}
