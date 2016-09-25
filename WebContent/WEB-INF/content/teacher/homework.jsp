<%@page import="com.cl.dao.Section"%>
<%@page import="com.cl.util.DateFormator"%>
<%@page import="com.cl.dao.Homework"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homework</title>
	<script type="text/javascript" src="public/bower_components/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="public/bower_components/moment/min/moment.min.js"></script>
	<script type="text/javascript" src="public/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="public/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
	<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.min.css" />
	<link rel="stylesheet" href="public/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css" />
	<script type="text/javascript">
	$(function () {	  
		$('#inputHomeworkStartTime').datetimepicker({
			format: 'YYYY-MM-DD HH:mm:ss'
		});
		$('#inputHomeworkEndTime').datetimepicker({
			format: 'YYYY-MM-DD HH:mm:ss'
		});
	});
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
				<div class="row">
					<h2>作业列表</h2>
					<c:forEach items="${homeworklist}" var="hw" varStatus="st">
						<c:if test="${st.index == 0 || hw[st.index].section_id != hw[st.index - 1].section_id}">
							<h4>章节：${sectionname[st.index]}</h4>
						</c:if>
						<div class="panel-group" id="homework${hw.homework_id}" role="tablist" aria-multiselectable="true">
							<div class="panel panel-default">
								<div class="panel-heading" role="tab" id="headinghomework${hw.homework_id}">
									<h4 class="panel-title">
										<a role="button" data-toggle="collapse" data-parent="#homework${hw.homework_id}" 
											href="#collapsehomework${hw.homework_id}" aria-expanded="true" 
											aria-controls="collapsehomework${hw.homework_id}">
											${hw.homework_title}
										</a>
										开始时间:${hw.homework_starttime}
										结束时间:${hw.homework_endtime}
										<a href="teacher_deletehomework?homework_id=${hw.homework_id}">
											<span class="glyphicon glyphicon-remove"></span>
										</a>
									</h4>
								</div>
								<div id="collapsehomework${hw.homework_id}" class="panel-collapse collapse" role="tabpanel" 
									aria-labelledby="headinghomework${hw.homework_id}">
									<div class="panel-body">
										<h5>作业内容</h5>
										<div class="well">
											<p>${hw.homework_content}</p>
										</div>
										<c:choose>
											<c:when test="${! empty hw.homework_accessory}">
												<h5>附件下载</h5>
												<p>${hw.homework_accessory}
													<a href="main_homeworkdownload?type=homeworkaccessory&filename=${hw.homework_accessory}">
														<span class="glyphicon glyphicon-download"></span>
													</a>
													<a href="teacher_deletehomeworkaccessory?homework_id=${hw.homework_id}">
														<span class="glyphicon glyphicon-remove"></span>
													</a>
												</p>
											</c:when>
											<c:otherwise>
												<form action="teacher_updatehomeworkaccessory?homework_id=${hw.homework_id}"
													enctype="multipart/form-data" method="post">
													<div class="form-group">
														<label for="updateHomeworkAccessory">提交附件</label>
														<s:file name="upload" label="Select File" id="updateHomeworkAccessory"></s:file>
													</div>
													<button class="btn btn-success btn-sm" type="submit">提交</button>
												</form>
											</c:otherwise>
										</c:choose>
										<hr>
										<a class="btn btn-sm btn-primary" href="teacher_getstudenthomeworklist?homework_id=${hw.homework_id}">批改作业</a>
									</div><!-- end panel-body -->
								</div><!-- end panel-collapse -->
							</div><!-- end panel -->
						</div><!-- end panel-group -->
					</c:forEach>
				</div><!-- end row -->
				<div class="row">
					<h2>添加作业</h2>
					<form action="teacher_addhomework" enctype="multipart/form-data" method="post">
						<div class="form-group">
							<label for="inputHomeworkTitle">作业标题</label>
							<input type="text" name="homework.homework_title" class="form-control" id="inputHomeworkTitle">
						</div>
						<div class="form-group">
							<label for="inputHomeworkSection">章节选择</label>
							<select name="homework.section_id" class="form-control" id="inputHomeworkSection">
								<c:forEach items="${sectionlist}" var="section">
									<option value="${section.section_id}">${section.section_name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="inputHomeworkContent">作业内容</label>
							<textarea rows="3" name="homework.homework_content" class="form-control" id="inputHomeworkContent"></textarea>
						</div>
						<div class="form-group">
							<label for="inputHomeworkStartTime">开始时间</label>
							<div id="inputHomeworkStartTime" class="input-group date">
								<input type="text" class="form-control" name="homework.homework_starttime" />
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
						<div class="form-group">
							<label for="inputHomeworkEndTime">结束时间</label>
							<div id="inputHomeworkEndTime" class="input-group date">
								<input type="text" class="form-control" name="homework.homework_endtime" />
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
						<div class="form-group">
							<label for="inputHomeworkAccessory">提交附件</label>
							<s:file name="upload" id="inputHomeworkAccessory"></s:file>
						</div>
						<button class="btn btn-success btn-sm" type="submit">提交</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>