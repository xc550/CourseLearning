<%@page import="com.cl.dao.Section"%>
<%@page import="com.cl.dao.HomeworkStudent"%>
<%@page import="com.cl.dao.Student"%>
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
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">  
<script type="text/javascript" src="public/bower_components/jquery/jquery.js"></script>
<script type="text/javascript" src="public/bower_components/bootstrap/js/collapse.js"></script>
<script type="text/javascript" src="public/bower_components/bootstrap/dist/js/bootstrap.js"></script>
<script type="text/javascript">
function changeModal(homework_id) {
	document.getElementById("submithomeworkform").action="student_submithomework?homework_id=" + homework_id;
}
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<s:action name="sidebar_topframe" executeResult="true"></s:action>
		</div>
		<div class="row">
			<div class="col-md-3 well">
				<s:action name="sidebar_stu_sectionlist" executeResult="true"></s:action>
			</div>
			<div class="col-md-8 col-md-offset-1">
				<h2>作业列表</h2>
				<c:forEach items="${homeworklist}" var="hw" varStatus="st">
					<c:if test="${st.index == 0 || hw[st.index].section_id != hw[st.index - 1].section_id}">
						<h4>章节：${sectionname[st.index]}</h4>
					</c:if>
					<div class="panel-group" id="homeworkstudent${hw.homework_id}" role="tablist" aria-multiselectable="true">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headinghomeworkstudent${hw.homework_id}">
								<h4 class="panel-title">
									<a role="button" data-toggle="collapse" data-parent="#homeworkstudent${hw.homework_id}" 
										href="#collapsehomeworkstudent${hw.homework_id}" aria-expanded="true" 
										aria-controls="collapsehomeworkstudent${hw.homework_id}">
										${hw.homework_title}
									</a>
									开始时间:${hw.homework_starttime}
									结束时间:${hw.homework_endtime}
									<c:choose>
									<c:when test="${! empty homeworkstudent[st.index]}">
										已提交
									</c:when>
									<c:otherwise>
										未提交
									</c:otherwise>
									</c:choose>
								</h4>
							</div>
							<div id="collapsehomeworkstudent${hw.homework_id}" class="panel-collapse collapse" role="tabpanel" 
								aria-labelledby="headinghomeworkstudent${hw.homework_id}">
								<div class="panel-body">
									<h5>作业内容</h5>
									<div class="well">
										<p>${hw.homework_content}</p>
									</div>
									<c:if test="${! empty hw.homework_accessory}">
										<h5>附件下载</h5>
										<p>${hw.homework_accessory}
											<a href="main_homeworkdownload?type=homeworkaccessory&filename=${hw.homework_accessory}">
												<span class="glyphicon glyphicon-download"></span>
											</a>
										</p>
									</c:if>
									<c:choose>
										<c:when test="${empty homeworkstudent[st.index]}">
											<button type="button" onclick="changeModal(${hw.homework_id})" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#submithomework">
											提交作业
											</button>
										</c:when>
										<c:otherwise>
											<h5>作业下载</h5>
											<p>${homeworkstudent[st.index].homeworkstudent_accessory}
												<a href="main_homeworkdownload?type=studentaccessory&filename=${homeworkstudent[st.index].student_id}_${homeworkstudent[st.index].homeworkstudent_accessory}&homework_id=${hw.homework_id}">
													<span class="glyphicon glyphicon-download"></span>
												</a>
												<a href="student_deletehomework?homeworkstudent_id=${homeworkstudent[st.index].homeworkstudent_id}">
													<span class="glyphicon glyphicon-remove"></span>
												</a>
											</p>											
										</c:otherwise>
									</c:choose>
								</div><!-- panel-body -->
							</div><!-- panel-collapse -->
						</div><!-- panel -->
					</div><!-- panel-group -->
				</c:forEach>
			</div><!-- col-md-8 -->
		</div><!-- row -->
	</div><!-- container -->
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
				</div><!-- modal-body -->
			</div><!-- modal-content -->
		</div><!-- modal-dialog -->
	</div><!-- modal -->
</body>
</html>