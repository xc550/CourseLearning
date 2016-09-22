<%@page import="com.cl.dao.CourseScore"%>
<%@page import="com.cl.dao.Course"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.cl.dao.Section"%>
<%@page import="com.cl.dao.Student"%>
<%@page import="com.cl.dao.SectionScore"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Summary</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<s:action name="sidebar_topframe" executeResult="true"></s:action>
		</div>
		<div class="row">
			<div class="col-md-3">
				<s:action name="sidebar_stu_eventlist" executeResult="true"></s:action>
			</div>
			<div class="col-md-8 col-md-offset-1">
				<div class="row">
					<h2>${section_name}</h2>
					<table class="table table-bordered table-hover"> 
						<tr>
							<c:forEach items="${sectioncolumns}" var="cl">
								<td>${cl}</td>
							</c:forEach>
						</tr>
						<c:choose>
						<c:when test="${section_id > 0}">
							<c:forEach items="${scorearray}" var="score">
								<tr>
									<td>${score.listening}</td>
									<td>${score.answer}</td>
									<td>${score.attendance}</td>
									<td>${score.homework}</td>
									<td>${score.experiment}</td>
									<td>${score.reviewandpreview}</td>
									<td>${score.sum}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach items="${scorearray}" var="stu">
								<tr>
									<c:forEach items="${stu.sectionscore}" var="score">
										<td>${score.sum}</td>	
									</c:forEach>
									<td>${stu.average}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
						</c:choose>
					</table>
				</div><!-- end row -->
			</div><!-- end col-md-8 -->
		</div><!-- end row -->
	</div><!-- end container -->
</body>
</html>