package com.alumni.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alumni.dao.PaymentDao;
import com.alumni.entity.MemberContribution;
import com.alumni.entity.Payment;

@Repository
@Transactional
public class PaymentDaoImpl extends BaseDao implements PaymentDao {

	@Override
	public void savePayment(Payment payment) {
		saveObject(payment);
	}

	@Override
	public void updatePayment(Payment payment) {
		updateObject(payment);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> getPaymentByStatus(String status) {
		
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("status", status);
		
		return (List<Payment>) (List<?>) getObjectsByCriteria(Payment.class, criteria);
	}

	@Override
	public Payment getPaymentById(int id) {
		return (Payment) getObjectById(Payment.class, id);
	}

	@Override
	public MemberContribution getLastContributionMonth(int userId) {
		//Query query = getCurrentSession().createQuery("select max(month) from MemberContribution where user.id=:userId");
		//query.setParameter("userId", userId);
		//return (Date) query.uniqueResult();
		
		DetachedCriteria maxQuery = DetachedCriteria.forClass(MemberContribution.class);
		maxQuery.add(Restrictions.eq("user.id", userId));
		maxQuery.setProjection( Projections.max("month"));
		
		Criteria query = getCurrentSession().createCriteria(MemberContribution.class);
		query.add(Property.forName("month").eq(maxQuery));
		
		List lst = query.list();
		
		if ((lst != null) && (lst.size()>0)) {
			return (MemberContribution) lst.get(0);
		}
		
		return null;
		
	}

	@Override
	public void saveMemberContribution(MemberContribution memberContribution) {
		saveObject(memberContribution);
	}

	@Override
	public void updateMemberContribution(MemberContribution memberContribution) {
		updateObject(memberContribution);
	}


}
