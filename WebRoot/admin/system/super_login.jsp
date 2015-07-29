<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>Zhbit科技协会-超级管理员后台</title>
		<link rel="stylesheet" href="admin/css/login.css" type="text/css" media="screen" />
		<%@ include file="../common/iclude_file.jsp" %>
		
	</head>
	<body id="login">
		<div id="login-wrapper" class="png_bg">
			<div id="login-top">
				<h1>
					Zhbit科技协会-超级管理员后台
				</h1>
				<!-- Logo (221px width) -->
			</div>
			<!-- End #logn-top -->
			<div class="wg_input" id="login-content">
				<form id="login_form" action="student/student_login" method="post">
					<p>
						<label>用户名</label>
						<input class="text-input" type="text" name="username"/>
					</p>
					<div class="clear"></div>
					<p>
						<label>密码</label>
						<input class="text-input" type="password" id="password" name="password"/>
					</p>
					<div class="clear"></div>
					<p>
						<label>验证码</label>
						<a href="javascript:void(0)" title='点击刷新验证码' onclick="refreshCode()" id="validataCode"><img style="width:90px; height: 30px; float: right;margin-left: 10px;" src="randomCode"/></a>
						<input class="text-input" type="text" name="validataCode" style="width:100px"/>
					</p>
					<div class="clear"></div>
					<p>
						<input id="login_submit" class="button" type="button"  style="height:30px;width:50px;margin-top: 0px;" value="登 录" />
					</p>
				</form>
			</div>
			<!-- End #login-content -->
		</div>
		<!-- End #login-wrapper -->
	</body>
</html>

<script type="text/javascript">
	$(function() {
		var url = top.location.href;
    	if(url.indexOf("login") < 0){
    		top.location.href = "<%=basePath%>admin/system/super_login.jsp";
    	}
		initLoginForm('#login_form', 'superadmin/superadmin_login', function() {
			window.location.href = "<%=basePath%>admin/system/super_admin/index.jsp";
		});
	});
</script>

