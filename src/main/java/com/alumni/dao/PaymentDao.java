package com.alumni.dao;

import java.util.Date;
import java.util.List;

import com.alumni.entity.MemberContribution;
import com.alumni.entity.Payment;
import com.alumni.entity.PaymentAllocation;

public interface PaymentDao {
	public void savePayment(Payment payment);
	public void updatePayment(Payment payment);
	public List<Payment> getPaymentByStatus(String status);
	public List<Payment> getPaymentByStatus(String status, Date startDate, Date endDate);
	public Payment getPaymentById(int id);
	public MemberContribution getLastContributionMonth(int userId);
	public void saveMemberContribution(MemberContribution memberContribution);
	public void updateMemberContribution(MemberContribution memberContribution);
	public List<MemberContribution> getContributionListByUserId(int userId);
	public List<PaymentAllocation> getPaymentAllocationList(int paymentId);
	public void savePaymentAllocation(PaymentAllocation paymentAllocation);
	public double getTotalAllocationAmount(String allocationType);
	public List<Payment> getPaymentByUser(int userId);
}
