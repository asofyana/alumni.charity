package com.alumni.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alumni.dao.PaymentDao;
import com.alumni.entity.Payment;

@Repository
@Transactional
public class PaymentDaoImpl extends BaseDao implements PaymentDao {

	@Override
	public void savePayment(Payment payment) {
		saveObject(payment);
	}

}
