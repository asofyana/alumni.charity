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
	      <h3 class="box-title">Object Donation Request</h3>
	    </div>
    </div>

    <div class="box-body">
    	${message}
    </div>
		
    <div class="box-body" style="width:300px;">
      <div class="form-group">
        <label for="exampleInputEmail1">Name</label>
        <form:input type="text" class="form-control" name="title" path="title" />
      </div>
      <div class="form-group">
        <label for="exampleInputEmail1">Type</label>
        <form:select class="form-control" name="type" path="type" >
        	<option value="Education">Education</option>
        	<option value="Health">Health</option>
        </form:select>
      </div>
      <div class="form-group">
        <label for="exampleInputEmail1">Amount</label>
        <form:input class="form-control" name="amount" path="amount" />
      </div>
      <div class="form-group">
        <label for="exampleInputEmail1">Description</label>
        <form:textarea class="form-control" name="description" path="description" />
      </div>
    </div>

    <div class="box-body">
      <button type="submit" class="btn btn-primary">Submit</button>
    </div>

</div>
</form:form>


