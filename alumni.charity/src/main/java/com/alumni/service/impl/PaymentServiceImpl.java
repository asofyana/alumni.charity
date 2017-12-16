package com.alumni.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alumni.dao.PaymentDao;
import com.alumni.entity.Payment;
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
	
	@Override
	public void savePayment(User user, MultipartFile multipartFile) throws BusinessProcessException {
		
		try {
			
			String fileExt = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().length() - 3);
			Date now = new Date();
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			String fileName = df.format(now) + "_" + user.getId() + fileExt;
			
			fileService.uploadFile(fileName, multipartFile.getBytes());
			
			Payment payment = new Payment();
			payment.setCreatedBy(String.valueOf(user.getId()));
			payment.setCreatedDate(now);
			payment.setFileName(fileName);
			payment.setPaymentType(Constants.PAYMENT_TYPE_MEMBER_MONTHLY);
			payment.setStatus(Constants.PAYMENT_STATUS_NEW);
			payment.setUser(user);
			
			paymentDao.savePayment(payment);

		} catch (Exception e) {
			throw new BusinessProcessException();
		}
		
	}

}
