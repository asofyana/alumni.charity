package com.alumni.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.alumni.entity.UserRole;
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
			modelAndView = createModelAndViewInstance(userBean, ROLE, "ViewMemberDetail");

			String email = request.getParameter("email");

			User user = userService.getUserByEmail(email);
			modelAndView.addObject("user", user);
			modelAndView.addObject("approveButton", "true");
			
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

	@RequestMapping(value = "/member-role-update", method = RequestMethod.GET)
	public ModelAndView viewMemberRoleUpdate(HttpServletRequest request, 			
			HttpServletResponse response) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "MemberRoleUpdate");

			String email = request.getParameter("email");

			User user = userService.getUserByEmail(email);
			
			List<UserRole> roleList = userService.getRoleListByUserId(user.getId());
			for (UserRole userRole : roleList) {
				modelAndView.addObject("ROLE_" + userRole.getRole().getCode(), "checked");
			} 
			
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

	@RequestMapping(value = "/member-role-update-action", method = RequestMethod.POST)
	public ModelAndView memberRoleUpdateAction(HttpServletRequest request, 			
			HttpServletResponse response) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "MemberRoleUpdate");

			String email = request.getParameter("email");
			String roleAdmin = request.getParameter("chkRoleAdmin");
			String roleTreasury = request.getParameter("chkRoleTreasury");
			String roleApprover = request.getParameter("chkRoleApprover");
			
			if ((roleAdmin != null) && "on".equals(roleAdmin)) {
				modelAndView.addObject("ROLE_ADMIN", "checked");
			}
			if ((roleTreasury != null) && "on".equals(roleTreasury)) {
				modelAndView.addObject("ROLE_TREASURY", "checked");
			}
			if ((roleApprover != null) && "on".equals(roleApprover)) {
				modelAndView.addObject("ROLE_APPROVER", "checked");
			}
			
			Map<String, String> roleMap = new HashMap<String, String>();
			roleMap.put("ADMIN", roleAdmin);
			roleMap.put("TREASURY", roleTreasury);
			roleMap.put("APPROVER", roleApprover);
			
			User user = userService.getUserByEmail(email);
			
			userService.updateUserRole(roleMap, user);
			
			modelAndView.addObject("user", user);
			modelAndView.addObject("message", "Role is updated successfuly");
			
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
