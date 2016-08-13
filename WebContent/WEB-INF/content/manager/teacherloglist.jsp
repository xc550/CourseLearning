<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LogList</title>
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
					ArrayList<String> loglist = (ArrayList<String>)request.getAttribute("teacherloglist");
					if (loglist.size() > 0) {
						int nowcolumn = ((Integer)request.getAttribute("nowcolumn")).intValue();
						int each = ((Integer)request.getAttribute("each")).intValue();
				    	int totalcolumn = ((Integer)request.getAttribute("totalcolumn")).intValue();
				    	int precolumn = ((Integer)request.getAttribute("precolumn")).intValue();
				    	int nextcolumn = ((Integer)request.getAttribute("nextcolumn")).intValue();
				    	int pre = Math.max(nowcolumn - 1, 1);
					  	int next = Math.min(nowcolumn + 1, totalcolumn);	
				%>
					<div class="row">
						<h2>教师日志</h2>
						<table class="table">
							<thead>
								<tr>
									<td></td>
									<td>日志时间</td>
									<td>日志来源</td>
									<td>日志下载</td>
									<td>日志删除</td>
								</tr>
							</thead>
							<tbody>
								<%
									for (int i = (nowcolumn - 1) * each * 3, j = 0; i < loglist.size() && j < each; i += 3, j++) {
										String logtime = loglist.get(i);
										String teacher = loglist.get(i + 1);
										String logname = loglist.get(i + 2);
								%>
									<tr>
										<td><%=j + 1 %></td>
										<td><%=logtime %></td>
										<td><%=teacher %></td>
										<td><a href="main_logdownload?filename=<%=logname%>"><span class="glyphicon glyphicon-download-alt"></span></a></td>
										<td><a href="manager_deletelog?logname=<%=logname%>"><span class="glyphicon glyphicon-remove"></span></a></td>
									</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
					<div class="row">
						<div class="col-md-2 col-md-offset-5">
							<nav>
							  <ul class="pagination pagination-sm">
							  <%
							  	if (pre != 1) {
						      %>
							    <li>
							      <a href="manager_loglist?column=<%=pre %>" aria-label="Previous">
							        <span aria-hidden="true">&laquo;</span>
							      </a>
							    </li>
							    <%
							  		}
							    	for (int i = precolumn; i <= nextcolumn; i++) {
							    %>
							    	<li><a href="manager_loglist?column=<%=i %>"><%=i %></a></li>
							    <%
							    	}
							    	if (next != totalcolumn) {
							    %>
							    <li>
							      <a href="manager_loglist?column=<%=next %>" aria-label="Next">
							        <span aria-hidden="true">&raquo;</span>
							      </a>
							    </li>
							    <% } %>
							  </ul>
							</nav>
						</div>
					</div>
				<%
					} else {
				%>
				<div class="row">
					<h2>教师日志</h2>
					<p>无日志</p>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</div>
</body>
</html>