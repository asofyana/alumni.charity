<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<form:form action="${contextPath}/request-payment-action" id="form-request-payment" enctype="multipart/form-data"  
method="post" modelAttribute="requestPaymentBean">

<div class="box box-info">

    <div class="box-body">
	    <div class="box-header with-border">
	      <h3 class="box-title">Request Penyaluran Dana</h3>
	    </div>
    </div>

    <div class="box-body">
    	${message}
    </div>
		
    <div class="box-body" style="width:300px;">
      <div class="form-group">
        <label for="exampleInputEmail1">Nama</label>
        <form:input type="text" class="form-control" name="title" path="title" />
      </div>
      <div class="form-group">
        <label for="exampleInputEmail1">Tipe</label>
        <form:select class="form-control" name="type" path="type" >
        	<option value="Pendidikan">Pendidikan</option>
        	<option value="Kesehatan">Kesehatan</option>
        </form:select>
      </div>
      <div class="form-group">
        <label for="exampleInputEmail1">Jumlah</label>
        <form:input class="form-control" name="amount" path="amount" />
      </div>
      <div class="form-group">
        <label for="exampleInputEmail1">Keterangan</label>
        <form:textarea class="form-control" name="description" path="description" />
      </div>
    </div>

    <div class="box-body">
      <button type="submit" class="btn btn-primary">Submit</button>
    </div>

</div>
</form:form>


