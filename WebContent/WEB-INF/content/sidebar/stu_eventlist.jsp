<%@page import="com.cl.dao.Event"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cl.dao.Section"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EventList</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
</head>
<body>
	<div class="container-fluid">
		<ul class="nav">
			<li><h3>${section_name}<small><a href="student_sectionwelcome?course_id=${course_id}">返回</a></small></h3></li>
			<li class="nav-divider"></li>
			<c:if test="${! empty eventlist}">
				<c:forEach var="event" items="${eventlist}">
					<li>
						<a href="student_geteventinfo?event_id=${event.event_id}">${event.event_content}</a>
					</li>
				</c:forEach>
			</c:if>
			<c:if test="${empty eventlist}">
				<li>无事件</li>
			</c:if>
			<li><h3>功能列表</h3></li>
			<li class="nav-divider"></li>
			<li><a href="student_getsectionsummary">查看个人成绩</a></li>
		</ul>
	</div>
</body>
</html>