<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<h3 class="box-title">Request Penyaluran Dana</h3>

<form:form action="${contextPath}/request-approval-action" id="form-request-payment" enctype="multipart/form-data"  
method="post" >

    <div class="box-body">
    	${message}
    </div>
	<input type="hidden" name="id" value="${paymentRequest.id}">
		
	<dl class="dl-horizontal">
		<dt>Tanggal Request</dt>
		<dd>${paymentRequest.createdDate}</dd>
		<dt>Nama</dt>
		<dd>${paymentRequest.title}</dd>
		<dt>Tipe</dt>
		<dd>${paymentRequest.type}</dd>
		<dt>Jumlah</dt>
		<dd><fmt:formatNumber value="${paymentRequest.amount}" type="number"/></dd>
		<dt>Keterangan</dt>
		<dd>${paymentRequest.description}</dd>
	</dl>


    <div class="box-body">
      <input type="submit" class="btn btn-primary" value="Approve" name="btnApprove">
    </div>


</form:form>


