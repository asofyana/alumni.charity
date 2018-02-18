package com.alumni.dao.impl;

import java.util.Calendar;
import java.util.Date;
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
import com.alumni.entity.PaymentAllocation;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> getPaymentByStatus(String status, Date startDate, Date endDate) {
		
		
		Criteria crit = getCurrentSession().createCriteria(Payment.class);
		
		if ((status != null) && !"".equals(status))
			crit.add(Restrictions.eq("status", status));
		
		if (startDate != null)
			crit.add(Restrictions.ge("createdDate", startDate));
		
		if (endDate != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(endDate);
			cal.add(Calendar.DATE, 1);
			crit.add(Restrictions.lt("createdDate", cal.getTime()));
		}
		
		return (List<Payment>) (List<?>) crit.list();
	}

	@Override
	public Payment getPaymentById(int id) {
		return (Payment) getObjectById(Payment.class, id);
	}

	@SuppressWarnings("rawtypes")
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

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberContribution> getContributionListByUserId(int userId) {

		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("user.id", userId);
		
		return (List<MemberContribution>) (List<?>) getObjectsByCriteria(MemberContribution.class, criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentAllocation> getPaymentAllocationList(int paymentId) {
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("payment.id", paymentId);
		
		return (List<PaymentAllocation>) (List<?>) getObjectsByCriteria(PaymentAllocation.class, criteria);
	}

	@Override
	public void savePaymentAllocation(PaymentAllocation paymentAllocation) {
		saveObject(paymentAllocation);
	}

}
