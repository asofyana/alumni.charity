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

import com.alumni.bean.UploadReceiptBean;
import com.alumni.bean.UserBean;
import com.alumni.exception.BusinessProcessException;
import com.alumni.exception.InvalidSessionException;
import com.alumni.exception.NotAuthorizedException;
import com.alumni.service.PaymentService;
import com.alumni.util.CommonUtil;
import com.alumni.util.Constants;

@Controller
@Scope("request")
public class MemberController extends BaseController {
	
	@Autowired
	PaymentService paymentService;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private String ROLE = "MEMBER";

	@RequestMapping(value = "/upload-receipt", method = RequestMethod.GET)
	public ModelAndView uploadReceipt(HttpServletRequest request, 			
			HttpServletResponse response,
			@ModelAttribute("uploadReceiptBean") UploadReceiptBean uploadReceiptBean,
			BindingResult result) {
		
		logger.info("upload-receipt - start");

		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "UploadReceipt");
		} catch (InvalidSessionException e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("redirect:/login");
		} catch (NotAuthorizedException e) {
			modelAndView = new ModelAndView("redirect:/login");
		} catch (Exception e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("redirect:/login");
		}

		logger.info("upload-receipt - end");

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
			
			System.out.println("amount: " + uploadReceiptBean.getAmount());
			System.out.println("file: " + uploadReceiptBean.getMultipartFile().getOriginalFilename());
			
			paymentService.savePayment(userBean.getUser(), uploadReceiptBean.getMultipartFile());
			
			modelAndView.addObject("message", "Your file is uploaded successfully");
			
		} catch (InvalidSessionException e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("redirect:/login");
		} catch (NotAuthorizedException e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("redirect:/login");
		} catch (BusinessProcessException e) {
			CommonUtil.logInternalError(logger, e);
		}
	
		return modelAndView;
	}
	
}
