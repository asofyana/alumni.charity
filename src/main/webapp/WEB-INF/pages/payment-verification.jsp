<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

      <div class="row">
        <div class="col-xs-12">

          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Verifikasi Pembayaran</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th>Tanggal</th>
                  <th>Nama</th>
                  <th>Jumlah</th>
                  <th>Cash in/out</th>
                </tr>
                </thead>
                <tbody>

			<c:forEach items="${paymentList}" var="payment">
                <tr>
                  <td><a href="view-payment-detail?id=${payment.id}">${payment.createdDate}</a></td>
                  <td>${payment.user.fullName}</td>
                  <td><fmt:formatNumber value="${payment.amount}" type="number"/></td>
                  <td>${payment.cashFlow}</td>
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

