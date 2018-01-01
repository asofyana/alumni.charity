package com.alumni.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alumni.bean.LoginForm;
import com.alumni.bean.UserBean;
import com.alumni.entity.UserRole;
import com.alumni.service.UserService;
import com.alumni.util.Constants;

@Controller
@Scope("request")
public class LoginController {

	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, 			
			HttpServletResponse response,
			@ModelAttribute("loginForm") LoginForm loginForm,
			BindingResult result) {

		ModelAndView modelAndView = new ModelAndView("Login");
	
		return modelAndView;
	}

	@RequestMapping(value = "/login-action", method = RequestMethod.POST)
	public ModelAndView loginAction(HttpServletRequest request, 			
			HttpServletResponse response,
			@ModelAttribute("loginForm") LoginForm loginForm,
			BindingResult result) {

		ModelAndView modelAndView = new ModelAndView();
		
		request.getSession().invalidate();
		
		UserBean userBean = userService.login(loginForm.getEmail(), loginForm.getPassword());
		if (userBean != null) {
			request.getSession().setAttribute(Constants.SESS_USER, userBean);
			modelAndView.setViewName("Home");
			modelAndView.addObject("user", userBean.getUser());
			modelAndView.addObject("pageHeader", "Dashboard");
			modelAndView.addObject("pageDescription", "");
			for (UserRole userRole : userBean.getRoleList()) {
				modelAndView.addObject(userRole.getRole().getCode(), "Y");
			}
		} else {
			modelAndView.setViewName("Login");
			modelAndView.addObject("message", "invalid user id or password");
		}
	
		return modelAndView;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, 			
			HttpServletResponse response,
			@ModelAttribute("loginForm") LoginForm loginForm,
			BindingResult result) {

		request.getSession().invalidate();
		ModelAndView modelAndView = new ModelAndView("Login");
		return modelAndView;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request, 			
			HttpServletResponse response,
			@ModelAttribute("loginForm") LoginForm loginForm,
			BindingResult result) {

		ModelAndView modelAndView = new ModelAndView("Home");
		return modelAndView;
	}

}
