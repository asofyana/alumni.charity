package com.alumni.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alumni.dao.UserDao;
import com.alumni.entity.Role;
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
		updateObject(user);
	}
	
	public void addUser(User user) {
		user.setCity(user.getCity().toUpperCase());
		saveObject(user);
	}
	
	public void addUserRole(UserRole userRole) {
		saveObject(userRole);
	}

	@SuppressWarnings("unchecked")
	public List<UserRole> getRoleListByUserId(int userId) {
		Criteria crit = getCurrentSession().createCriteria(UserRole.class);
		crit.add(Restrictions.eq("user.id", userId));
		return crit.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getUserListByStatus(String status) {
		Criteria crit = getCurrentSession().createCriteria(User.class);
		crit.add(Restrictions.eq("status", status));
		return crit.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> searchUser(User user) {
		Criteria crit = getCurrentSession().createCriteria(User.class);
		if ((user.getFullName() != null) && (!"".equals(user.getFullName()))) {
			crit.add(Restrictions.ilike("fullName", user.getFullName(), MatchMode.ANYWHERE));
		}
		if ((user.getCity() != null) && (!"".equals(user.getCity()))) {
			crit.add(Restrictions.ilike("city", user.getCity(), MatchMode.ANYWHERE));
		}
		if ((user.getGrade1() != null) && (!"".equals(user.getGrade1()))) {
			crit.add(Restrictions.ilike("grade1", user.getGrade1(), MatchMode.ANYWHERE));
		}
		if ((user.getGrade2() != null) && (!"".equals(user.getGrade2()))) {
			crit.add(Restrictions.ilike("grade2", user.getGrade2(), MatchMode.ANYWHERE));
		}
		if ((user.getGrade3() != null) && (!"".equals(user.getGrade3()))) {
			crit.add(Restrictions.ilike("grade3", user.getGrade3(), MatchMode.ANYWHERE));
		}
		return crit.list();
	}

	@Override
	public void deleteUserRole(UserRole userRole) {
		deleteObject(userRole);
	}

	@Override
	public Role getRoleByCode(String code) {
		Query query  = getCurrentSession().createQuery("FROM Role WHERE code=:code");
		query.setParameter("code", code);
		List<Role> lst = query.list();
		return lst.size() > 0 ? lst.get(0) : null;
	}

}
