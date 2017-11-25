package com.alumni.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alumni.dao.UserDao;
import com.alumni.entity.User;
import com.alumni.entity.UserRole;

@Repository
@Transactional
public class UserDaoImpl extends BaseDao implements UserDao {

	@SuppressWarnings("unchecked")
	public User getUserByEmail(String email) {
		Query query  = getCurrentSession().createQuery("FROM User WHERE email=:email");
		query.setParameter("email", email);
		List<User> lst = query.list();
		return lst.size() > 0 ? lst.get(0) : null;
	}

	public void updateUser(User user) {
		user.setUpdatedDate(new Date());
		getCurrentSession().update(user);
	}

	@SuppressWarnings("unchecked")
	public List<UserRole> getRoleListByUserId(int userId) {
		Criteria crit = getCurrentSession().createCriteria(UserRole.class);
		crit.add(Restrictions.eq("user.id", userId));
		return crit.list();
	}

}
