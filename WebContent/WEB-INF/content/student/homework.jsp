<%@page import="com.cl.dao.Section"%>
<%@page import="com.cl.dao.HomeworkStudent"%>
<%@page import="com.cl.dao.Student"%>
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
</head>
<body>
	<div class="container">
		<div class="row">
			<s:action name="sidebar_topframe" executeResult="true"></s:action>
		</div>
		<div class="row">
			<div class="col-md-3">
				<s:action name="sidebar_stu_sectionlist" executeResult="true"></s:action>
			</div>
			<div class="col-md-8 col-md-offset-1">
				<h2>作业列表</h2>
				<%
					ArrayList<Homework> hwlist = (ArrayList<Homework>)request.getAttribute("homeworklist");
					int section_id = -1;
					int student_id = ((Integer)request.getAttribute("user_id")).intValue();
					for (int i = 0; i < hwlist.size(); i++) {
						Homework hw = hwlist.get(i);
						if (hw.getSection_id() != section_id) {
							section_id = hw.getSection_id();
					%>
							<h4>章节：<%=Section.getSectionBySectionId(section_id).getSection_name() %></h4>
					<% } %>
					<div class="panel-group" id="homeworkstudent<%=hw.getHomework_id() %>" role="tablist" aria-multiselectable="true">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headinghomeworkstudent<%=hw.getHomework_id() %>">
								<h4 class="panel-title">
									<a role="button" data-toggle="collapse" data-parent="#homeworkstudent<%=hw.getHomework_id() %>" 
										href="#collapsehomeworkstudent<%=hw.getHomework_id() %>" aria-expanded="true" 
										aria-controls="collapsehomeworkstudent<%=hw.getHomework_id() %>">
										<%=hw.getHomework_title() %>
									</a>
									开始时间:<%=DateFormator.getDateCalendarToString(hw.getHomework_starttime()) %>
									结束时间:<%=DateFormator.getDateCalendarToString(hw.getHomework_endtime()) %>
									<%
										HomeworkStudent hws = HomeworkStudent.getHomeworkStudentByHomeworkIdAndStudentId(hw.getHomework_id(), student_id);
										if (hws != null) {
									%>
										已提交
									<% } else { %>
										未提交
									<% } %>
								</h4>
							</div>
							<div id="collapsehomeworkstudent<%=hw.getHomework_id() %>" class="panel-collapse collapse" role="tabpanel" 
								aria-labelledby="headinghomeworkstudent<%=hw.getHomework_id() %>">
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
									</p>
									<% } %>
									<% if (hws == null) { %>
										<button type="button" onclick="changeModal(<%=hw.getHomework_id() %>)" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#submithomework">
										提交作业
										</button>
									<% } else { %>
									<h5>作业下载</h5>
									<p><%=hws.getHomeworkstudent_accessory() %>
										<a href="main_homeworkdownload?filename=<%=hws.getHomeworkstudent_accessory()%>&homework_id=<%=hws.getHomework_id()%>">
											<span class="glyphicon glyphicon-download"></span>
										</a>
										<a href="student_deletehomework?homeworkstudent_id=<%=hws.getHomeworkstudent_id()%>">
											<span class="glyphicon glyphicon-remove"></span>
										</a>
									</p>
									<% } %>
								</div>
							</div>
						</div>
					</div>
				<% } %>
			</div><!-- end row -->
		</div>
	</div><!-- end container -->
	<!-- submit homework modal -->	
	<div class="modal fade" id="submithomework" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">提交作业</h4>
				</div>
				<div class="modal-body">
					<form action="student_submithomework" enctype="multipart/form-data" method="post" id="submithomeworkform">
						<div class="form-group">
							<label for="inputHomeworkComment">备注</label>
							<input type="text" name="homeworkcomment" class="form-control" id="inputHomeworkComment"/>
						</div>
						<div class="form-group">
							<label for="inputHomeworkAccessoryStu">作业</label>
							<s:file name="upload" label="Select File" id="inputHomeworkAccessoryStu"></s:file>
						</div>
						<button class="btn btn-success btn-sm" type="submit">提交</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="public/bower_components/jquery/jquery.js"></script>
	<script type="text/javascript" src="public/bower_components/bootstrap/js/collapse.js"></script>
	<script type="text/javascript" src="public/bower_components/bootstrap/dist/js/bootstrap.js"></script>
	<%-- <script src="public/js/calendar.js"></script> --%>
	<script type="text/javascript">
	function changeModal(homework_id) {
		document.getElementById("submithomeworkform").action="student_submithomework?homework_id=" + homework_id;
	}
	</script>
</body>
</html>