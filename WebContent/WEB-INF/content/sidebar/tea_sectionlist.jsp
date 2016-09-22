<%@page import="com.cl.dao.Course"%>
<%@page import="com.cl.dao.Section"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SectionList</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
</head>
<body>
	<div class="container-fluid">
		<ul class="nav">
			<li><h3>${course_name}<small><a href="teacher_welcome">返回</a></small></h3></li>
			<li class="nav-divider"></li>
			<c:if test="${! empty sectionlist}">
				<c:forEach var="section" items="${sectionlist}">
					<li>
						<a href="teacher_eventwelcome?section_id=${section.section_id}">${section.section_name}</a>
					</li>
				</c:forEach>
			</c:if>
			<c:if test="${empty sectionlist}">
				<li>无章节</li>
			</c:if>
			<li><h3>功能列表</h3></li>
			<li class="nav-divider"></li>
			<li><a href="teacher_managehomework">作业管理</a></li>
			<li><a href="teacher_managesection">章节管理</a></li>
			<li><a href="teacher_managesectionweight?section_id=0">更新权值</a></li>
		</ul>
	</div>
</body>
</html>