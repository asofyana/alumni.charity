<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

      <div class="row">
        <div class="col-xs-12">

          <div class="box">
            <div class="box-header">
              <h3 class="box-title">View Contribution</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th>Month</th>
                  <th>Amount</th>
                  <th>Payment Date</th>
                </tr>
                </thead>
                <tbody>

			<c:forEach items="${contributionList}" var="contribution">
                <tr>
                  <td><fmt:formatDate pattern="MMM yyyy" value = "${contribution.month}" /></td>
                  <td><fmt:formatNumber value="${contribution.amount}" type="number"/></td>
                  <td>${contribution.payment.createdDate}</td>
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

