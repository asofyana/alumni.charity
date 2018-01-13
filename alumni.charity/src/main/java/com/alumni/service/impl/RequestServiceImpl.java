package com.alumni.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alumni.bean.RequestPaymentBean;
import com.alumni.dao.PaymentDao;
import com.alumni.dao.RequestDao;
import com.alumni.entity.Payment;
import com.alumni.entity.PaymentRequest;
import com.alumni.entity.PaymentType;
import com.alumni.entity.User;
import com.alumni.service.RequestService;
import com.alumni.util.Constants;

@Service
public class RequestServiceImpl implements RequestService {

	@Autowired
	private RequestDao requestDao;

	@Autowired
	PaymentDao paymentDao;

	@Value("${max.approval.count}")
	private String maxApprovalCountStr;

	@Override
	public void saveRequest(RequestPaymentBean requestBean, User user) {
		
		PaymentRequest request = new PaymentRequest();
		request.setTitle(requestBean.getTitle());
		request.setType(requestBean.getType());
		request.setDescription(requestBean.getDescription());
		request.setAmount(Double.parseDouble(requestBean.getAmount()));
		request.setStatus(Constants.RequestPaymentStatus.PENDING_APPROVAL.toString());
		request.setCreatedBy(String.valueOf(user.getId()));
		request.setCreatedDate(new Date());
		
		requestDao.saveRequest(request);
	}

	@Override
	public void updateRequest(PaymentRequest request) {
		requestDao.updateRequest(request);
	}

	@Override
	public List<PaymentRequest> getUnapprovedRequestList(User user) {
		List<PaymentRequest> list = new ArrayList<PaymentRequest>();
		
		List<PaymentRequest> allUnapprovedList = requestDao.getRequestListByStatus(Constants.RequestPaymentStatus.PENDING_APPROVAL.toString());
		
		for (PaymentRequest request : allUnapprovedList) {
			if (!isAproverFound(String.valueOf(user.getId()), request.getApproverList())) {
				list.add(request);
			}
		}
		
		return list;
	}
	
	private boolean isAproverFound(String userId, String list) {
		boolean found = false;

		if ((list != null) && ("".equals(list))) {
			String[] ids = list.split(",");
			for (String s : ids) {
				if (s.equals(userId)) {
					found = true;
					break;
				}
			}
		}
		
		return found;
	}

	@Override
	public PaymentRequest getPaymentRequestById(int id) {
		return requestDao.getPaymentRequestById(id);
	}

	@Override
	public void approveRequest(User user, PaymentRequest paymentRequest) {
		
		int approvalCount = paymentRequest.getApprovalCount();
		approvalCount++;
		
		String approverList = paymentRequest.getApproverList();
		if ((approverList != null) && !"".equals(approverList)) {
			approverList += "," + user.getId();
		} else {
			approverList = String.valueOf(user.getId());
		}
		
		paymentRequest.setApprovalCount(approvalCount);
		paymentRequest.setApproverList(approverList);
		
		if (approvalCount == Integer.parseInt(maxApprovalCountStr)) {

			PaymentType pymType = new PaymentType();
			pymType.setPaymentType(Constants.PaymentType.CHARITY_PAYMENT.toString());

			// Create new payment
			Payment payment = new Payment();
			payment.setCreatedBy(String.valueOf(user.getId()));
			payment.setCreatedDate(new Date());
			payment.setAmount(paymentRequest.getAmount());
			payment.setPaymentType(pymType);
			payment.setStatus(Constants.PaymentStatus.NEW.toString());
			payment.setUser(user);
			paymentDao.savePayment(payment);
			
			paymentRequest.setStatus(Constants.RequestPaymentStatus.APPROVED.toString());
			
		}
		
		requestDao.updateRequest(paymentRequest);
		
		
	}

}
