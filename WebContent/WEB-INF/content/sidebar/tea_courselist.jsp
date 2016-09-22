<%@page import="com.cl.dao.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TeacherCourseList</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
</head>
<body>
	<div class="container-fluid">
		<ul class="nav">
			<li><h3>课程列表</h3></li>
			<li class="nav-divider"></li>
			<c:if test="${! empty courselist}">
				<c:forEach var="course" items="${courselist}">
					<li>
						<a href="teacher_sectionwelcome?course_id=${course.course_id}">${course.course_name}</a>
					</li>
				</c:forEach>
			</c:if>
			<c:if test="${empty courselist}">
				<li>无课程</li>
			</c:if>
		</ul>
	</div>
</body>
</html>