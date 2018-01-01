package com.alumni.service;

import java.util.List;

import com.alumni.bean.RequestPaymentBean;
import com.alumni.entity.PaymentRequest;
import com.alumni.entity.User;

public interface RequestService {
	public void saveRequest(RequestPaymentBean requestBean, User user);
	public void updateRequest(PaymentRequest request);
	public List<PaymentRequest> getUnapprovedRequestList(User user);
	public PaymentRequest getPaymentRequestById(int id);
	public void approveRequest(User user, PaymentRequest paymentRequest);
}
