<%@page import="com.cl.dao.Student"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>StudentList</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<s:action name="sidebar_topframe" executeResult="true"></s:action>
		</div>
		<div class="row">
			<div class="col-md-3">
				<s:action name="sidebar_man_functionlist" executeResult="true"></s:action>
			</div>
			<div class="col-md-8 col-md-offset-1">
				<div class="row">
					<%
						String[] columns = (String[])request.getAttribute("studentcolumns");
						ArrayList<Student> stulist = (ArrayList<Student>)request.getAttribute("studentlist");
						int nowcolumn = ((Integer)request.getAttribute("nowcolumn")).intValue();
						int each = ((Integer)request.getAttribute("each")).intValue();
				    	int totalcolumn = ((Integer)request.getAttribute("totalcolumn")).intValue();
				    	int precolumn = ((Integer)request.getAttribute("precolumn")).intValue();
				    	int nextcolumn = ((Integer)request.getAttribute("nextcolumn")).intValue();
				    	int pre = Math.max(nowcolumn - 1, 1);
					  	int next = Math.min(nowcolumn + 1, totalcolumn);
					%>
					<h2>学生列表</h2>
					<table class="table table-hover">
						<thead>
							<tr>
								<td></td>
								<% for (int i = 0; i < columns.length; i++) { %>
								<td><%=columns[i] %></td>
								<% } %>
								<td>删除</td>
							</tr>
						</thead>
						<tbody>
							<%
								for (int i = (nowcolumn - 1) * each, j = 0; j < each && i < stulist.size(); i++, j++) {
									Student stu = stulist.get(i);
							%>
							<tr>
								<td><%=j + 1 %></td>
								<td><%=stu.getLoginname() %></td>
								<td><%=stu.getName() %></td>
								<td><%=stu.getGender() %></td>
								<td><a href="manager_deletestudent?student_id=<%=stu.getId()%>"><span class="glyphicon glyphicon-remove"></span></a></td>
							</tr>
							<% } %>
						</tbody>
					</table>
				</div>
				<div class="row">
					<div class="col-md-3 col-md-offset-5">
						<nav>
						  <ul class="pagination pagination-sm">
						  <%
						  	if (pre != 1) {
					      %>
						    <li>
						      <a href="manager_studentlist?column=<%=pre %>" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </a>
						    </li>
						    <%
						  		}
						    	for (int i = precolumn; i <= nextcolumn; i++) {
						    %>
						    	<li><a href="manager_studentlist?column=<%=i %>"><%=i %></a></li>
						    <%
						    	}
						    	if (next != totalcolumn) {
						    %>
						    <li>
						      <a href="manager_studentlist?column=<%=next %>" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </a>
						    </li>
						    <% } %>
						  </ul>
						</nav>
					</div>
				</div>
				<div class="row">
					<h2>添加学生</h2>
					<form class="form-inline" action="manager_addstudent" method="post">
						<div class="form-group">
							<label class="sr-only" for="inputStudentLoginname">学号</label>
							<input type="text" name="student.loginname" class="form-control" id="inputStudentLoginname" placeholder="学号">
						</div>
						<div class="form-group">
							<label class="sr-only" for="inputStudentName">姓名</label>
							<input type="text" name="student.name" class="form-control" id="inputStudentName" placeholder="姓名">
						</div>
						<div class="form-group">
							<label class="sr-only" for="inputStudentGender">年级</label>
							<input type="text" name="student.gender" class="form-control" id="inputStudentGender" placeholder="年级">
						</div>
						<button type="submit" class="btn btn-default">提交</button>
					</form>
				</div>
				<div class="row">
					<h2>数据导入</h2>
				</div>
			</div>
		</div>
	</div>
</body>
</html>