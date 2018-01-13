package com.alumni.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.alumni.entity.MemberDonation;
import com.alumni.entity.Payment;
import com.alumni.entity.User;
import com.alumni.exception.BusinessProcessException;

public interface PaymentService {
	public void savePayment(User user, double amount, MultipartFile multipartFile) throws BusinessProcessException;
	public List<Payment> getPaymentByStatus(String status);
	public Payment getPaymentById(int id);
	public void verifyPayment(Payment payment, User user) throws Exception;
	public List<MemberDonation> getContributionListByUserId(int userId);
}
