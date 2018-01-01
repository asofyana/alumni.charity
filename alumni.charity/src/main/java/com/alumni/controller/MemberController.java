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

import com.alumni.bean.RequestPaymentBean;
import com.alumni.bean.UploadReceiptBean;
import com.alumni.bean.UserBean;
import com.alumni.entity.MemberContribution;
import com.alumni.entity.PaymentRequest;
import com.alumni.exception.BusinessProcessException;
import com.alumni.exception.InvalidSessionException;
import com.alumni.exception.NotAuthorizedException;
import com.alumni.service.PaymentService;
import com.alumni.service.RequestService;
import com.alumni.util.CommonUtil;
import com.alumni.util.Constants;

@Controller
@Scope("request")
public class MemberController extends BaseController {
	
	@Autowired
	PaymentService paymentService;

	@Autowired
	RequestService requestService;
	
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
			
			paymentService.savePayment(userBean.getUser(), Double.parseDouble(uploadReceiptBean.getAmount()), uploadReceiptBean.getMultipartFile());
			
			modelAndView.addObject("message", "Your file is uploaded successfully");
			
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
	public ModelAndView viewContribution(HttpServletRequest request, 			
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

}
