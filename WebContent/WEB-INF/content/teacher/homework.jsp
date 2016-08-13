<%@page import="com.cl.dao.Section"%>
<%@page import="com.cl.util.DateFormator"%>
<%@page import="com.cl.dao.Homework"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homework</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
<link rel="stylesheet" href="public/css/main.css">  
</head>
<body background="public/img/leftframe_content.gif">
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
					<h2>作业列表</h2>
					<%
						ArrayList<Homework> hwlist = (ArrayList<Homework>)request.getAttribute("homeworklist");
						int section_id = -1;
						for (int i = 0; i < hwlist.size(); i++) {
							Homework hw = hwlist.get(i);
							if (hw.getSection_id() != section_id) {
								section_id = hw.getSection_id();
					%>
						<h4>章节：<%=Section.getSectionBySectionId(section_id).getSection_name() %></h4>
					<% } %>
						<div class="panel-group" id="homework<%=hw.getHomework_id() %>" role="tablist" aria-multiselectable="true">
							<div class="panel panel-default">
								<div class="panel-heading" role="tab" id="headinghomework<%=hw.getHomework_id() %>">
									<h4 class="panel-title">
										<a role="button" data-toggle="collapse" data-parent="#homework<%=hw.getHomework_id() %>" 
											href="#collapsehomework<%=hw.getHomework_id() %>" aria-expanded="true" 
											aria-controls="collapsehomework<%=hw.getHomework_id() %>">
											<%=hw.getHomework_title() %>
										</a>
										开始时间:<%=DateFormator.getDateCalendarToString(hw.getHomework_starttime()) %>
										结束时间:<%=DateFormator.getDateCalendarToString(hw.getHomework_endtime()) %>
										<a href="teacher_deletehomework?homework_id=<%=hw.getHomework_id()%>">
											<span class="glyphicon glyphicon-remove"></span>
										</a>
									</h4>
								</div>
								<div id="collapsehomework<%=hw.getHomework_id() %>" class="panel-collapse collapse" role="tabpanel" 
									aria-labelledby="headinghomework<%=hw.getHomework_id() %>">
									<div class="panel-body">
										<h5>作业内容</h5>
										<div class="well">
											<p><%=hw.getHomework_content() %></p>
										</div>
										<% if (hw.getHomework_accessory() != null) { %>
										<h5>附件下载</h5>
										<p><%=hw.getHomework_accessory() %>
											<a href="main_homeworkdownload?filename=<%=hw.getHomework_accessory()%>">
												<span class="glyphicon glyphicon-download"></span>
											</a>
											<a href="teacher_deletehomeworkaccessory?homework_id=<%=hw.getHomework_id() %>">
												<span class="glyphicon glyphicon-remove"></span>
											</a>
										</p>
										<% } else { %>
											<form action="teacher_updatehomeworkaccessory?homework_id=<%=hw.getHomework_id() %>"
												enctype="multipart/form-data" method="post">
												<div class="form-group">
													<label for="updateHomeworkAccessory">提交附件</label>
													<s:file name="upload" label="Select File" id="updateHomeworkAccessory"></s:file>
												</div>
												<button class="btn btn-success btn-sm" type="submit">提交</button>
											</form>
										<% } %>
										<hr>
										<a class="btn btn-sm btn-primary" href="teacher_getstudenthomeworklist?homework_id=<%=hw.getHomework_id()%>">批改作业</a>
									</div>
								</div>
							</div>
						</div>
					<% } %>
				</div>
				<div class="row">
					<h2>添加作业</h2>
					<% ArrayList<Section> sectionlist = (ArrayList<Section>)request.getAttribute("sectionlist"); %>
					<form action="teacher_addhomework" enctype="multipart/form-data" method="post">
						<div class="form-group">
							<label for="inputHomeworkTitle">作业标题</label>
							<input type="text" name="homework.homework_title" class="form-control" id="inputHomeworkTitle">
						</div>
						<div class="form-group">
							<label for="inputHomeworkSection">章节选择</label>
							<select name="homework.section_id" class="form-control" id="inputHomeworkSection">
								<% for (int i = 0; i < sectionlist.size(); i++) { %>
								<option value="<%=sectionlist.get(i).getSection_id()%>"><%=sectionlist.get(i).getSection_name() %></option>
								<% } %>
							</select>
						</div>
						<div class="form-group">
							<label for="inputHomeworkContent">作业内容</label>
							<textarea rows="3" name="homework.homework_content" class="form-control" id="inputHomeworkContent"></textarea>
						</div>
						<div class="form-group">
							<label for="inputHomeworkStartTime">开始时间</label>
							<input type="text" name="homework.homework_starttime" onclick="setday(this)" class="form-control" id="inputHomeworkStartTime"/>
						</div>
						<div class="form-group">
							<label for="inputHomeworkEndTime">结束时间</label>
							<input type="text" name="homework.homework_endtime" onclick="setday(this)" class="form-control" id="inputHomeworkEndTime"/>
						</div>
						<div class="form-group">
							<label for="inputHomeworkAccessory">提交附件</label>
							<s:file name="upload" label="Select File" id="inputHomeworkAccessory"></s:file>
						</div>
						<button class="btn btn-success btn-sm" type="submit">提交</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="public/bower_components/jquery/dist/jquery.js"></script>
	<script type="text/javascript" src="public/bower_components/bootstrap/js/collapse.js"></script>
	<script src="public/js/calendar.js"></script>
</body>
</html>