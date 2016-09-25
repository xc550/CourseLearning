<%@page import="java.math.BigDecimal"%>
<%@page import="com.cl.dao.SectionScore"%>
<%@page import="com.cl.dao.Homework"%>
<%@page import="com.cl.dao.Student"%>
<%@page import="com.cl.dao.HomeworkStudent"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ScoreHomework</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
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
				<h5>作业内容</h5>
				<div class="well">
					<p>${homework.homework_content}</p>
				</div>
				<c:if test="${! empty homework.homework_accessory}">
					<h5>附件下载</h5>
					<p>${homework.homework_accessory}
						<a href="main_homeworkdownload?type=homeworkaccessory&filename=${homework.homework_accessory}">
							<span class="glyphicon glyphicon-download"></span>
						</a>
					</p>
				</c:if>
				<hr>
				<c:choose>
					<c:when test="${! empty homeworklist}">
						<form action="teacher_scorehomework?section_id=${homework.section_id}&homework_id=${homework.homework_id}" method="post">
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
									<c:forEach items="${homeworklist}" var="hws" varStatus="st">
										<tr>
											<td><input name="sectionscore[${st.index}].student_id" value="${student[st.index].id}" 
													style="border: 0px; background-color: transparent; width: 10px;"></td>
											<td>${student[st.index].name}</td>
											<td>${hws.homeworkstudent_comment}</td>
											<c:choose>
												<c:when test="${! empty hws.homeworkstudent_accessory}">
													<td>${hws.homeworkstudent_accessory}
														<a href="main_homeworkdownload?type=studentaccessory&filename=${hws.student_id}_${hws.homeworkstudent_accessory}&homework_id=${hws.homework_id}">
															<span class="glyphicon glyphicon-download"></span>
														</a>
													</td>
												</c:when>
												<c:otherwise>
													<td>无附件</td>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${score[st.index] != -1.0}">
													<td><input name="sectionscore[${st.index}].homework" min="0" max="100" step="0.5"></td>
													<td></td>
												</c:when>
												<c:otherwise>
													<td id="student${hws.student_id}homeworkscore"><p id="student${hws.student_id}nowhomeworkscore">${score[st.index]}</p></td>
													<td>
														<a onclick="javascript:updateScore(${st.index}, ${hws.student_id}, ${score[st.index]}); return false;" href="#">
															<span class="glyphicon glyphicon-pencil"></span>
														</a>
													</td>
												</c:otherwise>
											</c:choose>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<button class="btn btn-success btn-sm" type="submit">提交</button>
						</form>
					</c:when>
					<c:otherwise>
						<p><strong>学生还未提交作业</strong></p>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>