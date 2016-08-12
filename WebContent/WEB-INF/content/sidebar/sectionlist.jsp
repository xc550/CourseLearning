<%@page import="com.cl.dao.Course"%>
<%@page import="com.cl.dao.Section"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Course</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
<link rel="stylesheet" href="public/css/main.css">
</head>
<body>
	<%
		ArrayList<Section> res = (ArrayList<Section>)request.getSession().getAttribute("sectionlist");
		int course_id = ((Integer)request.getSession().getAttribute("course_id")).intValue();
		String course_name = Course.getCourse(course_id).getCourse_name();
		String role = (String)request.getSession().getAttribute("role");
	%>
	<div class="container">
		<div class="row">
			<table class="table leftframe_content">
				<thead>
					<tr class="leftframe_title">
						<td align="center">
							<a href="main_courselist"><span class="glyphicon glyphicon-arrow-left"></span></a>
						</td>
						<td align="left"><%=course_name %></td>
					</tr>
				</thead>
				<tbody>
					<%
						for (int i = 0; i < res.size(); i++) {
					%>
						<tr class="leftframe_content">
							<td><%=i + 1%></td>
							<td><a href="main_geteventlist?section_id=<%=res.get(i).getSection_id()%>"><%=res.get(i).getSection_name() %></a></td>
						</tr>
					<%
						}
					%>
					<tr class="leftframe_content">
						<td></td>
						<td><a href="main_geteventlist?section_id=-1">课程总结</a></td>
					</tr>
				</tbody>
			</table>
			<% if (role.equals("teacher")) { %>
				<table class="table leftframe_content">
					<thead>
						<tr><td align="center">功能列表</td></tr>
					</thead>
					<tbody>
						<tr><td align="center"><a href="teacher_managesection" target="rightframe">章节管理</a></td></tr>
						<tr><td align="center"><a href="teacher_getweight" target="rightframe">更新权值</a></td></tr>
					</tbody>
				</table>
			<% } %>
		</div>
	</div>
</body>
</html>