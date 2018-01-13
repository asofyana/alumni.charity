package com.alumni.service;

import com.alumni.bean.RegistrationBean;
import com.alumni.bean.UserBean;
import com.alumni.exception.BusinessProcessException;

public interface UserService {
	public UserBean login(String userName, String password);
	public int saveNewUser(RegistrationBean registrationBean) throws BusinessProcessException;
}
