<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<h3 class="box-title">Payment Verification</h3>

<form:form action="${contextPath}/verify-payment" id="form-payment-verification" enctype="multipart/form-data" class="form-horizontal"  
method="post">

    <div class="box-body">
    	${message}
    </div>
	<input type="hidden" name="id" value="${payment.id}">


	<dl class="dl-horizontal">
		<dt>Date</dt>
		<dd>${payment.createdDate}</dd>
		<dt>Name</dt>
		<dd>${payment.user.fullName}</dd>
		<dt>Amount</dt>
		<dd><fmt:formatNumber value="${payment.amount}" type="number"/></dd>
		<dt>Payment Type</dt>
		<dd>${payment.paymentType}</dd>
	</dl>

	<c:if test="${not empty base64Img}">
	    <div class="box-body" style="width:300px;">
	      <div class="form-group">
	        <label for="exampleInputEmail1">Transfer Proof</label>
	        <img title="" 
				src="data:image/png;base64,${base64Img}" />
	      </div>
	    </div>
    </c:if>

	<c:if test="${empty message}">
	    <div class="box-body">
	      <input type="submit" class="btn btn-primary" value="Verify" name="btnVerify">
	    </div>
    </c:if>

</form:form>
 
</html>