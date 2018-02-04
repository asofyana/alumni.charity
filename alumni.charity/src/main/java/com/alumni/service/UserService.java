package com.alumni.service;

import java.util.List;

import com.alumni.bean.RegistrationBean;
import com.alumni.bean.UserBean;
import com.alumni.entity.User;
import com.alumni.exception.BusinessProcessException;

public interface UserService {
	public UserBean login(String userName, String password);
	public int saveNewUser(RegistrationBean registrationBean) throws BusinessProcessException;
	public List<User> getUserListByStatus(String status);
	public User getUserByEmail(String email);
	public void updateUser(User user);
}
