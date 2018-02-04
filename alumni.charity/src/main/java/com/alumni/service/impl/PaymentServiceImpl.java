package com.alumni.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alumni.dao.PaymentDao;
import com.alumni.entity.MemberContribution;
import com.alumni.entity.Payment;
import com.alumni.entity.PaymentType;
import com.alumni.entity.User;
import com.alumni.exception.BusinessProcessException;
import com.alumni.service.FileService;
import com.alumni.service.PaymentService;
import com.alumni.util.Constants;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	FileService fileService;
	
	@Autowired
	PaymentDao paymentDao;
	
	@Value("${monthly.contribution}")
	private String monthlyContributionStr;

	@Value("${initial.month}")
	private String initialMonth;

	@Override
	public void savePayment(User user, double amount, MultipartFile multipartFile) throws BusinessProcessException {
		
		try {
			
			String fileExt = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().length() - 3);
			Date now = new Date();
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			String fileName = df.format(now) + "_" + user.getId() + fileExt;
			
			fileService.uploadFile(fileName, multipartFile.getBytes());
			
			PaymentType pymType = new PaymentType();
			pymType.setPaymentType(Constants.PaymentType.MEMBER_MONTHLY.toString());
			
			Payment payment = new Payment();
			payment.setCreatedBy(String.valueOf(user.getId()));
			payment.setCreatedDate(now);
			payment.setFileName(fileName);
			payment.setAmount(amount);
			payment.setPaymentType(pymType);
			payment.setStatus(Constants.PaymentStatus.NEW.toString());
			payment.setUser(user);
			
			paymentDao.savePayment(payment);

		} catch (Exception e) {
			throw new BusinessProcessException(e.getMessage());
		}
		
	}

	@Override
	public List<Payment> getPaymentByStatus(String status) {
		return paymentDao.getPaymentByStatus(status);
	}

	@Override
	public Payment getPaymentById(int id) {
		return paymentDao.getPaymentById(id);
	}

	@Override
	public void verifyPayment(Payment payment, User user) throws Exception {
		payment.setStatus(Constants.PaymentStatus.VERIFIED.toString());
		paymentDao.updatePayment(payment);
		if (Constants.PaymentType.MEMBER_MONTHLY.toString().equals(payment.getPaymentType())) {
			double monthlyContribution = Double.parseDouble(monthlyContributionStr);
			MemberContribution lastContr = paymentDao.getLastContributionMonth(payment.getUser().getId());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date startPayment = df.parse(initialMonth);
			double amount = payment.getAmount();
			if (lastContr != null) {
				double tmpPaid = monthlyContribution - lastContr.getAmount();
				if (amount < tmpPaid) {
					lastContr.setAmount(lastContr.getAmount() + amount);
					amount = 0;
				} else {
					lastContr.setAmount(monthlyContribution);
					amount -= tmpPaid;
					Calendar cal = Calendar.getInstance();
					cal.setTime(lastContr.getMonth());
					cal.add(Calendar.MONTH, 1);
					startPayment = cal.getTime();
				}
				lastContr.setUpdatedDate(new Date());
				lastContr.setUpdatedBy(String.valueOf(user.getId()));
				paymentDao.updateMemberContribution(lastContr);
			}
			
			while (amount > 0) {
				MemberContribution contr = new MemberContribution();
				contr.setMonth(startPayment);
				contr.setCreatedDate(new Date());
				contr.setCreatedBy(String.valueOf(user.getId()));
				contr.setPayment(payment);
				contr.setUser(payment.getUser());
				
				if (amount > monthlyContribution) {
					amount -= monthlyContribution;
					contr.setAmount(monthlyContribution);
				} else {
					contr.setAmount(amount);
					amount = 0;
				}
				
				paymentDao.saveMemberContribution(contr);
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(startPayment);
				cal.add(Calendar.MONTH, 1);
				startPayment = cal.getTime();
				
			}
		}
	}
	
	@Override
	public List<MemberContribution> getContributionListByUserId(int userId) {
		return paymentDao.getContributionListByUserId(userId);
	}
}
