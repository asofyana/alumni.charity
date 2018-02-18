package com.alumni.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.alumni.entity.MemberContribution;
import com.alumni.entity.Payment;
import com.alumni.entity.User;
import com.alumni.exception.BusinessProcessException;

public interface PaymentService {
	public void savePayment(User user, double committedAmount, double uncommittedAmount, MultipartFile multipartFile) throws BusinessProcessException;
	public List<Payment> getPaymentByStatus(String status);
	public List<Payment> getPaymentByStatus(String status, Date startDate, Date endDate);
	public Payment getPaymentById(int id);
	public void verifyPayment(Payment payment, User user) throws Exception;
	public List<MemberContribution> getContributionListByUserId(int userId);
}
