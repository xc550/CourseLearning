<%@page import="com.cl.dao.Course"%>
<%@page import="com.cl.dao.Section"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SectionList</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<%
					ArrayList<Section> res = (ArrayList<Section>)request.getAttribute("sectionlist");
					String course_name = (String)request.getAttribute("course_name");
				%>
				<table class="table">
					<thead>
						<tr>
							<td align="center">
								<a href="student_welcome.action"><span class="glyphicon glyphicon-arrow-left"></span></a>
							</td>
							<td align="left"><%=course_name %></td>
						</tr>
					</thead>
					<tbody>
						<% for (int i = 0; i < res.size(); i++) { %>
							<tr>
								<td><%=i + 1%></td>
								<td><a href="student_eventwelcome?section_id=<%=res.get(i).getSection_id()%>"><%=res.get(i).getSection_name() %></a></td>
							</tr>
						<%
							}
						%>
						<tr>
							<td><%=res.size() + 1 %></td>
							<td><a href="student_eventwelcome?section_id=0">课程总结</a></td>
						</tr>
					</tbody>
				</table>
				<table class="table">
					<thead>
						<tr><td align="center">功能列表</td></tr>
					</thead>
					<tbody>
						<tr><td align="center"><a href="student_getsectionsummary">查看个人成绩</a></td></tr>
						<tr><td align="center"><a href="student_gethomework">查看作业</a></td></tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>