<%@page import="java.math.BigDecimal"%>
<%@page import="com.cl.dao.SectionScore"%>
<%@page import="com.cl.dao.Homework"%>
<%@page import="com.cl.dao.Student"%>
<%@page import="com.cl.dao.HomeworkStudent"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ScoreHomework</title>
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
				<h2>批改作业</h2><a href="teacher_managehomework">返回作业列表</a>
				<%
					ArrayList<HomeworkStudent> list = (ArrayList<HomeworkStudent>)request.getAttribute("homeworklist");
					Homework hw = (Homework)request.getAttribute("homework");
				%>
				<h5>作业内容</h5>
				<div class="well">
					<p><%=hw.getHomework_content() %></p>
				</div>
				<% if (hw.getHomework_accessory() != null) { %>
				<h5>附件下载</h5>
				<p><%=hw.getHomework_accessory() %>
					<a href="main_homeworkdownload?type=homeworkaccessory&filename=<%=hw.getHomework_accessory()%>">
						<span class="glyphicon glyphicon-download"></span>
					</a>
				</p>
				<% } %>
				<hr>
				<% if (list.size() > 0) { %>
				<form action="teacher_scorehomework?section_id=<%=hw.getSection_id() %>&homework_id=<%=hw.getHomework_id() %>" method="post">
					<table class="table table-hover table-condensed">
						<thead>
							<tr>
								<td>学生编号</td>
								<td>学生姓名</td>
								<td>备注</td>
								<td>附件</td>
								<td>评分</td>
								<td>修改</td>
							</tr>
						</thead>
						<tbody>
							<%
								for (int i = 0; i < list.size(); i++) {
									HomeworkStudent hws = list.get(i);
									Student stu = Student.getStudentByStudentId(hws.getStudent_id());
							%>
							<tr>
								<td><input name="sectionscore[<%=i %>].student_id" value="<%=stu.getId()%>" 
										style="border: 0px; background-color: transparent; width: 10px;"></td>
								<td><%=stu.getName() %></td>
								<td><%=hws.getHomeworkstudent_comment() %></td>
								<td><%=hws.getHomeworkstudent_accessory() %>
									<a href="main_homeworkdownload?type=studentaccessory&filename=<%=hws.getStudent_id() %>_<%=hws.getHomeworkstudent_accessory()%>&homework_id=<%=hws.getHomework_id()%>">
										<span class="glyphicon glyphicon-download"></span>
									</a>
								</td>
								<%
									double score = new BigDecimal(SectionScore.getHomeworkScoreByStudentIdAndSectionId(hw.getSection_id(), stu.getId())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
									if (score == -1.0) {
								%>
									<td><input name="sectionscore[<%=i %>].homework" min="0" max="100" step="0.5"></td>
									<td></td>
								<% } else {%>
									<td id="student<%=hws.getStudent_id() %>homeworkscore"><p id="student<%=hws.getStudent_id() %>nowhomeworkscore"><%=score %></p></td>
									<td><a onclick="javascript:updateScore(<%=i%>, <%=hws.getStudent_id()%>, <%=score%>); return false;" href="#"><span class="glyphicon glyphicon-pencil"></span></a></td>
								<% } %>
							</tr>
							<% } %>
						</tbody>
					</table>
					<button class="btn btn-success btn-sm" type="submit">提交</button>
				</form>
				<% } else { %>
				<p><strong>学生还未提交作业</strong></p>
				<% } %>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function updateScore(index, student_id, score) {
			var parent=document.getElementById("student"+student_id+"homeworkscore");
			var child=document.createElement("input");
			var former=document.getElementById("student"+student_id+"nowhomeworkscore")
			child.name="sectionscore["+index+"].homework";
			child.value=score;
			child.min=0;
			child.max=100;
			child.step=0.5;
			parent.removeChild(former);
			parent.appendChild(child);
		}
	</script>
</body>
</html>