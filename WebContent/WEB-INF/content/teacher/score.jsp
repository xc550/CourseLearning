<%@page import="com.cl.dao.CourseScore"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.cl.dao.Student"%>
<%@page import="com.cl.dao.SectionScore"%>
<%@page import="com.cl.dao.Section"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Score</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<s:action name="sidebar_topframe" executeResult="true"></s:action>
		</div>
		<div class="row">
			<div class="col-md-3 well">
				<s:action name="sidebar_tea_eventlist" executeResult="true"></s:action>
			</div>
			<div class="col-md-8 col-md-offset-1">
				<div class="row">
					<h2>${section_name}</h2>
					<form action="teacher_updatescore" method="post">
						<table class="table table-bordered table-hover"> 
							<tr>
								<td>编号</td>
								<c:forEach items="${sectioncolumns}" var="cl">
									<td>${cl}</td>
								</c:forEach>
							</tr>
							<c:forEach items="${scorearray}" var="score" varStatus="st">
								<tr>
									<td><input name="sectionscore[${st.index}].student_id" value="${score.student_id}" 
										style="border: 0px; background-color: transparent; width: 10px;">
									</td>
									<td>${studentlist[st.index].loginname}</td>
									<td>${studentlist[st.index].name}</td>
									<td><input name="sectionscore[${st.index}].answer" value="${score.answer}" style="width: 60px;"></td>
									<td><input name="sectionscore[${st.index}].listening" value="${score.listening}" style="width: 60px;"></td>
									<td><input name="sectionscore[${st.index}].attendance" value="${score.attendance}" style="width: 60px;"></td>
									<td><input name="sectionscore[${st.index}].homework" value="${score.homework}" style="width: 60px;"></td>
									<td><input name="sectionscore[${st.index}].experiment" value="${score.experiment}" style="width: 60px;"></td>
									<td><input name="sectionscore[${st.index}].reviewandpreview" value="${score.reviewandpreview}" style="width: 60px;"></td>
								</tr>
							</c:forEach>
						</table>
						<button type="submit" class="btn btn-success">更新</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>