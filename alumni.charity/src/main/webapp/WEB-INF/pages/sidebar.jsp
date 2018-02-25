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
        <li><a href="member-search"><i class="fa fa-link"></i> <span>Search Alumni</span></a></li>
        <li><a href="upload-receipt"><i class="fa fa-link"></i> <span>Upload receipt</span></a></li>
        <li><a href="view-contribution"><i class="fa fa-link"></i> <span>View committed donation</span></a></li>
        <li><a href="request-payment"><i class="fa fa-link"></i> <span>Distribution Request</span></a></li>
        <li><a href="payment-summary"><i class="fa fa-link"></i> <span>Cash Summary</span></a></li>
        <li><a href="member-payment-list"><i class="fa fa-link"></i> <span>Payment List</span></a></li>

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
            <li><a href="search-payment">Search Payment</a></li>
            <li><a href="view-pending-payment">Payment Verification/Request</a></li>
          </ul>
        </li>
    </c:if>

	<c:if test="${not empty APPROVER}">
        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>Verification</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="view-request-list">Object Donation Requests</a></li>
          </ul>
        </li>
	</c:if>

      </ul>
      <!-- /.sidebar-menu -->
    </section>