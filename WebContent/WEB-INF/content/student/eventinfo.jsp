<%@page import="com.cl.dao.Teacher"%>
<%@page import="com.cl.util.DateFormator"%>
<%@page import="com.cl.dao.Student"%>
<%@page import="com.cl.dao.Event"%>
<%@page import="com.cl.dao.BBS"%>
<%@page import="com.cl.dao.LearningStatus"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EventInfo</title>
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
				<s:action name="sidebar_stu_eventlist" executeResult="true"></s:action>
			</div>
			<%
				int event_id = ((Integer)request.getSession().getAttribute("event_id")).intValue();
				LearningStatus ls = (LearningStatus)request.getAttribute("learningstatus");
				ArrayList<BBS> bbs = (ArrayList<BBS>)request.getAttribute("bbs");
				ArrayList<String> columns = (ArrayList<String>)request.getAttribute("learningstatuscolumns");
				Event eve = ((Event)request.getAttribute("event"));
			%>
			<div class="col-md-8 col-md-offset-1">
				<div class="row">
					<h2>
						<%=eve.getEvent_content() %>
						<small>
							<%=eve.getEvent_type() %>&nbsp;
							<%=DateFormator.getDateCalendarToString(eve.getStarttime()) %>&nbsp;~&nbsp;
							<%=DateFormator.getDateCalendarToString(eve.getEndtime()) %>
						</small>
					</h2>
					<form action="student_submitlearningstatus" method="post">
						<table class="table table-bordered table-condensed">
							<thead>
								<tr>
									<% for (int i = 0; i < columns.size(); i++) { %>
										<td><%=columns.get(i) %></td>
									<% } %>
								</tr>
							</thead>
							<tbody>
								<% if (ls != null) { %>
									<tr>
										<td><%=LearningStatus.getClassTimeString(ls.getClasstime()) %></td>
										<td><%=LearningStatus.getClassString(ls.getInclass()) %></td>
										<td><%=LearningStatus.getClassString(ls.getOutclass()) %></td>
										<td><%=ls.getMethod() %></td>
									</tr>
								<%
									} else {
								%>
									<tr>
										<td>
											<select class="form-control" name="learningstauts.classtime">
											<%
												String[] res = LearningStatus.getClasstimetostring();
												for (int i = 0; i < res.length; i++) {
											%>
												<option value="<%=i %>"><%=res[i] %></option>
											<% } %>
											</select>
										</td>
										<td>
											<select class="form-control" name="learningstatus.inclass">
											<%
												String[] rs = LearningStatus.getClasstostring();
												for (int i = 0; i < rs.length; i++) {
											%>
												<option value="<%=i %>"><%=rs[i] %></option>
											<% } %>
											</select>
										</td>
										<td>
											<select class="form-control" name="learningstatus.outclass">
											<%
												for (int i = 0; i < rs.length; i++) {
											%>
												<option value="<%=i %>"><%=rs[i] %></option>
											<% } %>
											</select>
										</td>
										<td><input name="learningstatus.method"></td>
									</tr>
								<%
									}
								%>
							</tbody>
						</table>
						<% if (ls == null) { %>
						<button type="submit">提交</button>
						<% } %>
					</form>
				</div>
				
				<div class="row">
					<h2>论坛</h2>
					<%
						for (int i = 0; i < bbs.size(); i++) {
							BBS b = bbs.get(i);
							Student stu = null;
							Teacher te = null;
							if (b.getStudent_id() != -1)
								stu = Student.getStudentById(b.getStudent_id());
							if (b.getTeacher_id() != -1)
								te = Teacher.getTeacherById(b.getTeacher_id());
					%>
					<div class="row">
						<table class="table">
							<tbody>
								<tr>
									<td width="200px">
										<div class="row">
											<div class="col-xs-10 col-md-5">
												<a href="#" class="thumbnail">
													<img alt="Head" src="public/userheads/head.jpg">
												</a>
											</div>
										</div>
									</td>
									<td>
										<div class="row bbs_head">
											<strong>
												<em><%=i + 1 %></em>
												<sup>#</sup>
											</strong>
											<p>&nbsp;
											<% if (b.getStudent_id() == bbs.get(0).getStudent_id()
													&& b.getTeacher_id() == bbs.get(0).getTeacher_id()) { %>
												<strong>楼主:</strong>
											<% } else if (b.getTeacher_id() != -1) { %>
												<strong>老师：</strong>
											<% } %>
											<% if (stu != null) { %>
												<%=stu.getName() %>
											<% } else if (te != null) { %>
												<%=te.getName() %>
											<% } %>
											发表于 
											<%=DateFormator.getDateCalendarToString(b.getBbs_date()) %>
											</p>
											<a type="button" onclick="changeModal(<%=b.getBbs_id() %>);" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#studentModal">回复</a>
										</div>
										<div class="row">
											<%
												if (b.getReply_id() != -1) {
													BBS t = BBS.getBBS(b.getReply_id());
											%>
												<blockquote>
													<p>
													<u>
													<% if (t.getStudent_id() != -1) { %>
														<%=Student.getStudentById(t.getStudent_id()).getName() %>
													<% } else if (t.getTeacher_id() != -1) { %>
														<%=Teacher.getTeacherById(t.getTeacher_id()).getName() %>
													<% } %>
													发表于
													<%=DateFormator.getDateCalendarToString(t.getBbs_date()) %>
													</u>
													<br>
													<%=t.getBbs_content() %>
													</p>
												</blockquote>
												
											<%
												}
											%>
											<br>
											<p><%=b.getBbs_content() %></p>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<% } %>
					<!-- 发帖框 -->
					<div class="row">
						<table class="table">
							<tbody>
								<tr>
									<td width="200px">
										<div class="row">
											<div class="col-xs-10 col-md-5">
												<a href="#" class="thumbnail">
													<img alt="Head" src="public/userheads/head.jpg">
												</a>
											</div>
										</div>
									</td>
									<td>
										<div class="row">
											<form action="student_submitbbs?event_id=<%=event_id %>&reply_id=-1" method="post">
												<textarea name="bbs.bbs_content" class="form-control" rows="3"></textarea>
												<button class="btn btn-success btn-sm" type="submit">提交</button>
											</form>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div><!-- submit bbs div close -->
				</div><!-- bbs div close -->
			</div><!-- right content div close -->
		</div><!-- content div close -->
	</div><!-- container close -->
	<!-- Reply Modal -->
	<div class="modal fade bs-example-modal-sm" id="studentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
		<div class="modal-content">
		    <div class="modal-header">
		    	<h2>回复框</h2>
		    </div>
		    <form id="replyform" action="student_submitbbs?reply_id=-1" method="post">
		    	<div class="modal-body">
					<textarea name="bbs.bbs_content" class="form-control" rows="3"></textarea>
		    	</div>
		    	<div class="modal-footer">
					<button id="submitbtn" class="btn btn-success btn-sm" type="submit">提交</button>
				</div>
			</form>
		</div>
	  </div>
	</div>
	
	<script type="text/javascript" src="public/bower_components/jquery/dist/jquery.js"></script>
	<script type="text/javascript">
	function changeModal(bbsid) {
		document.getElementById("replyform").action="student_submitbbs?reply_id=" + bbsid;
	}
	</script>
	<script type="text/javascript" src="public/js/main.js"></script>
	<script type="text/javascript" src="public/bower_components/bootstrap/dist/js/bootstrap.js"></script>
</body>
</html>