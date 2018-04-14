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
import com.alumni.entity.PaymentRequest;
import com.alumni.exception.InvalidSessionException;
import com.alumni.exception.NotAuthorizedException;
import com.alumni.service.RequestService;
import com.alumni.util.CommonUtil;
import com.alumni.util.Constants;

@Controller
@Scope("request")
public class ApproverController extends BaseController {

	@Autowired
	RequestService requestService;

	private static final Logger logger = LoggerFactory.getLogger(ApproverController.class);
	private String ROLE = "APPROVER";
	
	@RequestMapping(value = "/view-request-list", method = RequestMethod.GET)
	public ModelAndView viewRequestList(HttpServletRequest request, 			
			HttpServletResponse response) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "ViewRequestList");
			
			List<PaymentRequest> requestList = requestService.getUnapprovedRequestList(userBean.getUser());
			modelAndView.addObject("requestList", requestList);
			
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

	@RequestMapping(value = "/view-request-detail", method = RequestMethod.GET)
	public ModelAndView viewRequestDetail(HttpServletRequest request, 			
			HttpServletResponse response) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "RequestApprovalDetail");
			
			String reqId = request.getParameter("id");
			PaymentRequest paymentRequest = requestService.getPaymentRequestById(Integer.parseInt(reqId));
			modelAndView.addObject("paymentRequest", paymentRequest);
			
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

	@RequestMapping(value = "/request-approval-action", method = RequestMethod.POST)
	public ModelAndView requestApproval(HttpServletRequest request, 			
			HttpServletResponse response) {
		
		ModelAndView modelAndView = null;
		try {
			UserBean userBean = (UserBean) request.getSession().getAttribute(Constants.SESS_USER);
			modelAndView = createModelAndViewInstance(userBean, ROLE, "RequestApprovalDetail");
			
			String reqId = request.getParameter("id");
			PaymentRequest paymentRequest = requestService.getPaymentRequestById(Integer.parseInt(reqId));
			modelAndView.addObject("paymentRequest", paymentRequest);
			
			if ("Approve".equals(request.getParameter("btnApprove"))) {
				requestService.approveRequest(userBean.getUser(), paymentRequest);
				modelAndView.addObject("message", "Permintaan sudah disetujui");
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
