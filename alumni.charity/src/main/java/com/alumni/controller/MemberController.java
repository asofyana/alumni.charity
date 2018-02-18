package com.alumni.controller;

import java.util.List;

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

import com.alumni.bean.RegistrationBean;
import com.alumni.bean.RequestPaymentBean;
import com.alumni.bean.UploadReceiptBean;
import com.alumni.bean.UserBean;
import com.alumni.entity.MemberContribution;
import com.alumni.entity.User;
import com.alumni.exception.BusinessProcessException;
import com.alumni.exception.InvalidSessionException;
import com.alumni.exception.NotAuthorizedException;
import com.alumni.service.PaymentService;
import com.alumni.service.RequestService;
import com.alumni.service.UserService;
import com.alumni.util.CommonUtil;
import com.alumni.util.Constants;

@Controller
@Scope("request")
public class MemberController extends BaseController {
	
	@Autowired
	PaymentService paymentService;

	@Autowired
	RequestService requestService;
	
	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private String ROLE = "MEMBER";

	@RequestMapping(value = "/upload-receipt", method = RequestMethod.GET)
	public ModelAndView uploadReceipt(HttpServletRequest request, 			
			HttpServletResponse response,
			@ModelAttribute("uploadReceiptBean") UploadReceiptBean uploadReceiptBean,
			BindingResult result) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "UploadReceipt");
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

	@RequestMapping(value = "/upload-receipt-action", method = RequestMethod.POST)
	public ModelAndView uploadReceiptAction(HttpServletRequest request, 			
			HttpServletResponse response,
			@ModelAttribute("uploadReceiptBean") UploadReceiptBean uploadReceiptBean,
			BindingResult result) {

		ModelAndView modelAndView = null;
		
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "UploadReceipt");
			double totalAmount = 0;
			double committedAmount = 0;
			double uncommittedAmount = 0;
			
			try {
				totalAmount = Double.parseDouble(uploadReceiptBean.getTotalAmount());
			} catch (Exception e) {
			}

			try {
				committedAmount = Double.parseDouble(uploadReceiptBean.getCommittedAmount());
			} catch (Exception e) {
			}

			try {
				uncommittedAmount = Double.parseDouble(uploadReceiptBean.getUncommittedAmount());
			} catch (Exception e) {
			}
			
			logger.debug("totalAmount: " + totalAmount);
			logger.debug("committedAmount: " + committedAmount);
			logger.debug("uncommittedAmount: " + uncommittedAmount);
			
			boolean isValid = true;
			String message = "";

			if (totalAmount == 0) {
				isValid = false;
				message = "Please fill amount";
			}
			
			if (isValid && (totalAmount != (committedAmount + uncommittedAmount))) {
				isValid = false;
				message = "Total amount must be equal to committed + uncommitted amount";
			}
			
			if (isValid && ((uploadReceiptBean.getMultipartFile() == null) || (uploadReceiptBean.getMultipartFile().getSize() == 0))) {
				isValid = false;
				message = "Please upload receipt";
			}

			if (isValid) {
				paymentService.savePayment(userBean.getUser(), committedAmount, uncommittedAmount, uploadReceiptBean.getMultipartFile());
				message = "Your file is uploaded successfully";
			}
			
			modelAndView.addObject("message", message);
			
		} catch (InvalidSessionException e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("redirect:/login");
		} catch (NotAuthorizedException e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("NotAuthorized");
		} catch (BusinessProcessException e) {
			CommonUtil.logInternalError(logger, e);
		} catch (Exception e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("Error500");
		}
	
		return modelAndView;
	}

	@RequestMapping(value = "/view-contribution", method = RequestMethod.GET)
	public ModelAndView viewDonation(HttpServletRequest request, 			
			HttpServletResponse response,
			@ModelAttribute("uploadReceiptBean") UploadReceiptBean uploadReceiptBean,
			BindingResult result) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "ViewContribution");
			
			List<MemberContribution> contributionList = paymentService.getContributionListByUserId(userBean.getUser().getId());
			modelAndView.addObject("contributionList", contributionList);
			
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

	@RequestMapping(value = "/request-payment", method = RequestMethod.GET)
	public ModelAndView requestPayment(HttpServletRequest request, 			
			HttpServletResponse response,
			@ModelAttribute("requestPaymentBean") RequestPaymentBean requestPaymentBean,
			BindingResult result) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "RequestPayment");
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

	@RequestMapping(value = "/request-payment-action", method = RequestMethod.POST)
	public ModelAndView requestPaymentAction(HttpServletRequest request, 			
			HttpServletResponse response,
			@ModelAttribute("requestPaymentBean") RequestPaymentBean requestPaymentBean,
			BindingResult result) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "RequestPayment");
			
			requestService.saveRequest(requestPaymentBean, userBean.getUser());
			
			modelAndView.addObject("message", "Your request is saved successfully");
			
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

	@RequestMapping(value = "/member-registration", method = RequestMethod.GET)
	public ModelAndView memberRegistration(HttpServletRequest request, 			
			HttpServletResponse response,
			@ModelAttribute("registrationBean") RegistrationBean registrationBean,
			BindingResult result) {
		
		ModelAndView modelAndView = new ModelAndView("MemberRegistration");
		
		return modelAndView;

	}
	
	@RequestMapping(value = "/member-registration-action", method = RequestMethod.POST)
	public ModelAndView memberRegistrationAction(HttpServletRequest request, 			
			HttpServletResponse response,
			@ModelAttribute("registrationBean") RegistrationBean registrationBean,
			BindingResult result) {

		ModelAndView modelAndView = new ModelAndView("MemberRegistration");
		
		try {
			userService.saveNewUser(registrationBean);
			modelAndView.addObject("message", "Your data is saved successfuly. Please login after your registration is approved");
		} catch (BusinessProcessException e) {
			modelAndView.addObject("message", e.getMessage());
		}
		
		return modelAndView;

	}
	
	@RequestMapping(value = "/member-search", method = RequestMethod.GET)
	public ModelAndView searchMember(HttpServletRequest request, 			
			HttpServletResponse response,
			@ModelAttribute("user") User user,
			BindingResult result) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "MemberSearch");
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
	
	@RequestMapping(value = "/member-search-action", method = RequestMethod.POST)
	public ModelAndView searchMemberAction(HttpServletRequest request, 			
			HttpServletResponse response,
			@ModelAttribute("user") User user,
			BindingResult result) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "MemberSearch");
			
			logger.debug("getFullName:" + user.getFullName());
			logger.debug("getCity:" + user.getCity());
			logger.debug("getGrade1:" + user.getGrade1());
			logger.debug("getGrade2:" + user.getGrade2());
			logger.debug("getGrade3:" + user.getGrade3());
			
			List<User> userList = userService.searchUser(user);
			modelAndView.addObject("memberList", userList);
			
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

	@RequestMapping(value = "/member-detail", method = RequestMethod.GET)
	public ModelAndView viewPendingMemberDetail(HttpServletRequest request, 			
			HttpServletResponse response) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "ViewMemberDetail");

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

}
