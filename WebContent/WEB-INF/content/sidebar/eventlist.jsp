<%@page import="com.cl.dao.Event"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cl.dao.Section"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SectionList</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
<link rel="stylesheet" href="public/css/main.css">
</head>
<body>
	<%
		int course_id = ((Integer)request.getSession().getAttribute("course_id")).intValue();
		int section_id = ((Integer)request.getSession().getAttribute("section_id")).intValue();
		String role = (String)request.getSession().getAttribute("role");
		String section_name = "";
		if (section_id != -1)
			section_name = Section.getSection(section_id).getSection_name();
		else
			section_name = "课程总结";
	%>
	<div class="container">
		<div class="row">
			<table class="table leftframe_content">
				<thead>
					<tr class="leftframe_title">
						<td align="center">
							<a href="main_getsectionlist?course_id=<%=course_id%>"><span class="glyphicon glyphicon-arrow-left"></span></a>
						</td>
						<td align="left"><%=section_name %></td>
					</tr>
				</thead>
				<tbody>
					<%
						ArrayList<Event> eventlist = (ArrayList<Event>)request.getSession().getAttribute("eventlist");
						if (eventlist != null) {
							for (int i = 0; i < eventlist.size(); i++) {
								Event event = eventlist.get(i);
					%>
						<tr>
							<td><%=i + 1 %></td>
							<td><a href="<%=role %>_geteventinfo?event_id=<%=event.getEvent_id()%>" target="rightframe"><%=event.getEvent_content() %></a></td>
						</tr>
					<%
							}
						}
					%>
				</tbody>
			</table>
			<%if (role.equals("teacher")) {%>
				<table class="table leftframe_content">
					<thead>
						<tr><td align="center">功能列表</td></tr>
					</thead>
					<tbody>
						<tr><td align="center"><a href="teacher_manageevent" target="rightframe">事件管理</a></td></tr>
						<% if (section_id != -1) { %>
							<tr><td align="center"><a href="teacher_getweight" target="rightframe">更新权值</a></td></tr>
							<tr><td align="center"><a href="teacher_getscore" target="rightframe">评分</a></td></tr>
						<% } %>
						<tr><td align="center"><a href="teacher_gethomework" target="rightframe">作业管理</a></td></tr>
						<tr><td align="center"><a href="teacher_getsectionsummary" target="rightframe">查看总结报告</a></td></tr>
					</tbody>
				</table>
			<% } else if (role.equals("student")) { %>
				<table class="table leftframe_content">
					<thead>
						<tr><td align="center">功能列表</td></tr>
					</thead>
					<tbody>
						<tr><td align="center"><a href="student_getsectionsummary" target="rightframe">查看个人成绩</a></td></tr>
						<tr><td align="center"><a href="student_gethomework" target="rightframe">查看作业</a></td></tr>
					</tbody>
				</table>
			<% } %>
		</div>
	</div>
</body>
</html>