<%@page import="com.cl.dao.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SelectCourse</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<s:action name="sidebar_topframe" executeResult="true"></s:action>
		</div>
		<div class="row">
			<div class="col-md-3">
				<s:action name="sidebar_stu_courselist" executeResult="true"></s:action>
			</div>
			<div class="col-md-8 col-md-offset-1">
				<%
					ArrayList<Course> courselistnotselected = (ArrayList<Course>)request.getAttribute("courselistnotselected");
					ArrayList<Course> courseselected = (ArrayList<Course>)request.getAttribute("courseselected");
				%>
				<div class="row">
					<h2>可选课程列表</h2>
					<ul class="list-group">
					<% for (int i = 0; i < courselistnotselected.size(); i++) { %>
						<li class="list-group-item">
							<a href="teacher_selectcourse?course_id=<%=courselistnotselected.get(i).getCourse_id()%>"><span class="glyphicon glyphicon-ok"></span></a>
							<%=courselistnotselected.get(i).getCourse_name() %>
						</li>
					<% } %>
					</ul>
				</div>
				<div class="row">
					<h2>已经选到的课程</h2>
					<ul class="list-group">
					<% for (int i = 0; i < courseselected.size(); i++) { %>
						<li class="list-group-item">
							<a href="teacher_notselectcourse?course_id=<%=courseselected.get(i).getCourse_id()%>"><span class="glyphicon glyphicon-remove"></span></a>
							<%=courseselected.get(i).getCourse_name() %>
						</li>
					<% } %>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>