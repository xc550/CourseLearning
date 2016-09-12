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
				<s:action name="sidebar_tea_eventlist" executeResult="true"></s:action>
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
							if (section_id != 0) {
								ArrayList<SectionScore> scorearray = (ArrayList<SectionScore>)request.getAttribute("scorearray");
								for (int i = 0; i < scorearray.size(); i++) {
									SectionScore sectionscore = scorearray.get(i);
									Student student = Student.getStudentByStudentId(sectionscore.getStudent_id());
									double listening = sectionscore.getListening();
									double answer = sectionscore.getAnswer();
									double attendance = sectionscore.getAttendance();
									double homework = sectionscore.getHomework();
									double experiment = sectionscore.getExperiment();
									double reviewandpreview = sectionscore.getReviewandpreview();
									double sum = sectionscore.getReviewandpreview();
						%>
							<tr>
								<td><%=student.getName() %></td>
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
									Student student = Student.getStudentByStudentId(scorearray.get(i).getStudent_id());
									double sum = scorearray.get(i).getAverage();
						%>
							<tr>
								<td><%=student.getName() %></td>
								<%
									for (int j = 0; j < studentscore.size(); j++) {
										double score = studentscore.get(j).getSum();
								%>
									<td><%=score %></td>
								<%
									}
								%>
								<td><%=sum %></td>
							</tr>
						<%
								}
							}
						%>
					</table>
				</div>
				
				<div class="row">
					<h2>总结</h2>
					<%
						if (section_id != 0) {
							double average = ((BigDecimal)request.getAttribute("average")).doubleValue();
							double max = ((BigDecimal)request.getAttribute("max")).doubleValue();
							double min = ((BigDecimal)request.getAttribute("min")).doubleValue();
							ArrayList<String> maxscore = (ArrayList<String>)request.getAttribute("maxscore");
							ArrayList<String> minscore = (ArrayList<String>)request.getAttribute("minscore");
							ArrayList<String> higher = (ArrayList<String>)request.getAttribute("higher");
							ArrayList<String> lower = (ArrayList<String>)request.getAttribute("lower");
							ArrayList<String> cheating = (ArrayList<String>)request.getAttribute("cheating");
					%>
						<p><strong>平均分：</strong><%=average %></p>
						<p><strong>最高分：</strong></p>
						<p>
						<%=max %>：
						<% for (int i = 0; i < maxscore.size(); i++) { %>
							<%=maxscore.get(i) %>
						<% } %>
						</p>
						<p><strong>最低分：</strong></p>
						<p>
						<%=min %>：
						<% for (int i = 0; i < minscore.size(); i++) { %>
							<%=minscore.get(i)%>
						<% } %>
						</p>
						<p><strong>高于平均分：</strong></p>
						<% if (higher.size() > 0) { %>
							<p>
							<% for (int i = 0; i < higher.size(); i++) { %>
								<%=higher.get(i)%>
							<% } %>
							</p>
						<% } else { %>
							<p>无</p>
						<% } %>
						<p><strong>低于平均分：</strong></p>
						<% if (lower.size() > 0) { %>
							<p>
							<% for (int i = 0; i < lower.size(); i++) { %>
								<%=lower.get(i)%>
							<% } %>
							</p>
						<% } else { %>
							<p>无</p>
						<% } %>
						<p><strong>怀疑作弊的：</strong></p>
						<% if (cheating.size() > 0) { %>
							<p>
							<% for (int i = 0; i < cheating.size(); i++) { %>
								<%=cheating.get(i)%>
							<% } %>
							</p>
						<% } else { %>
							<p>无</p>
						<% } %>
					<%
						} else {
							double average = ((BigDecimal)request.getAttribute("average")).doubleValue();
							double max = ((BigDecimal)request.getAttribute("max")).doubleValue();
							double min = ((BigDecimal)request.getAttribute("min")).doubleValue();
							ArrayList<String> maxscore = (ArrayList<String>)request.getAttribute("maxscore");
							ArrayList<String> minscore = (ArrayList<String>)request.getAttribute("minscore");
							ArrayList<String> higher = (ArrayList<String>)request.getAttribute("higher");
							ArrayList<String> lower = (ArrayList<String>)request.getAttribute("lower");
							ArrayList<String> cheating = (ArrayList<String>)request.getAttribute("cheating");
							ArrayList<String> coreprob = (ArrayList<String>)request.getAttribute("coreproblem");
					%>
						<p><strong>平均分：</strong><%=average %></p>
						<p><strong>最高分：</strong></p>
						<p>
						<%=max %>:
						<% for (int i = 0; i < maxscore.size(); i++) { %>
							<%=maxscore.get(i) %>
						<% } %>
						</p>
						<p><strong>最低分：</strong></p>
						<p>
						<%=min %>:
						<% for (int i = 0; i < minscore.size(); i++) { %>
							<%=minscore.get(i)%>
						<% } %>
						</p>
						<p><strong>高于平均分：</strong></p>
						<% if (higher.size() > 0) { %>
							<p>
							<% for (int i = 0; i < higher.size(); i++) { %>
								<%=higher.get(i)%>
							<% } %>
							</p>
						<% } else { %>
							<p>无</p>
						<% } %>
						<p><strong>低于平均分及其问题单元：</strong></p>
						<%
							if (lower.size() > 0) {
								boolean isNumb = true;
								for (int i = 0, size = 0; i < lower.size(); i++) { 
									if (isNumb) {
										size = new Integer(lower.get(i++)).intValue();
										String student_name = lower.get(i);
										isNumb = false;
						%>
							<p><%=student_name%>：</p> 
							<%
									}
									else {
							%>
							<p>
							<%
										for (int j = 0; j < size; j++, i++) {
											String prob_name = lower.get(i);
							%>
								<%=prob_name%>
							<%
										}
							%>
							</p>
						<%
									}
								}
							} else {
						%>
							<p>无</p>
						<% } %>
						<p><strong>怀疑作弊最多的：</strong></p>
						<%
							if (cheating.size() > 0) {
								int times = new Integer(cheating.get(0)).intValue();
						%>
							<p>以下同学均怀疑作弊<%=times %>次</p>
						<%
								for (int i = 1; i < cheating.size(); i++) {
									String student_name = cheating.get(i);
						%>
							<p><%=student_name %></p>
						<% 
								}
							} else {
						%>
							<p>无</p>
						<% } %>
						<p><strong>难点：</strong></p>
						<%
							if (coreprob.size() > 0) {
								for (int i = 0; i < coreprob.size(); i++) {
									String coreprob_name = coreprob.get(i);
						%>
							<p><%=coreprob_name %></p>
						<% 
								}
							} else {
						%>
							<p>无</p>
					<%
							}
						}
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>