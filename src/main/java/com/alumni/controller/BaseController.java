package com.alumni.controller;

import org.springframework.web.servlet.ModelAndView;

import com.alumni.bean.UserBean;
import com.alumni.entity.UserRole;
import com.alumni.exception.InvalidSessionException;
import com.alumni.exception.NotAuthorizedException;

public class BaseController {
	
	protected ModelAndView createModelAndViewInstance(UserBean userBean, String role, String pageName) 
			throws InvalidSessionException, NotAuthorizedException {
		
		if (userBean == null) {
			throw new InvalidSessionException();
		}
		
		boolean roleFound = false;
		for (UserRole userRole : userBean.getRoleList()) {
			if (role.equals(userRole.getRole().getCode())) {
				roleFound = true;
				break;
			}
		}
		if (!roleFound) {
			throw new NotAuthorizedException();
		}
		
		ModelAndView mv = new ModelAndView(pageName);
		for (UserRole userRole : userBean.getRoleList()) {
			mv.addObject(userRole.getRole().getCode(), "Y");
		}		
		
		return mv;
	}

}
