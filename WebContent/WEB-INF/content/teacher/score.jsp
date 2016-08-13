<%@page import="com.cl.dao.CourseScore"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.cl.dao.Student"%>
<%@page import="com.cl.dao.SectionScore"%>
<%@page import="com.cl.dao.Section"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
			<div class="col-md-3">
				<s:action name="sidebar_tea_eventlist" executeResult="true"></s:action>
			</div>
			<div class="col-md-8 col-md-offset-1">
				<%
					int section_id = ((Integer)request.getSession().getAttribute("section_id")).intValue();
					ArrayList<String> columns = (ArrayList<String>)request.getAttribute("sectioncolumns");
					ArrayList<SectionScore> scorearray = (ArrayList<SectionScore>)request.getAttribute("scorearray");
					String section_name = (String)request.getAttribute("section_name");
				%>
				<div class="row">
					<h2><%=section_name %></h2>
					<form action="teacher_updatescore" method="post">
						<table class="table table-bordered table-hover"> 
							<tr>
								<td>编号</td>
								<% for (int i = 0; i < columns.size(); i++) {%>
									<td><%=columns.get(i) %></td>
								<% } %>
							</tr>
							<% 
								for (int i = 0; i < scorearray.size(); i++) {
									SectionScore sectionscore = scorearray.get(i);
									Student student = Student.getStudentByStudentId(sectionscore.getStudent_id());
									double listening = new BigDecimal(sectionscore.getListening()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
									double answer = new BigDecimal(sectionscore.getAnswer()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
									double attendance = new BigDecimal(sectionscore.getAttendance()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
									double homework = new BigDecimal(sectionscore.getHomework()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
									double experiment = new BigDecimal(sectionscore.getExperiment()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
									double reviewandpreview = new BigDecimal(sectionscore.getReviewandpreview()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
							%>
								<tr>
									<td><input name="sectionscore[<%=i %>].student_id" value="<%=sectionscore.getStudent_id()%>" 
										style="border: 0px; background-color: transparent; width: 10px;">
									</td>
									<td><%=student.getLoginname() %></td>
									<td><%=student.getName() %></td>
									<td><input name="sectionscore[<%=i %>].listening" value="<%=listening%>" style="width: 60px;"></td>
									<td><input name="sectionscore[<%=i %>].answer" value="<%=answer%>" style="width: 60px;"></td>
									<td><input name="sectionscore[<%=i %>].attendance" value="<%=attendance%>" style="width: 60px;"></td>
									<td><input name="sectionscore[<%=i %>].homework" value="<%=homework%>" style="width: 60px;"></td>
									<td><input name="sectionscore[<%=i %>].experiment" value="<%=experiment%>" style="width: 60px;"></td>
									<td><input name="sectionscore[<%=i %>].reviewandpreview" value="<%=reviewandpreview%>" style="width: 60px;"></td>
								</tr>
							<%
								}
							%>
						</table>
						<button type="submit" class="btn btn-success">更新</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>