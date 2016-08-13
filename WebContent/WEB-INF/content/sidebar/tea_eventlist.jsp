<%@page import="com.cl.dao.Event"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cl.dao.Section"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EventList</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
			<%
				int course_id = ((Integer)request.getSession().getAttribute("course_id")).intValue();
				int section_id = ((Integer)request.getSession().getAttribute("section_id")).intValue();
				String section_name = ((String)request.getAttribute("section_name"));
			%>
				<table class="table">
					<thead>
						<tr>
							<td align="center">
								<a href="teacher_sectionwelcome?course_id=<%=course_id%>">
									<span class="glyphicon glyphicon-arrow-left"></span>
								</a>
							</td>
							<td align="left"><%=section_name %></td>
						</tr>
					</thead>
					<tbody>
						<%
							ArrayList<Event> eventlist = (ArrayList<Event>)request.getAttribute("eventlist");
							if (eventlist.size() != 0) {
								for (int i = 0; i < eventlist.size(); i++) {
									Event event = eventlist.get(i);
						%>
							<tr>
								<td><%=i + 1 %></td>
								<td>
									<a href="teacher_geteventinfo?event_id=<%=event.getEvent_id()%>"><%=event.getEvent_content() %></a>
								</td>
							</tr>
						<%
								}
							}
						%>
					</tbody>
				</table>
				<table class="table">
					<thead>
						<tr><td align="center">功能列表</td></tr>
					</thead>
					<tbody>
						<tr><td align="center"><a href="teacher_manageevent">事件管理</a></td></tr>
						<% if (section_id != 0) { %>
						<tr><td align="center"><a href="teacher_manageknowledgeweight">更新权值</a></td></tr>
						<tr><td align="center"><a href="teacher_getscore">评分</a></td></tr>
						<% } %>
						<tr><td align="center"><a href="teacher_getsectionsummary">查看总结报告</a></td></tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>