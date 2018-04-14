<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	 <button type="button" class="btn button-home" onclick="window.location.href='member-search'">
	 <!-- <i class="fa fa-arrow-circle-right"></i> --> Cari Alumni
	 </button>
	<br><br>
	 <button type="button" class="btn button-home" onclick="window.location.href='upload-receipt'">
	 Upload Bukti Transfer
	 </button>
	<br><br>
	 <button type="button" class="btn button-home" onclick="window.location.href='view-contribution'">
	 Sumbangan Wajib
	 </button>
	<br><br>
	 <button type="button" class="btn button-home" onclick="window.location.href='request-payment'">
	 Permintaan Penyaluran
	 </button>
	<br><br>
	 <button type="button" class="btn button-home" onclick="window.location.href='payment-summary'">
	 Posisi Kas
	 </button>
	<br><br>
	 <button type="button" class="btn button-home" onclick="window.location.href='member-payment-list'">
	 Daftar Pembayaran
	 </button>
 
 <c:if test="${not empty TREASURY}">
	<br><br>
	 <button type="button" class="btn button-home" onclick="window.location.href='search-payment'">
	 Cari Pembayaran
	 </button>
	<br><br>
	 <button type="button" class="btn button-home" onclick="window.location.href='view-pending-payment'">
	 Verifikasi/Permintaan Pembayaran
	 </button>
 </c:if>
 
  <c:if test="${not empty APPROVER}">
	<br><br>
	 <button type="button" class="btn button-home" onclick="window.location.href='view-request-list'">
	 Verifikasi Permintaan Penyaluran
	 </button>
 </c:if>