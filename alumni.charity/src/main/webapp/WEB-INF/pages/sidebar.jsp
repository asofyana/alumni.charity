<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="dist/img/avatar.png" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>${user.fullName}</p>
          <!-- Status -->
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu">
        <li class="header">HEADER</li>

        <!-- Optionally, you can add icons to the links -->
        <!-- 
        <li class="active"><a href="#"><i class="fa fa-link"></i> <span>ZZZZZZZZZ</span></a></li>
         -->
        <li><a href="member-search"><i class="fa fa-link"></i> <span>Search Alumni</span></a></li>
        <li><a href="upload-receipt"><i class="fa fa-link"></i> <span>Upload receipt</span></a></li>
        <li><a href="view-contribution"><i class="fa fa-link"></i> <span>View committed donation</span></a></li>
        <li><a href="request-payment"><i class="fa fa-link"></i> <span>Object Donation Request</span></a></li>

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

	<c:if test="${not empty TREASURY}">
        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>Finance</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="search-payment">Search Payment</a></li>
            <li><a href="view-pending-payment">Payment Verification</a></li>
          </ul>
        </li>
    </c:if>

	<c:if test="${not empty APPROVER}">
        <li class="treeview">
          <a href="#"><i class="fa fa-link"></i> <span>Approver</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="view-request-list">View Request List</a></li>
          </ul>
        </li>
	</c:if>

      </ul>
      <!-- /.sidebar-menu -->
    </section>