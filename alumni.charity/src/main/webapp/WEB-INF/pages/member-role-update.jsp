<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<h3 class="box-title">Member Role Update</h3>

<form:form action="${contextPath}/member-role-update-action" id="form1" enctype="multipart/form-data"  
method="post" >

<div class="box box-info">

    <div class="box-body">
    	${message}
    </div>
	<input type="hidden" name="email" value="${user.email}">

    <div class="box-body">
		
			<dl class="dl-horizontal">
				<dt>Name</dt>
				<dd>${user.fullName}</dd>
				<dt>Email</dt>
				<dd>${user.email}</dd>
				<dt>Address</dt>
				<dd>${user.address}</dd>
				<dt>City</dt>
				<dd>${user.city}</dd>
			</dl>
		
		<div class="form-group">
	        <label>
	          Role:<br><br>
	          <input type="checkbox" class="minimal" ${ROLE_ADMIN} name="chkRoleAdmin">&nbsp;&nbsp;&nbsp;&nbsp;Admin<br>
	          <input type="checkbox" class="minimal" ${ROLE_TREASURY} name="chkRoleTreasury">&nbsp;&nbsp;&nbsp;&nbsp;Finance<br>
	          <input type="checkbox" class="minimal" ${ROLE_APPROVER} name="chkRoleApprover">&nbsp;&nbsp;&nbsp;&nbsp;Verificator
	        </label>
		</div>
	
	    <div class="box-body">
	      <input type="submit" class="btn btn-primary" value="Update" name="btnUpdate">
	    </div>
	</div>
</div>
</form:form>


