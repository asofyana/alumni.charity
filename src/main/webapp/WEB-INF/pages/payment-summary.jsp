<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<h3 class="box-title">Payment Summary</h3>

<form:form id="form1" enctype="multipart/form-data" method="post" >

	<br>

	<table>
		<tr>
			<td>Committed Donation (Cash In)</td>
			<td>:</td>
			<td>&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${committedDonation}" type="number"/></td>
		</tr>
		<tr>
			<td>Uncommitted Donation (Cash In) &nbsp;&nbsp;&nbsp;</td>
			<td>:</td>
			<td>&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${uncommittedDonation}" type="number"/></td>
		</tr>
		<tr>
			<td>Distribution (Cash Out)</td>
			<td>:</td>
			<td>&nbsp;&nbsp;&nbsp;<fmt:formatNumber value="${distribution}" type="number"/></td>
		</tr>
		<tr>
			<td><b>Total</b></td>
			<td>:</td>
			<td>&nbsp;&nbsp;&nbsp;<b><fmt:formatNumber value="${totalAmount}" type="number"/></b></td>
		</tr>
	</table>

</form:form>


