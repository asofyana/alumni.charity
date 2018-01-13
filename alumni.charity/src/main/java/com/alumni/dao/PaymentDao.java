package com.alumni.dao;

import java.util.List;

import com.alumni.entity.MemberDonation;
import com.alumni.entity.Payment;

public interface PaymentDao {
	public void savePayment(Payment payment);
	public void updatePayment(Payment payment);
	public List<Payment> getPaymentByStatus(String status);
	public Payment getPaymentById(int id);
	public MemberDonation getLastContributionMonth(int userId);
	public void saveMemberContribution(MemberDonation memberContribution);
	public void updateMemberContribution(MemberDonation memberContribution);
	public List<MemberDonation> getContributionListByUserId(int userId);
}
