<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

      <div class="row">
        <div class="col-xs-12">

          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Request Approval</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Alamat</th>
                  <th>Kota</th>
                  <th>Tanggal Request</th>
                </tr>
                </thead>
                <tbody>

			<c:forEach items="${userList}" var="request">
                <tr>
                  <td><a href="pending-member-detail?email=${request.email}">${request.fullName}</a></td>
                  <td>${request.email}</td>
                  <td>${request.address}</td>
                  <td>${request.city}</td>
                  <td>${request.createdDate}</td>
                </tr>
			</c:forEach>

                </tbody>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
</div>
</div>

