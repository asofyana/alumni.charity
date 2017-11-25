package com.alumni.service.util;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alumni.bean.UserBean;
import com.alumni.dao.UserDao;
import com.alumni.entity.User;
import com.alumni.entity.UserRole;
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

}
