<%@page import="com.cl.dao.Section"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ManageSection</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<s:action name="sidebar_topframe" executeResult="true"></s:action>
		</div>
		<div class="row">
			<div class="col-md-3">
				<s:action name="sidebar_tea_sectionlist" executeResult="true"></s:action>
			</div>
			<div class="col-md-8 col-md-offset-1">
				<div class="row">
					<% ArrayList<Section> sectionlist = (ArrayList<Section>)request.getAttribute("sectionlist"); %>
					<h2>章节列表</h2>
					<ul class="list-group">
						<%
							int i = 0, j = 0;
							for (i = 0; i < sectionlist.size(); i++) {
						%>
						<li class="list-group-item">
							<div class="row">
								<div class="col-md-4">
									<%=sectionlist.get(i).getSection_name() %>
									<a href="teacher_deletesection?section_id=<%=sectionlist.get(i).getSection_id() %>">
									<span class="glyphicon glyphicon-remove"></span>
									</a>
								</div>
							</div>
						</li>
						 <% } %>
					</ul>
				</div>
				<div class="row">
					<h2>添加章节</h2>
					<form class="form-inline" action="teacher_addsection" method="post">
						<div class="form-group">
							<label class="sr-only" for="inputSectionName">章节名称</label>
							<input type="text" style="width: 150px" name="section.section_name" class="form-control" id="inputSectionName" placeholder="章节名称">
						</div>
						<button type="submit" class="btn btn-default">提交</button>
					</form>
				</div>
				<div class="row">
					<h2>数据导入</h2>
				</div>
			</div>
		</div>
	</div>
</body>
</html>