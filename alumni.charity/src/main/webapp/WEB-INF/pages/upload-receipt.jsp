<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

Upload Receipt

<form:form action="${contextPath}/upload-receipt-action" id="form-upload" enctype="multipart/form-data"  
method="post" modelAttribute="uploadReceiptBean">


    <div class="box-body" style="width:300px;">
      <div class="form-group">
        <label for="exampleInputEmail1">Amount</label>
        <form:input type="text" class="form-control" name="amount" path="amount" />
      </div>
      <div class="form-group">
        <label for="exampleInputFile">File input</label>
		<form:input class="" name="file" accept="image/*" type="file" path="multipartFile" />
      </div>
    </div>

    <div class="box-body">
      <button type="submit" class="btn btn-primary">Submit</button>
    </div>

    <div class="box-body">
    	${message}
    </div>
		

</form:form>


