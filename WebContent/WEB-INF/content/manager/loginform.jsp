<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ManagerLoginForm</title>
<link rel="stylesheet" href="public/bower_components/bootstrap/dist/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-5 col-md-offset-4">
				<h2>管理员登录</h2><a href="main_loginform">返回</a>
				<hr>
				<form class="form-horizontal" action="manager_login" method="post">
					<div class="form-group">
						<label for="inputManagerLoginname" class="col-md-2 control-label">用户名</label>
						<div class="col-md-8">
					    	<input type="text" name="manager.loginname" class="form-control" id="inputManagerLoginname">
					    </div>
					</div>
					<div class="form-group">
						<label for="inputManagerPassword" class="col-md-2 control-label">密码</label>
						<div class="col-md-8">
					    	<input type="password" name="manager.password" class="form-control" id="inputManagerPassword">
					    </div>
					</div>
					<button class="btn btn-success">提交</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>