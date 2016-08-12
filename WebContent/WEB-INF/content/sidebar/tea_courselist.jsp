<%@page import="com.cl.dao.Course"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TeacherCourseList</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<table class="table">
					<thead>
						<!-- 未居中 -->
						<tr><th></th><th align="center">课程列表</th></tr>
					</thead>
					<tbody>
						<%
							ArrayList<Course> courselist = (ArrayList<Course>)request.getAttribute("courselist");
							if (courselist.size() == 0) {
						%>
						<tr><td>无课程</td></tr>
						<%
							} else {
								for (int i = 0; i < courselist.size(); i++) { %>
								<tr>
									<td><%=i + 1 %></td>
									<td><a href="teacher_sectionwelcome?course_id=<%=courselist.get(i).getCourse_id()%>"><%=courselist.get(i).getCourse_name() %></a></td>
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
						<tr><td align="center"><a href="teacher_getcourseselection">选课</a></td></tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>