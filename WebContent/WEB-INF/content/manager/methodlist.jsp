<%@page import="com.cl.dao.Method"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MethodList</title>
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
				<div class="row">
					<h2>方法管理</h2>
					<%
						ArrayList<Method> methodlist = (ArrayList<Method>)request.getAttribute("methodlist");
						if (methodlist.size() != 0) {
					%>
					<table class="table">
						<thead>
							<tr>
								<td></td>
								<td>方法名</td>
								<td>方法下载</td>
								<td>方法删除</td>
							</tr>
						</thead>
						<tbody>
							<% for (int i = 0; i < methodlist.size(); i++) { %>
							<tr>
								<td><%=i + 1 %></td>
								<td><%=methodlist.get(i).getMethod_name() %></td>
								<td><a href="main_homeworkdownload?filename=<%=methodlist.get(i).getMehtod_path()%>">
											<span class="glyphicon glyphicon-download"></span>
										</a>
								</td>
								<td>
										<a href="manager_deletehomework?method_id=<%=methodlist.get(i).getMethod_id()%>">
											<span class="glyphicon glyphicon-remove"></span>
										</a>
								</td>
							</tr>
							<% } %>
						</tbody>
					</table>
					<% } else { %>
					<p>无方法！</p>
					<% } %>
				</div>
				<div class="row">
					<h2>添加方法</h2>
					<form class="form-inline" action="manager_addmethod" method="post">
						<div class="form-group">
							<label class="sr-only" for="inputMethodName">方法名</label>
							<input type="text" style="width: 150px" name="method_name" class="form-control" id="inputMethodName" placeholder="课程名称">
						</div>
						<div class="form-group">
							<label for="inputMethodPath">方法</label>
							<s:file name="upload" label="Select File" id="inputMethodPath"></s:file>
						</div>
						<button type="submit" class="btn btn-default">提交</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>