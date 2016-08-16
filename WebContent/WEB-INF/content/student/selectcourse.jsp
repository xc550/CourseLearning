<%@page import="java.util.HashMap"%>
<%@page import="com.cl.dao.Teacher"%>
<%@page import="com.cl.dao.CourseTeacher"%>
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
					HashMap<Integer, ArrayList<CourseTeacher>> map = (HashMap<Integer, ArrayList<CourseTeacher>>)request.getAttribute("courseteacher");
				%>
				<div class="row">
					<h2>可选课程列表</h2>
					<ul class="list-group">
					<% 
						for (int i = 0; i < courselistnotselected.size(); i++) {
							int course_id = courselistnotselected.get(i).getCourse_id();
							ArrayList<CourseTeacher> classes = map.get(new Integer(course_id));
							if (classes.size() != 0) {
						%>
						<li class="list-group-item">
							<strong><%=courselistnotselected.get(i).getCourse_name() %></strong>
							<%
								for (int j = 0; j < classes.size(); j++) {
									int class_id = classes.get(j).getClass_id();
									Teacher te = Teacher.getTeacherByTeacherId(classes.get(j).getId());
							%>
								<a href="student_selectcourse?course_id=<%=course_id%>&class_id=<%=class_id%>">
									<button class="btn btn-primary btn-sm"><%=te.getName() %></button>
								</a>
							<%
								}
							%>
						</li>
					<% }} %>
					</ul>
				</div>
				<div class="row">
					<h2>已经选到的课程</h2>
					<ul class="list-group">
					<% for (int i = 0; i < courseselected.size(); i++) { %>
						<li class="list-group-item">
							<a href="student_notselectcourse?course_id=<%=courseselected.get(i).getCourse_id()%>"><span class="glyphicon glyphicon-remove"></span></a>
							<strong><%=courseselected.get(i).getCourse_name() %></strong>
						</li>
					<% } %>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>