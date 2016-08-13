<%@page import="com.cl.dao.Teacher"%>
<%@page import="com.cl.dao.CourseTeacher"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cl.dao.Course"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CourseList</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<s:action name="sidebar_topframe" executeResult="true"></s:action>
		</div>
		<div class="row">
			<div class="col-md-3">
				<s:action name="sidebar_man_functionlist" executeResult="true"></s:action>
			</div>
			<div class="col-md-8 col-md-offset-1">
				<%
					Course course = (Course)request.getAttribute("course");
					ArrayList<CourseTeacher> ct = (ArrayList<CourseTeacher>)request.getAttribute("courseteacher");
					ArrayList<Teacher> tl = (ArrayList<Teacher>)request.getAttribute("teacherlist"); 
				%>
				<h2><%=course.getCourse_name() %></h2><a href="manager_courselist">返回课程列表</a>
				<div class="row">
					<div class="col-md-8">
						<form action="manager_updatecourseteacher" method="post">
							<table class="table table-hover">
								<thead>
									<tr>
										<td>课堂号</td>
										<td>教师</td>
									</tr>
								</thead>
								<tbody>
								<%
									for (int i = 1, j = 0; i <= course.getCourse_classcapacity(); i++) {
								%>
									<tr>
										<td><input name="coursehead[<%=i - 1 %>].class_id" value="<%=i%>" style="border: 0px; background-color: transparent; width: 10px;"></td>
										<td>
											<select name="coursehead[<%=i - 1 %>].id">
											<%
												boolean exist = false;
												if (j < ct.size() && ct.get(j).getClass_id() == i) {
													Teacher te = Teacher.getTeacherByTeacherId(ct.get(j).getId());
													exist = true;
											%>
												<option value="<%=te.getId()%>"><%=te.getName() %></option>
											<%
													j++;
												} else {
											%>
												<option value="-1"></option>
											<%
												}
												for (int k = 0; k < tl.size(); k++) {
											%>
												<option value="<%=tl.get(k).getId()%>"><%=tl.get(k).getName() %></option>
											<%
												}
												if (exist) {
											%>
												<option value="-1"></option>
											<% } %>
											</select>
										</td>
									</tr>
								<%
									}
								%>
								</tbody>
							</table>
							<button class="btn btn-success" type="submit">提交</button>
						</form>								
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-2">
						<a href="manager_updatecourseclasscapacity?action=add">
							<button class="btn btn-sm btn-primary">添加课堂</button>
						</a>
					</div>
					<div class="col-md-2">
						<a href="manager_updatecourseclasscapacity?action=delete">
							<button class="btn btn-sm btn-danger">删除课堂</button>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>