<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<c:if test="${not empty payment.paymentRequest}">
		<h3 class="box-title">Request Pembayaran</h3>
	</c:if>
	
	<c:if test="${empty payment.paymentRequest}">
		<h3 class="box-title">Verifikasi Pembayaran</h3>
	</c:if>


<form:form action="${contextPath}/verify-payment" id="form-payment-verification" enctype="multipart/form-data" class="form-horizontal"  
method="post">

    <div class="box-body">
    	${message}
    </div>
	<input type="hidden" name="id" value="${payment.id}">


	<dl class="dl-horizontal">
		<dt>Date</dt>
		<dd>${payment.createdDate}</dd>

		<c:if test="${not empty payment.paymentRequest}">
			<dt>Requester</dt>
		</c:if>
		<c:if test="${empty payment.paymentRequest}">
			<dt>Nama</dt>
		</c:if>

		<dd>${payment.user.fullName}</dd>
		<dt>Jumlah</dt>
		<dd><fmt:formatNumber value="${payment.amount}" type="number"/></dd>
		<dt>Cash in/out</dt>
		<dd>${payment.cashFlow}</dd>
	</dl>

	<c:if test="${not empty payment.paymentRequest}">
			<dl class="dl-horizontal">
		<dt>Tanggal Request</dt>
		<dd>${payment.paymentRequest.createdDate}</dd>
		<dt>Judul</dt>
		<dd>${payment.paymentRequest.title}</dd>
		<dt>Tipe</dt>
		<dd>${payment.paymentRequest.type}</dd>
		<dt>Jumlah</dt>
		<dd><fmt:formatNumber value="${payment.paymentRequest.amount}" type="number"/></dd>
		<dt>Keterangan</dt>
		<dd>${payment.paymentRequest.description}</dd>
	</dl>

	</c:if>

	<c:if test="${not empty base64Img}">
	    <div class="box-body" style="width:300px;">
	      <div class="form-group">
	        <label for="exampleInputEmail1">Bukti Transfer</label>
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