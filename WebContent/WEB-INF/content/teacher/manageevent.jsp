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
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
</head>
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
									<td><%=DateFormator.getDateCalendarToString(e.getStarttime()) %></td>
									<td><%=DateFormator.getDateCalendarToString(e.getEndtime()) %></td>
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
						<table class="table">
							<thead>
								<tr>
									<td>章节名称</td>
									<td>事件名称</td>
									<td>事件类型</td>
									<td>事件截止时间</td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><%=section_name %></td>
									<td><input name="event.event_content" type="text"></td>
									<td><input name="event.event_type" type="text"></td>
									<!-- <td><input name="event.endtime" onclick="setday(this)" maxlength="60"></td> -->
									<td><input type="datetime-local" name="event.endtime" maxlength="60"></td>
								</tr>
							</tbody>
						</table>
						<button type="submit" class="btn btn-success">发布</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>