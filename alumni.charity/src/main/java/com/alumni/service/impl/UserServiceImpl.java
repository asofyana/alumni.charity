package com.alumni.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alumni.bean.RegistrationBean;
import com.alumni.bean.UserBean;
import com.alumni.dao.UserDao;
import com.alumni.entity.Role;
import com.alumni.entity.User;
import com.alumni.entity.UserRole;
import com.alumni.exception.BusinessProcessException;
import com.alumni.service.UserService;
import com.alumni.util.CommonUtil;
import com.alumni.util.Constants;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private static int SALT_LENGTH = 16;
	
	public UserBean login(String email, String password) {
		
		UserBean userBean = null;
		User user = userDao.getUserByEmail(email);
		if (user != null) {
			logger.debug("User found");
			
			if (!Constants.MemberStatus.ACTIVE.toString().equals(user.getStatus())) {
				return null;
			}
			
			if (user.getSalt() == null) {
				if ((password != null) && password.equals(user.getPassword())) {
					// Generate salt and encrypt password
					try {
						String salt = CommonUtil.generateUniqueString(SALT_LENGTH);
						String hashPassword = CommonUtil.hashPassword(password, salt);
						
						user.setSalt(salt);
						user.setPassword(hashPassword);
						user.setLastAccessDate(new Date());
						user.setUpdatedBy(Constants.SYSTEM);
						userDao.updateUser(user);
						
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
			} else {
				try {
					String hashPassword = CommonUtil.hashPassword(password, user.getSalt());
					if (hashPassword.equals(user.getPassword())) {
						user.setLastAccessDate(new Date());
						user.setUpdatedBy(Constants.SYSTEM);
						userDao.updateUser(user);
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
			
			userBean = new UserBean();
			userBean.setUser(user);

			// Get user role list
			List<UserRole> userRoleList = userDao.getRoleListByUserId(user.getId());
			userBean.setRoleList(userRoleList);
			
		} else {
			logger.debug("User not found");
		}
		
		return userBean;
	}

	@Override
	public int saveNewUser(RegistrationBean registrationBean)  throws BusinessProcessException {
		
		User user = userDao.getUserByEmail(registrationBean.getEmail());
		
		if (user != null) {
			throw new BusinessProcessException("Email Address already exists"); 
		}
		
		try {
			user = new User();
			user.setEmail(registrationBean.getEmail());
			user.setFullName(registrationBean.getFullName());
			user.setAddress(registrationBean.getAddress());
			user.setCity(registrationBean.getCity());
			user.setPostalCode(registrationBean.getPostalCode());
			user.setHomePhoneNumber(registrationBean.getHomePhoneNumber());
			user.setMobileNumber(registrationBean.getMobileNumber());
			user.setGrade1(registrationBean.getGrade1());
			user.setGrade2(registrationBean.getGrade2());
			user.setGrade3(registrationBean.getGrade3());
			user.setStatus(Constants.MemberStatus.PENDING.toString());
	
			String salt = CommonUtil.generateUniqueString(SALT_LENGTH);
			String hashPassword = CommonUtil.hashPassword(registrationBean.getPassword(), salt);
			
			user.setSalt(salt);
			user.setPassword(hashPassword);
			
			user.setCreatedDate(new Date());
			user.setCreatedBy("SYSTEM");
	
			userDao.addUser(user);
			
			user = userDao.getUserByEmail(user.getEmail());
			
			logger.info("User ID: " + user.getId());
			
			Role role = new Role();
			role.setId(2);
			
			UserRole userRole = new UserRole();
			userRole.setRole(role);
			userRole.setUser(user);
			
			userDao.addUserRole(userRole);
			
		} catch (Exception e) {
			CommonUtil.logInternalError(logger, e);
			throw new BusinessProcessException(e.getMessage());
		}
		
		return 1;
		
	}

}
