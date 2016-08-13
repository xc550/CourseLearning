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
								<td><%=student.getLoginname() %></td>
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
									double sum = new BigDecimal(scorearray.get(i).getAverage()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						%>
							<tr>
								<td><%=student.getLoginname() %></td>
								<td><%=student.getName() %></td>
								<%
									for (int j = 0; j < studentscore.size(); j++) {
										double score = new BigDecimal(studentscore.get(j).getSum()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
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
							double average = ((BigDecimal)request.getAttribute("average")).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
							ArrayList<Student> maxscore = (ArrayList<Student>)request.getAttribute("maxscore");
							ArrayList<Student> minscore = (ArrayList<Student>)request.getAttribute("minscore");
							ArrayList<Student> higher = (ArrayList<Student>)request.getAttribute("higher");
							ArrayList<Student> lower = (ArrayList<Student>)request.getAttribute("lower");
							ArrayList<Student> cheating = (ArrayList<Student>)request.getAttribute("cheating");
					%>
						<p><strong>平均分：</strong><%=average %></p>
						<p><strong>最高分：</strong></p>
						<p>
						<% for (int i = 0; i < maxscore.size(); i++) { %>
							<%=maxscore.get(i).getName() %>
						<% } %>
						</p>
						<p><strong>最低分：</strong></p>
						<p>
						<% for (int i = 0; i < minscore.size(); i++) { %>
							<%=minscore.get(i).getName() %>
						<% } %>
						</p>
						<p><strong>高于平均分：</strong></p>
						<p>
						<% for (int i = 0; i < higher.size(); i++) { %>
							<%=higher.get(i).getName() %>
						<% } %>
						</p>
						<p><strong>低于平均分：</strong></p>
						<p>
						<% for (int i = 0; i < lower.size(); i++) { %>
							<%=lower.get(i).getName() %>
						<% } %>
						</p>
						<p><strong>怀疑作弊的：</strong></p>
						<p>
						<% for (int i = 0; i < cheating.size(); i++) { %>
							<%=cheating.get(i).getName() %>
						<% } %>
						</p>
					<%
						} else {
							double average = ((BigDecimal)request.getAttribute("average")).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
							ArrayList<Student> maxscore = (ArrayList<Student>)request.getAttribute("maxscore");
							ArrayList<Student> minscore = (ArrayList<Student>)request.getAttribute("minscore");
							ArrayList<Student> higher = (ArrayList<Student>)request.getAttribute("higher");
							ArrayList<Integer> lower = (ArrayList<Integer>)request.getAttribute("lower");
							ArrayList<Integer> cheating = (ArrayList<Integer>)request.getAttribute("cheating");
							ArrayList<Section> coreprob = (ArrayList<Section>)request.getAttribute("coreproblem");
					%>
						<p><strong>平均分：</strong><%=average %></p>
						<p><strong>最高分：</strong></p>
						<p>
						<% for (int i = 0; i < maxscore.size(); i++) { %>
							<%=maxscore.get(i).getName() %>
						<% } %>
						</p>
						<p><strong>最低分：</strong></p>
						<p>
						<% for (int i = 0; i < minscore.size(); i++) { %>
							<%=minscore.get(i).getName() %>
						<% } %>
						</p>
						<p><strong>高于平均分：</strong></p>
						<p>
						<% for (int i = 0; i < higher.size(); i++) { %>
							<%=higher.get(i).getName() %>
						<% } %>
						</p>
						<p><strong>低于平均分及其问题单元：</strong></p>
						<p>
						<%
							for (int i = 0; i < lower.size(); i += 2) { 
								String student_name = Student.getStudentByStudentId(lower.get(i).intValue()).getName();
								String prob_name = Section.getSectionBySectionId(lower.get(i + 1).intValue()).getSection_name();
						%>
							<p><%=student_name %>: <%=prob_name %></p>
						<% } %>
						</p>
						<p><strong>怀疑作弊的：</strong></p>
						<p>
						<%
							for (int i = 0; i < cheating.size(); i += 2) {
								String student_name = Student.getStudentByStudentId(cheating.get(i).intValue()).getName();
								int times = cheating.get(i + 1).intValue();
						%>
							<p><%=student_name %>: <%=times %>次</p>
						<% } %>
						</p>
						<p><strong>难点：</strong></p>
						<p>
						<%
							for (int i = 0; i < coreprob.size(); i++) {
								String coreprob_name = coreprob.get(i).getSection_name();
						%>
							<p><%=coreprob_name %></p>
						<% } %>
						</p>
					<%
						}
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>