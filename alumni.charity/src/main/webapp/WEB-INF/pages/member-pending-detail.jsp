<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<h3 class="box-title">Request Payment</h3>

<form:form action="${contextPath}/member-approval-action" id="form1" enctype="multipart/form-data"  
method="post" >

    <div class="box-body">
    	${message}
    </div>
	<input type="hidden" name="email" value="${user.email}">
		
	<dl class="dl-horizontal">
		<dt>Request Date</dt>
		<dd>${user.createdDate}</dd>
		<dt>Name</dt>
		<dd>${user.fullName}</dd>
		<dt>Email</dt>
		<dd>${user.email}</dd>
		<dt>Address</dt>
		<dd>${user.address}</dd>
		<dt>City</dt>
		<dd>${user.city}</dd>
		<dt>Phone</dt>
		<dd>${user.homePhoneNumber}</dd>
		<dt>Mobile</dt>
		<dd>${user.mobileNumber}</dd>
		<dt>Grade 1</dt>
		<dd>${user.grade1}</dd>
		<dt>Grade 2</dt>
		<dd>${user.grade2}</dd>
		<dt>Grade 3</dt>
		<dd>${user.grade3}</dd>
	</dl>


    <div class="box-body">
      <input type="submit" class="btn btn-primary" value="Approve" name="btnApprove">
    </div>


</form:form>


