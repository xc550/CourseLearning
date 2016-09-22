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
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EventInfo</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
<script type="text/javascript" src="public/bower_components/jquery/jquery.js"></script>
<script type="text/javascript">
function changeModal(bbsid) {
	document.getElementById("replyformforteacher").action="teacher_submitbbs?reply_id=" + bbsid;
}
</script>
<script type="text/javascript" src="public/bower_components/bootstrap/dist/js/bootstrap.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<s:action name="sidebar_topframe" executeResult="true"></s:action>
		</div>
		<div class="row">
			<div class="col-md-3 well">
				<s:action name="sidebar_tea_eventlist" executeResult="true"></s:action>
			</div>
			<div class="col-md-8 col-md-offset-1">
				<div class="row">
					<h2>
						${event.event_content}
						<small>
							${event.event_type}&nbsp;
							${event.starttime}&nbsp;~&nbsp;${event.endtime}
						</small>
					</h2>
					<table class="table table-bordered table-condensed">
						<thead>
							<tr>
								<c:forEach items="${learningstatuscolumns}" var="cl">
									<td>${cl}</td>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${learningstatus}" var="ls" varStatus="st">
								<tr>
									<td>${student[st.count].loginname}</td>
									<td>${student[st.count].name}</td>
									<td>${learningstatus.classtimetostring[learningstatus.classtime]}</td>
									<td>${learningstatus.classtostring[learningstatus.inclass]}</td>
									<td>${learningstatus.classtostring[learningstatus.outclass]}</td>
									<td>${learningstatus.method}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="row well">
					<h2>数据饼状图</h2>
					<img alt="ClassTime" src="teacher_displayChart?pilename=classtime">
					<img alt="InClass" src="teacher_displayChart?pilename=inclass">
					<img alt="OutClass" src="teacher_displayChart?pilename=outclass">
				</div>
				<div class="row">
					<h2>论坛发言情况</h2>
					<c:forEach items="${bbs}" var="item" varStatus="st">
						<hr>
						<div class="row">
							<div class="col-md-3">
								<div class="row">
									<div class="col-xs-10 col-md-5">
										<a href="#" class="thumbnail">
											<img alt="Head" data-src="holder.js/300x300" src="public/userheads/head.jpg">
										</a>
									</div>
								</div>
							</div>
							<div class="col-md-9">
								<div class="row bbs_head">
									<strong>
										<em>${st.count}</em>
										<sup>#</sup>
									</strong>
									<p>&nbsp;
									<c:choose>
										<c:when test="${item.student_id == bbs[0].student_id && item.teacher_id == bbs[0].teacher_id}">
											<strong>楼主:</strong>
										</c:when>
										<c:when test="${item.teacher_id != -1}">
											<strong>老师：</strong>
										</c:when>
									</c:choose>
									${item.username}
									发表于 
									${item.bbs_date}
									</p>
									<a type="button" onclick="changeModal(${item.bbs_id});" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#studentModal">回复</a>
								</div>
								<div class="row">
									<c:if test="${item.reply_id != -1}">
										<blockquote>
											<p>
											<u>
											${bbs[item.reply_id].username}
											发表于
											${bbs[item.reply_id].bbs_date}
											</u>
											<br>
											${bbs[item.reply_id].bbs_content}
											</p>
										</blockquote>
									</c:if>
									<br>
									<p>${item.bbs_content}</p>
								</div>
							</div>
						</div>
					</c:forEach>
					<!-- 发帖框 -->
					<hr>
					<div class="row">
						<div class="col-md-3">
							<div class="row">
								<div class="col-xs-10 col-md-5">
									<a href="#" class="thumbnail">
										<img alt="Head" data-src="holder.js/300x300" src="public/userheads/head.jpg">
									</a>
								</div>
							</div>
						</div>
						<div class="col-md-9">
							<form action="teacher_submitbbs?reply_id=-1" method="post">
								<div class="form-group">
									<textarea name="bbs.bbs_content" class="form-control" rows="3"></textarea>
								</div>
								<button class="btn btn-success btn-sm" type="submit">提交</button>
							</form>
						</div>
					</div><!-- end row -->
				</div><!-- end row -->
			</div><!-- end col-md-8 -->
		</div><!-- end row -->
	</div><!-- end container -->
	
	<!-- Reply Modal -->
	<div class="modal fade bs-example-modal-sm" id="teacherModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
		<div class="modal-content">
		    <div class="modal-header">
		    	<h2>回复框</h2>
		    </div>
		    <form id="replyformforteacher" action="teacher_submitbbs" method="post">
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
</body>
</html>