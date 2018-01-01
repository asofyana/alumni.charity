package com.alumni.dao;

import java.util.List;

import com.alumni.entity.PaymentRequest;

public interface RequestDao {
	public void saveRequest(PaymentRequest request);
	public void updateRequest(PaymentRequest request);
	public List<PaymentRequest> getRequestListByStatus(String status);
	PaymentRequest getPaymentRequestById(int id);
}
