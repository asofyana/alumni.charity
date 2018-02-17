<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<h3 class="box-title">Member Detail</h3>

<form:form action="${contextPath}/member-approval-action" id="form1" enctype="multipart/form-data"  
method="post" >

    <div class="box-body">
    	${message}
    </div>
	<input type="hidden" name="email" value="${user.email}">
		
	<dl class="dl-horizontal">
		<c:if test="${not empty approveButton}">
		<dt>Request Date</dt>
		<dd>${user.createdDate}</dd>
		</c:if>
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
		<dt>Profession</dt>
		<dd>${user.jobTitle}</dd>
		<dt>Office Name</dt>
		<dd>${user.officeName}</dd>
		<dt>Office Address</dt>
		<dd>${user.officeAddress}</dd>
		<dt>Grade 1</dt>
		<dd>${user.grade1}</dd>
		<dt>Grade 2</dt>
		<dd>${user.grade2}</dd>
		<dt>Grade 3</dt>
		<dd>${user.grade3}</dd>
	</dl>

	<c:if test="${not empty approveButton}">
    <div class="box-body">
      <input type="submit" class="btn btn-primary" value="Approve" name="btnApprove">
    </div>
	</c:if>

	<c:if test="${not empty ADMIN}">
    <div class="box-body">
      <input type="button" class="btn btn-primary" value="Change Role" name="btnChangeRole" onClick="window.location.href='member-role-update?email=${user.email}'">
    </div>
	</c:if>

</form:form>


