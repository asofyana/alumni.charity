<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

      <div class="box box-info">
        <div class="box-header with-border">
          <h3 class="box-title">Search Alumni</h3>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
  <form:form action="member-search-action" method="POST" modelAttribute="user">
          <div class="box-body">
            <div class="form-group">
              <label for="inputEmail3" class="col-sm-2 control-label">Name</label>
              <div class="col-sm-10">
                 <form:input type="text" name="strName" class="form-control" id="name" path="fullName" />
              </div>
            </div>
            <div class="form-group">
              <label for="inputEmail3" class="col-sm-2 control-label">City Address</label>
              <div class="col-sm-10">
                 <form:input type="text" name="strCity" class="form-control" id="city" path="city" />
              </div>
            </div>
            <div class="form-group">
              <label for="inputPassword3" class="col-sm-2 control-label">Grade 1</label>
              <div class="col-sm-10">
                <form:select name="grade1" class="form-control select2" path="grade1">
                	<option value="">ALL</option>
                	<option value="A">1A</option>
                	<option value="B">1B</option>
                	<option value="C">1C</option>
                	<option value="D">1D</option>
                	<option value="E">1E</option>
                	<option value="F">1F</option>
                </form:select>
              </div>
            </div>
            <div class="form-group">
              <label for="inputPassword3" class="col-sm-2 control-label">Grade 2</label>
              <div class="col-sm-10">
                <form:select name="grade2" class="form-control select2" path="grade2">
                	<option value="">ALL</option>
                	<option value="A">2A</option>
                	<option value="B">2B</option>
                	<option value="C">2C</option>
                	<option value="D">2D</option>
                	<option value="E">2E</option>
                	<option value="F">2F</option>
                </form:select>
              </div>
            </div>
            <div class="form-group">
              <label for="inputPassword3" class="col-sm-2 control-label">Grade 3</label>
              <div class="col-sm-10">
                <form:select name="grade3" class="form-control select2" path="grade3">
                	<option value="">ALL</option>
                	<option value="A">3A</option>
                	<option value="B">3B</option>
                	<option value="C">3C</option>
                	<option value="D">3D</option>
                	<option value="E">3E</option>
                	<option value="F">3F</option>
                </form:select>
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
                  <th>Name</th>
                  <th>City Address</th>
                  <th>Grade 1</th>
                  <th>Grade 2</th>
                  <th>Grade 3</th>
                </tr>
                </thead>
                <tbody>

			<c:forEach items="${memberList}" var="member">
                <tr>
                  <td><a href="member-detail?email=${member.email}">${member.fullName}</a></td>
                  <td>${member.city}</td>
                  <td>${member.grade1}</td>
                  <td>${member.grade2}</td>
                  <td>${member.grade3}</td>
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


