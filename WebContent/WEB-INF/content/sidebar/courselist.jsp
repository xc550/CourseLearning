<%@page import="com.cl.dao.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LeftFrame</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
<link rel="stylesheet" href="public/css/main.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<%
					String role = (String)request.getSession().getAttribute("role");
					if (role.equals("manager")) {
				%>
				<% } else { %>
				
				<table class="table">
					<thead>
						<tr><td align="center">功能列表</td></tr>
					</thead>
					<tbody>
						<tr><td align="center"><a href="<%=role %>_getcourseselection">选课</a></td></tr>
					</tbody>
				</table>
				<% } %>
			</div>
		</div>
	</div>
</body>
</html>