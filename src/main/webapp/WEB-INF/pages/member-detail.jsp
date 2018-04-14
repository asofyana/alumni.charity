<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<h3 class="box-title">Detail Anggota</h3>

<form:form action="${contextPath}/member-approval-action" id="form1" enctype="multipart/form-data"  
method="post" >

    <div class="box-body">
    	${message}
    </div>
	<input type="hidden" name="email" value="${user.email}">
		
	<dl class="dl-horizontal">
		<c:if test="${not empty approveButton}">
		<dt>Tanggal Request</dt>
		<dd>${user.createdDate}</dd>
		</c:if>
		<dt>Nam</dt>
		<dd>${user.fullName}</dd>
		<dt>Email</dt>
		<dd>${user.email}</dd>
		<dt>Alamat</dt>
		<dd>${user.address}</dd>
		<dt>Kota</dt>
		<dd>${user.city}</dd>
		<dt>No Telepon</dt>
		<dd>${user.homePhoneNumber}</dd>
		<dt>No Handphone</dt>
		<dd>${user.mobileNumber}</dd>
		<dt>Pekerjaan</dt>
		<dd>${user.jobTitle}</dd>
		<dt>Nama Kantor</dt>
		<dd>${user.officeName}</dd>
		<dt>Alamat Kantor</dt>
		<dd>${user.officeAddress}</dd>
		<dt>Kelas 1</dt>
		<dd>${user.grade1}</dd>
		<dt>Kelas 2</dt>
		<dd>${user.grade2}</dd>
		<dt>Kelas 3</dt>
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


