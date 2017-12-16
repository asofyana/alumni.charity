package com.alumni.service;

import org.springframework.web.multipart.MultipartFile;

import com.alumni.entity.User;
import com.alumni.exception.BusinessProcessException;

public interface PaymentService {
	public void savePayment(User user, MultipartFile multipartFile) throws BusinessProcessException;
}
