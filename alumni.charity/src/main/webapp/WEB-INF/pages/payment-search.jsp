<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

      <div class="box box-info">
        <div class="box-header with-border">
          <h3 class="box-title">Search Payment</h3>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
  <form:form action="search-payment-action" method="POST">
          <div class="box-body">
            <div class="form-group">
              <label for="inputEmail3" class="col-sm-2 control-label">Start Date</label>
              <div class="col-sm-10">
                <div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <input type="text" value="${strStartDate}" name="startDate" class="form-control pull-right" id="datepicker">
                </div>
              </div>
            </div>
            <div class="form-group">
              <label for="inputEmail3" class="col-sm-2 control-label">End Date</label>
              <div class="col-sm-10">
                <div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <input type="text" value="${strEndDate}" name="endDate" class="form-control pull-right" id="datepicker2">
                </div>
              </div>
            </div>
            <div class="form-group">
              <label for="inputPassword3" class="col-sm-2 control-label">Status</label>
              <div class="col-sm-10">
                <select name="status">
                	<option value="">ALL</option>
                	<option value="NEW" <c:if test="${status eq 'NEW'}">selected</c:if>>NEW</option>
                	<option value="VERIFIED" <c:if test="${status eq 'VERIFIED'}">selected</c:if>>VERIFIED</option>
                </select>
              </div>
            </div>
          </div>
          <!-- /.box-body -->
          <div class="box-footer">
            <button type="submit" class="btn btn-info pull-right">Search</button>
          </div>
          <!-- /.box-footer -->
        </form:form>
      </div>

      <div class="row">
        <div class="col-xs-12">

          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Search Result</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th>Date</th>
                  <th>Name</th>
                  <th>Amount</th>
                  <th>Cash in/out</th>
                  <th>Status</th>
                </tr>
                </thead>
                <tbody>

			<c:forEach items="${paymentList}" var="payment">
                <tr>
                  <td>${payment.createdDate}</td>
                  <td>${payment.user.fullName}</td>
                  <td><fmt:formatNumber value="${payment.amount}" type="number"/></td>
                  <td>${payment.cashFlow}</td>
                  <td>${payment.status}</td>
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


