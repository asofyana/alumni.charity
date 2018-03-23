package com.alumni.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alumni.dao.RequestDao;
import com.alumni.entity.PaymentRequest;

@Repository
@Transactional
public class RequestDaoImpl extends BaseDao implements RequestDao {

	@Override
	public void saveRequest(PaymentRequest request) {
		saveObject(request);
	}

	@Override
	public void updateRequest(PaymentRequest request) {
		updateObject(request);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentRequest> getRequestListByStatus(String status) {
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("status", status);
		
		return (List<PaymentRequest>) (List<?>) getObjectsByCriteria(PaymentRequest.class, criteria);
	}

	@Override
	public PaymentRequest getPaymentRequestById(int id) {
		return (PaymentRequest) getObjectById(PaymentRequest.class, id);
	}

}
