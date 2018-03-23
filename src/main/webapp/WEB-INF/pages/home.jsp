<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	 <button type="button" class="btn button-home" onclick="window.location.href='member-search'">
	 <!-- <i class="fa fa-arrow-circle-right"></i> --> Search Alumni
	 </button>
	<br><br>
	 <button type="button" class="btn button-home" onclick="window.location.href='upload-receipt'">
	 Upload receipt
	 </button>
	<br><br>
	 <button type="button" class="btn button-home" onclick="window.location.href='view-contribution'">
	 View committed donation
	 </button>
	<br><br>
	 <button type="button" class="btn button-home" onclick="window.location.href='request-payment'">
	 Distribution Request
	 </button>
	<br><br>
	 <button type="button" class="btn button-home" onclick="window.location.href='payment-summary'">
	 Cash Summary
	 </button>
	<br><br>
	 <button type="button" class="btn button-home" onclick="window.location.href='member-payment-list'">
	 Payment List
	 </button>
 
 <c:if test="${not empty TREASURY}">
	<br><br>
	 <button type="button" class="btn button-home" onclick="window.location.href='search-payment'">
	 Search Payment
	 </button>
	<br><br>
	 <button type="button" class="btn button-home" onclick="window.location.href='view-pending-payment'">
	 Payment Verification/Request
	 </button>
 </c:if>
 
  <c:if test="${not empty APPROVER}">
	<br><br>
	 <button type="button" class="btn button-home" onclick="window.location.href='view-request-list'">
	 Object Donation Requests
	 </button>
 </c:if>