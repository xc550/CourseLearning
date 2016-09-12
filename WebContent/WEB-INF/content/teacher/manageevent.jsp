<%@page import="com.cl.util.DateFormator"%>
<%@page import="com.cl.dao.Event"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cl.dao.Section"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AddEvent</title>
	<script type="text/javascript" src="public/bower_components/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="public/bower_components/moment/min/moment.min.js"></script>
	<script type="text/javascript" src="public/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="public/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
	<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.min.css" />
	<link rel="stylesheet" href="public/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css" />
	<script type="text/javascript">
	$(function () {	  
		$('#inputEventEndTime').datetimepicker({
			format: 'YYYY-MM-DD HH:mm:ss'
		});
	});
	</script></head>
<body>
	<div class="container">
		<div class="row">
			<s:action name="sidebar_topframe" executeResult="true"></s:action>
		</div>
		<div class="row">
			<div class="col-md-3">
				<s:action name="sidebar_tea_eventlist" executeResult="true"></s:action>
			</div>
			<div class="col-md-8 col-md-offset-1">
				<%
					int section_id = ((Integer)session.getAttribute("section_id")).intValue();
					String section_name = ((String)request.getAttribute("section_name"));
					ArrayList<Event> eventlist = (ArrayList<Event>)request.getAttribute("eventlist");
					if (eventlist.size() != 0) {
				%>
				<div class="row">
					<h2>事件列表</h2>
					<table class="table table-hover">
						<thead>
							<tr>
								<td>事件编号</td>
								<td>事件名称</td>
								<td>事件类型</td>
								<td>事件开始时间</td>
								<td>事件截止时间</td>
								<td>删除</td>
							</tr>
						</thead>
						<tbody>
							<%
								for (int i = 0; i < eventlist.size(); i++) {
									Event e = eventlist.get(i);
							%>
								<tr>
									<td><%=i + 1 %></td>
									<td><%=e.getEvent_content() %></td>
									<td><%=e.getEvent_type() %></td>
									<td><%=e.getStarttime() %></td>
									<td><%=e.getEndtime() %></td>
									<td>
										<a href="teacher_deleteevent?event_id=<%=e.getEvent_id()%>">
											<span class="glyphicon glyphicon-remove"></span>
										</a>
									</td>
								</tr>
							<% } %>
						</tbody>
					</table>
				</div>
				<% } %>
				<div class="row">
					<h2>添加事件</h2>
					<form action="teacher_addevent" method="post">
						<div class="row">
							<div class="col-md-7">
								<div class="form-group">
									<label for="inputSectionName">章节名称</label>
									<p><%=section_name %></p>
								</div>
							</div>
							<div class="col-md-7">
								<div class="form-group">
									<label for="inputEventContent">事件名称</label>
									<input type="text" name="event.event_content" class="form-control" id="inputEventContent">
								</div>
							</div>
							<div class="col-md-7">
								<div class="form-group">
									<label for="inputEventType">事件类型</label>
									<select name="event.event_type" class="form-control" id="inputEventType">
										<option value="听课">听课</option>
										<option value="讨论">讨论</option>
									</select>
								</div>
							</div>
							<div class="col-md-7">
								<div class="form-group">
									<label for="inputEventType">事件截止时间</label>
									<div id="inputEventEndTime" class="input-group date">
										<input type="text" class="form-control" name="event.endtime" />
										<span class="input-group-addon">
											<span class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<button type="submit" class="btn btn-success">发布</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>