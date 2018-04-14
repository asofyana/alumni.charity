<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


      <div class="box box-info">
        <div class="box-header with-border">
          <h3 class="box-title">Ganti Password</h3>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
  `		<form:form action="change-password-action" method="POST">
  
      	<div class="box-body">
    		${message}
    	</div>
  
          <div class="box-body">
            <div class="form-group">
              <label for="inputEmail3" class="col-sm-2 control-label">Password Lama</label>
              <div class="col-sm-10">
                 <input type="password" name="old-password" class="form-control" id="old-password" >
              </div>
            </div>
            <div class="form-group">
              <label for="inputEmail3" class="col-sm-2 control-label">Password Baru</label>
              <div class="col-sm-10">
                 <input type="password" name="new-password" class="form-control" id="new-password" >
              </div>
            </div>
            <div class="form-group">
              <label for="inputEmail3" class="col-sm-2 control-label">Konfirmasi Password</label>
              <div class="col-sm-10">
                 <input type="password" name="confirm-password" class="form-control" id="confirm-password" >
              </div>
            </div>
          </div>
          <!-- /.box-body -->
          <div class="box-footer">
            <button type="submit" class="btn btn-info pull-right">Submit</button>
          </div>
          <!-- /.box-footer -->
        </form:form>
      </div>

