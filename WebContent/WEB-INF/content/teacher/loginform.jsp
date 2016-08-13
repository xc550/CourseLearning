<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TeacherLoginForm</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-5 col-md-offset-4">
				<h2>教师登录</h2><a href="main_loginform">返回</a>
				<form class="form-horizontal" action="teacher_login" method="post">
					<div class="form-group">
						<label for="inputTeacherLoginname" class="col-md-2 control-label">用户名</label>
						<div class="col-md-8">
					    	<input type="text" name="teacher.loginname" class="form-control" id="inputTeacherLoginname">
					    </div>
					</div>
					<div class="form-group">
						<label for="inputTeacherPassword" class="col-md-2 control-label">密码</label>
						<div class="col-md-8">
					    	<input type="password" name="teacher.password" class="form-control" id="inputTeacherPassword">
					    </div>
					</div>
					<button class="btn btn-success">提交</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>