package com.alumni.dao;

import java.util.List;

import com.alumni.entity.MemberContribution;
import com.alumni.entity.Payment;

public interface PaymentDao {
	public void savePayment(Payment payment);
	public void updatePayment(Payment payment);
	public List<Payment> getPaymentByStatus(String status);
	public Payment getPaymentById(int id);
	public MemberContribution getLastContributionMonth(int userId);
	public void saveMemberContribution(MemberContribution memberContribution);
	public void updateMemberContribution(MemberContribution memberContribution);
}
