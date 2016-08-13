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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
			<%
				int section_id = ((Integer)request.getSession().getAttribute("section_id")).intValue();
				ArrayList<String> columns = (ArrayList<String>)request.getAttribute("sectioncolumns");
				String section_name = (String)request.getAttribute("section_name");
			%>
				<div class="row">
					<h2><%=section_name %></h2>
					<table class="table table-bordered table-hover"> 
						<tr>
							<% for (int i = 0; i < columns.size(); i++) {%>
								<td><%=columns.get(i) %></td>
							<% } %>
						</tr>
						<% 
							if (section_id > 0) {
								ArrayList<SectionScore> scorearray = (ArrayList<SectionScore>)request.getAttribute("scorearray");
								for (int i = 0; i < scorearray.size(); i++) {
									SectionScore sectionscore = scorearray.get(i);
									double listening = sectionscore.getListening();
									double answer = sectionscore.getAnswer();
									double attendance = sectionscore.getAttendance();
									double homework = sectionscore.getHomework();
									double experiment = sectionscore.getExperiment();
									double reviewandpreview = sectionscore.getReviewandpreview();
									double sum = sectionscore.getSum();
						%>
								<tr>
									<td><%=listening %></td>
									<td><%=answer %></td>
									<td><%=attendance %></td>
									<td><%=homework %></td>
									<td><%=experiment %></td>
									<td><%=reviewandpreview %></td>
									<td><%=sum%></td>
								</tr>
						<%
								}
							} else {
								ArrayList<CourseScore> scorearray = (ArrayList<CourseScore>)request.getAttribute("scorearray");
								for (int i = 0; i < scorearray.size(); i++) {
									ArrayList<SectionScore> studentscore = scorearray.get(i).getSectionscore();
									double sum = new BigDecimal(scorearray.get(i).getAverage()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						%>
							<tr>
								<%
									for (int j = 0; j < studentscore.size(); j++) {
										double score = new BigDecimal(studentscore.get(j).getSum()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
								%>
									<td><%=score %></td>
								<%
									}
									for (int j = 0; j < columns.size() - 1 - studentscore.size(); j++) {
								%>
									<td>-1</td>
								<% } %>
								<% if (columns.size() - 1 > studentscore.size()) { %>
								<td>-1</td>
								<% } else { %>
								<td><%=sum %></td>
								<% } %>
							</tr>
						<%
								}
							}
						%>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>