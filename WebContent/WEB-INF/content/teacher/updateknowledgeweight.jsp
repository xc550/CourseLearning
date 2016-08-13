<%@page import="java.math.BigDecimal"%>
<%@page import="com.cl.dao.KnowledgeWeight"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cl.dao.Section"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UpdateWeight</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
<link rel="stylesheet" href="public/css/main.css">
</head>
<body background="public/img/leftframe_content.gif">
	<div class="container">
		<div class="row">
			<s:action name="sidebar_topframe" executeResult="true"></s:action>
		</div>
		<div class="row">
			<div class="col-md-3">
				<s:action name="sidebar_tea_eventlist" executeResult="true"></s:action>
			</div>
			<div class="col-md-8 col-md-offset-1">
				<!-- 分2个页面来写是否更好：更新章节权值和更新知识点权值 -->
				<div class="row">
					<%
						int section_id = ((Integer)request.getAttribute("section_id")).intValue();
						ArrayList<String> columns = (ArrayList<String>) request.getAttribute("columns");
						KnowledgeWeight kw = (KnowledgeWeight)request.getAttribute("weightlist");
					%>
					<form action="teacher_updateknowledgeweight" method="post">
						<table class="table">
							<thead>
								<tr>
									<% for (int i = 0; i < columns.size() - 1; i++) { %>
										<td><%=columns.get(i) %></td>
									<% } %>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input name="knowledgeweight.listening_weight" value="<%=new BigDecimal(kw.getListening_weight()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()%>" type="number" min=0 max=1 step=0.01></td>
									<td><input name="knowledgeweight.answer_weight" value="<%=new BigDecimal(kw.getAnswer_weight()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()%>" type="number" min=0 max=1 step=0.01></td>
									<td><input name="knowledgeweight.attendance_weight" value="<%=new BigDecimal(kw.getAttendance_weight()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()%>" type="number" min=0 max=1 step=0.01></td>
									<td><input name="knowledgeweight.homework_weight" value="<%=new BigDecimal(kw.getHomework_weight()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()%>" type="number" min=0 max=1 step=0.01></td>
									<td><input name="knowledgeweight.experiment_weight" value="<%=new BigDecimal(kw.getExperiment_weight()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()%>" type="number" min=0 max=1 step=0.01></td>
									<td><input name="knowledgeweight.reviewandpreview_weight" value="<%=new BigDecimal(kw.getReviewandpreview_weight()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()%>" type="number" min=0 max=1 step=0.01></td>
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