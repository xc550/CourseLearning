<%@page import="java.math.BigDecimal"%>
<%@page import="com.cl.dao.KnowledgeWeight"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cl.dao.Section"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UpdateWeight</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<s:action name="sidebar_topframe" executeResult="true"></s:action>
		</div>
		<div class="row">
			<div class="col-md-3">
				<s:action name="sidebar_tea_eventlist" executeResult="true"></s:action>
			</div>
			<div class="col-md-8 col-md-offset-1">
				<div class="row">
					<form action="teacher_updateknowledgeweight" method="post">
						<table class="table">
							<thead>
								<tr>
									<c:forEach items="${columns}" var="cl">
										<td>${cl}</td>
									</c:forEach>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input name="knowledgeweight.listening_weight" value="${knowledgeweight.listening_weight}" type="number" min=0 max=1 step=0.01></td>
									<td><input name="knowledgeweight.answer_weight" value="${knowledgeweight.answer_weight}" type="number" min=0 max=1 step=0.01></td>
									<td><input name="knowledgeweight.attendance_weight" value="${knowledgeweight.attendance_weight}" type="number" min=0 max=1 step=0.01></td>
									<td><input name="knowledgeweight.homework_weight" value="${knowledgeweight.homework_weight}" type="number" min=0 max=1 step=0.01></td>
									<td><input name="knowledgeweight.experiment_weight" value="${knowledgeweight.experiment_weight}" type="number" min=0 max=1 step=0.01></td>
									<td><input name="knowledgeweight.reviewandpreview_weight" value="${knowledgeweight.reviewandpreview_weight}" type="number" min=0 max=1 step=0.01></td>
								</tr>
							</tbody>
						</table>
						<button type="submit" class="btn btn-success">更新</button>
					</form>
				</div>
			</div>
		</div>		
	</div>
</body>
</html>