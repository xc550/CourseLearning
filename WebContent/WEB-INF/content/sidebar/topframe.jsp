<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="com.cl.dao.Manager"%>
<%@page import="com.cl.dao.Student"%>
<%@page import="com.cl.entity.User"%>
<%@page import="com.cl.dao.Teacher"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TopFrame</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
<script type="text/javascript" src="public/bower_components/jquery/jquery.min.js"></script>
<script type="text/javascript" src="public/bower_components/bootstrap/js/collapse.js"></script>
</head>
<body>
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="#">学习分析管理系统</a>
	    </div>
	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav navbar-right">
	      	<li><p class="navbar-text">欢迎，${realname}</p></li>
	        <li><a href="${role}_logout">注销</a></li>
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
</body>
</html>