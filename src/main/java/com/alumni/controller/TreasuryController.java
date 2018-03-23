package com.alumni.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alumni.bean.UserBean;
import com.alumni.entity.Payment;
import com.alumni.entity.PaymentAllocation;
import com.alumni.exception.InvalidSessionException;
import com.alumni.exception.NotAuthorizedException;
import com.alumni.service.FileService;
import com.alumni.service.PaymentService;
import com.alumni.util.CommonUtil;
import com.alumni.util.Constants;

@Controller
@Scope("request")
public class TreasuryController extends BaseController {
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	FileService fileService;

	private static final Logger logger = LoggerFactory.getLogger(TreasuryController.class);
	private String ROLE = "TREASURY";

	@RequestMapping(value = "/view-pending-payment", method = RequestMethod.GET)
	public ModelAndView viewPendingPayment(HttpServletRequest request, 			
			HttpServletResponse response) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "PaymentVerification");
			
			List<Payment> paymentList = paymentService.getPaymentByStatus(Constants.PaymentStatus.NEW.toString());
			modelAndView.addObject("paymentList", paymentList);
			
		} catch (InvalidSessionException e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("redirect:/login");
		} catch (NotAuthorizedException e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("NotAuthorized");
		} catch (Exception e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("Error500");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/view-payment-detail", method = RequestMethod.GET)
	public ModelAndView viewPaymentDetail(HttpServletRequest request, 			
			HttpServletResponse response) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "PaymentDetail");
			
			String paymentId = request.getParameter("id");
			logger.debug("paymentId:" + paymentId);
			Payment payment = paymentService.getPaymentById(Integer.parseInt(paymentId));
			modelAndView.addObject("payment", payment);
			
			if ((payment.getFileName() != null) && !"".equals(payment.getFileName())) {
				byte[] img = fileService.readFile(payment.getFileName());
				String enc = DatatypeConverter.printBase64Binary(img);
				modelAndView.addObject("base64Img", enc);
			}

		} catch (InvalidSessionException e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("redirect:/login");
		} catch (NotAuthorizedException e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("NotAuthorized");
		} catch (Exception e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("Error500");
		}

		return modelAndView;
	}


	@RequestMapping(value = "/verify-payment", method = RequestMethod.POST)
	public ModelAndView verifyPayment(HttpServletRequest request, 			
			HttpServletResponse response) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "PaymentDetail");
			
			String paymentId = request.getParameter("id");
			logger.debug("paymentId:" + paymentId);
			Payment payment = paymentService.getPaymentById(Integer.parseInt(paymentId));
			modelAndView.addObject("payment", payment);
			
			if ((payment.getFileName() != null) && !"".equals(payment.getFileName())) {
				byte[] img = fileService.readFile(payment.getFileName());
				String enc = DatatypeConverter.printBase64Binary(img);
				modelAndView.addObject("base64Img", enc);
			}

			String submit = request.getParameter("btnVerify");
			logger.debug("submit: " + submit);
			
			if ("Verify".equals(submit)) {
				paymentService.verifyPayment(payment, userBean.getUser());
				modelAndView.addObject("message", "Payment is verified successfully");
			}
			
		} catch (InvalidSessionException e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("redirect:/login");
		} catch (NotAuthorizedException e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("NotAuthorized");
		} catch (Exception e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("Error500");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/search-payment", method = RequestMethod.GET)
	public ModelAndView searchPayment(HttpServletRequest request, 			
			HttpServletResponse response) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "SearchPayment");
			
		} catch (InvalidSessionException e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("redirect:/login");
		} catch (NotAuthorizedException e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("NotAuthorized");
		} catch (Exception e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("Error500");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/search-payment-action", method = RequestMethod.POST)
	public ModelAndView searchPaymentAction(HttpServletRequest request, 			
			HttpServletResponse response) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "SearchPayment");
			
			String strStartDate = request.getParameter("startDate");
			String strEndDate = request.getParameter("endDate");
			String status = request.getParameter("status");
			
			modelAndView.addObject("strStartDate", strStartDate);
			modelAndView.addObject("strEndDate", strEndDate);
			modelAndView.addObject("status", status);
			
			Date startDate = null;
			Date endDate = null;
			
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			
			try {
				if ((strStartDate != null) && !"".equals(strStartDate))
					startDate = df.parse(strStartDate);

				if ((strEndDate != null) && !"".equals(strEndDate))
					endDate = df.parse(strEndDate);
			} catch (ParseException e) {
				CommonUtil.logInternalError(logger, e);
			}
			
			List<Payment> paymentList = paymentService.getPaymentByStatus(status, startDate, endDate);
			modelAndView.addObject("paymentList", paymentList);
			
		} catch (InvalidSessionException e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("redirect:/login");
		} catch (NotAuthorizedException e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("NotAuthorized");
		} catch (Exception e) {
			CommonUtil.logInternalError(logger, e);
			modelAndView = new ModelAndView("Error500");
		}

		return modelAndView;
	}


}
