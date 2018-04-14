<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

Upload Bukti Transfer

<form:form action="${contextPath}/upload-receipt-action" id="form-upload" enctype="multipart/form-data"  
method="post" modelAttribute="uploadReceiptBean">


    <div class="box-body" style="width:300px;">
      <div class="form-group">
        <label for="exampleInputEmail1">Total</label>
        <form:input type="text" class="form-control" name="totalAmount" path="totalAmount" />
        <br>
        <ul>
        	<li> 
        		<label for="exampleInputEmail1">Sumbangan Wajib</label>
        		<form:input type="text" class="form-control" name="committedAmount" path="committedAmount" />
        	</li>
        	<li>
        		<label for="exampleInputEmail1">Sumbangan Sukarela</label>
        		<form:input type="text" class="form-control" name="uncommittedAmount" path="uncommittedAmount" />
        	</li>
        </ul>
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


