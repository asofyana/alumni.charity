<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Registration</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="plugins/iCheck/square/blue.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body class="hold-transition login-page">

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<h3 class="box-title">Member Registration</h3>

<form:form action="${contextPath}/member-registration-action" id="form-registration" enctype="multipart/form-data"  
method="post" modelAttribute="registrationBean">

    <div class="form-group text-red">
        ${message}
    </div>

    <div class="box-body" style="width:300px;">
      <div class="form-group">
        <label for="exampleInputEmail1">Email</label>
        <form:input type="text" class="form-control" name="email" path="email" />
      </div>
      <div class="form-group">
        <label for="exampleInputEmail1">Password</label>
        <form:input type="password" class="form-control" name="password" path="password" />
      </div>
      <div class="form-group">
        <label for="exampleInputEmail1">Full Name</label>
        <form:input type="text" class="form-control" name="fullName" path="fullName" />
      </div>
      <div class="form-group">
        <label for="exampleInputEmail1">Address</label>
        <form:textarea class="form-control" name="address" path="address" />
      </div>
      <div class="form-group">
        <label for="exampleInputEmail1">City</label>
        <form:input type="text" class="form-control" name="city" path="city" />
      </div>
      <div class="form-group">
        <label for="exampleInputEmail1">Postal Code</label>
        <form:input type="text" class="form-control" name="postalCode" path="postalCode" />
      </div>
      <div class="form-group">
        <label for="exampleInputEmail1">Phone Number</label>
        <form:input type="text" class="form-control" name="homePhoneNumber" path="homePhoneNumber" />
      </div>
      <div class="form-group">
        <label for="exampleInputEmail1">Mobile Number</label>
        <form:input type="text" class="form-control" name="mobileNumber" path="mobileNumber" />
      </div>
      <div class="form-group">
        <label for="exampleInputEmail1">Grade 1</label>
        <form:input type="text" class="form-control" name="grade1" path="grade1" />
      </div>
      <div class="form-group">
        <label for="exampleInputEmail1">Grade 2</label>
        <form:input type="text" class="form-control" name="grade2" path="grade2" />
      </div>
      <div class="form-group">
        <label for="exampleInputEmail1">Grade 3</label>
        <form:input type="text" class="form-control" name="grade3" path="grade3" />
      </div>
    </div>

    <div class="box-body">
      <button type="submit" class="btn btn-primary">Submit</button>
    </div>

</form:form>

<!-- jQuery 2.2.3 -->
<script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="plugins/iCheck/icheck.min.js"></script>
<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
  });
</script>
</body>
</html>

