package com.alumni.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alumni.bean.UserBean;
import com.alumni.entity.User;
import com.alumni.exception.InvalidSessionException;
import com.alumni.exception.NotAuthorizedException;
import com.alumni.service.UserService;
import com.alumni.util.CommonUtil;
import com.alumni.util.Constants;

@Controller
@Scope("request")
public class AdminController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	private String ROLE = "ADMIN";
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/pending-member-list", method = RequestMethod.GET)
	public ModelAndView viewPendingMembertList(HttpServletRequest request, 			
			HttpServletResponse response) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "ViewPendingMemberList");
			
			List<User> userList = userService.getUserListByStatus(Constants.MemberStatus.PENDING.toString());
			modelAndView.addObject("userList", userList);
			
		} catch (InvalidSessionException e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("redirect:/login");
		} catch (NotAuthorizedException e) {
			modelAndView = new ModelAndView("NotAuthorized");
		} catch (Exception e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("Error500");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/pending-member-detail", method = RequestMethod.GET)
	public ModelAndView viewPendingMemberDetail(HttpServletRequest request, 			
			HttpServletResponse response) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "ViewPendingMemberDetail");

			String email = request.getParameter("email");

			User user = userService.getUserByEmail(email);
			modelAndView.addObject("user", user);
			
		} catch (InvalidSessionException e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("redirect:/login");
		} catch (NotAuthorizedException e) {
			modelAndView = new ModelAndView("NotAuthorized");
		} catch (Exception e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("Error500");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/member-approval-action", method = RequestMethod.POST)
	public ModelAndView approvePendingMember(HttpServletRequest request, 			
			HttpServletResponse response) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "ViewPendingMemberDetail");

			String email = request.getParameter("email");

			User user = userService.getUserByEmail(email);
			modelAndView.addObject("user", user);
			
			if ("Approve".equals(request.getParameter("btnApprove"))) {
				user.setStatus(Constants.MemberStatus.ACTIVE.toString());
				userService.updateUser(user);
				modelAndView.addObject("message", "The request is approved");
			}

		} catch (InvalidSessionException e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("redirect:/login");
		} catch (NotAuthorizedException e) {
			modelAndView = new ModelAndView("NotAuthorized");
		} catch (Exception e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("Error500");
		}

		return modelAndView;
	}
	
}
