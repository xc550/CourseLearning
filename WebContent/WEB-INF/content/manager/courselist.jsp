<%@page import="com.cl.dao.Section"%>
<%@page import="com.cl.dao.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CourseList</title>
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
				<%
					ArrayList<Course> courselist = (ArrayList<Course>)request.getAttribute("courselist");
				%>
				<div class="row">
					<h2>课程列表</h2>
					<ul class="col-md-8 list-group">
						<%
							int i = 0, j = 0;
							for (i = 0; i < courselist.size(); i++) {
						%>
						<li class="list-group-item">
							<div class="row">
								<div class="col-md-3">
									<strong><%=courselist.get(i).getCourse_name() %></strong>
								</div>
								<div class="col-md-3">
									<p>课堂容量：<%=courselist.get(i).getCourse_classcapacity() %></p>
								</div>
								<div class="col-md-2 col-md-offset-1">
									<a href="manager_getassignteacher?course_id=<%=courselist.get(i).getCourse_id() %>">
										<button class="btn btn-sm btn-info">分配教师</button>
									</a>
								</div>
								<div class="col-md-2">
									<a href="manager_deletecourse?course_id=<%=courselist.get(i).getCourse_id() %>">
										<button class="btn btn-sm btn-danger">删除课程</button>
									</a>
								</div>
							</div>
						</li>
						<% } %>
					</ul>
				</div>
				<div class="row">
					<h2>添加课程</h2>
					<form class="form-inline" action="manager_addcourse" method="post">
						<div class="form-group">
							<label class="sr-only" for="inputCourseName">课程名称</label>
							<input type="text" style="width: 150px" name="course.course_name" class="form-control" id="inputCourseName" placeholder="课程名称">
						</div>
						<div class="form-group">
							<label class="sr-only" for="inputCourseCapacity">课程容量</label>
							<input type="number" min="0" style="width: 150px" name="course.course_classcapacity" class="form-control" id="inputCourseCapacity" placeholder="课程容量">
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