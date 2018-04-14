<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <section class="sidebar">

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu">
        <!-- Optionally, you can add icons to the links -->
        <!-- 
        <li class="active"><a href="#"><i class="fa fa-link"></i> <span>ZZZZZZZZZ</span></a></li>
         -->
        <li><a href="member-search"><i class="fa fa-link"></i> <span>Cari Alumni</span></a></li>
        <li><a href="upload-receipt"><i class="fa fa-link"></i> <span>Upload Bukti Transfer</span></a></li>
        <li><a href="view-contribution"><i class="fa fa-link"></i> <span>Sumbangan Wajib</span></a></li>
        <li><a href="request-payment"><i class="fa fa-link"></i> <span>Permintaan Penyaluran</span></a></li>
        <li><a href="payment-summary"><i class="fa fa-link"></i> <span>Posisi Kas</span></a></li>
        <li><a href="member-payment-list"><i class="fa fa-link"></i> <span>Daftar Pembayaran</span></a></li>
        <li><a href="change-password"><i class="fa fa-link"></i> <span>Ganti Password</span></a></li>

<!-- 
	<c:if test="${not empty ADMIN}">
        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>Admin</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pending-member-list">Approve New Member</a></li>
          </ul>
        </li>
	</c:if>
-->
	<c:if test="${not empty TREASURY}">
        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>Finance</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="search-payment">Cari Pembayaran</a></li>
            <li><a href="view-pending-payment">Verifikasi/Permintaan Pembayaran</a></li>
          </ul>
        </li>
    </c:if>

	<c:if test="${not empty APPROVER}">
        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>Verifikasi</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="view-request-list">Verifikasi Permintaan Penyaluran</a></li>
          </ul>
        </li>
	</c:if>

      </ul>
      <!-- /.sidebar-menu -->
    </section>