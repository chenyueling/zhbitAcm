<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>学生用户登录</title>
		<link rel="stylesheet" href="admin/css/login.css" type="text/css" media="screen" />
		<%@ include file="../common/iclude_file.jsp" %>
		
	</head>
	<body id="login">
		<div id="login-wrapper" class="png_bg">
			<div id="login-top">
				<h1>
					学生用户后台
				</h1>
				<!-- Logo (221px width) -->
			</div>
			<!-- End #logn-top -->
			<div class="wg_input" id="login-content">
				<form id="login_form" action="student/student_login" method="post">
					<p>
						<label>
							用户名
						</label>
						<input class="text-input" type="text" name="username"
						value="<s:property value="#request.username"/>"/>
					</p>
					<div class="clear"></div>
					<p>
						<label>
							密码
						</label>
						<input class="text-input" type="password" id="password"/>
					</p>
					<div class="clear"></div>
					<p>
						<input id="submit" class="button" type="submit" value="登 录" />
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
		var message = '<s:property value="#request.message"/>';
		if (message != "") {
			alert(message);
		}

		$('#submit').bind('click', function() {
			var password = $('#password').val();
			bodyRSA();
			var result = encryptedString(key, encodeURIComponent(password));
			var html = "<input type='hidden' name='password' value='" + result + "' />";
			$('#login_form').append(html);
			return true;
		});
		
	});

	var key; 
	function bodyRSA() { 
	    setMaxDigits(131); 
	    key = new RSAKeyPair("10001", "", "81595192cea41428a8606f15902d0c451100be86022086b5700ff404d9229b01a4d22830af5a53305bdcdde99ec52269bb6dace76cc22bbfec14793afc2f6bc5db4da2922a316de3b330b40ccd3e77e4a1afd65096db5c066734324260825ef9c7d19fb1572e2ff876e4b42f3572ed38aac5d4cb2c2f64598ffae93ab3a2ed59"); 
	}
</script>

