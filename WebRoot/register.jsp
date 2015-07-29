<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
	<head>
	<base href="<%=basePath%>"> 
		<title>注册</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<%@ include file="../WEB-INF/common/commonInclude.jsp"%>
		
	<script type="text/javascript">
	//异步登录请求		
	$(function() {
	$.acm.initForm({
		form:'#registerForm',
		url:'user/user_register',
		success:function(json) {
			alert(json.data);
			window.location.href = "<%=basePath%>index.jsp";
		}
	}); 

	//验证表单
	 $("input,select,textarea").not("[type=submit]").jqBootstrapValidation(); 
    $("input,select,textarea").jqBootstrapValidation({sniffHtml: false});//;
	})
	</script>
	</head>

	<body>
		<div class="swap">
			<%@ include file="WEB-INF/common/header.jsp"%>
			<%@ include file="WEB-INF/common/banner.jsp"%>
			<div class="main_swap">
				<div class="main">
					<form class="form-horizontal" id="registerForm" method="post">
						<fieldset>
							<div id="legend" class="">
								<legend class="">
									加入Zhbit ACM
								</legend>
							</div>
							<div class="control-group">

								<!-- Text input-->
								<label class="control-label" for="input01">
									用户名
								</label>
								<div class="controls">
									<input type="text" placeholder="Username" maxlength="16" minlength="6" name="username" data-validation-required-message="用户名不能为空"  class="input-xlarge" required>
									<p class="help-block"></p>
								</div>
							</div>
							<div class="control-group">

								<!-- Text input-->
								<label class="control-label" for="input01">
									密码
								</label>
								<div class="controls">
									<input type="password" placeholder="Password" maxlength="16" data-validation-required-message="密码不能为空" minlength="6" name="password"
										class="input-xlarge" required>
									<p class="help-block"></p>
								</div>
							</div>

							<div class="control-group">

								<!-- Text input-->
								<label class="control-label" for="input01">
									重复密码
								</label>
								<div class="controls">
									<input type="password" placeholder="Password" name="password2"
										class="input-xlarge" maxlength="16" data-validation-required-message="密码不能为空" minlength="6" name="password" required>
									<p class="help-block"></p>
								</div>
							</div>

							<div class="control-group">

								<!-- Text input-->
								<label class="control-label" for="input01">
									Email
								</label>
								<div class="controls">
									<input type="email" placeholder="Email" name="email" 
										class="input-xlarge" data-validation-email-message="邮箱格式不正确" required>
									<p class="help-block"></p>
								</div>
							</div>

							<div class="control-group">

								<!-- Text input-->
								<label class="control-label" for="input01">
									昵称
								</label>
								<div class="controls">
									<input type="text" placeholder="Nickname" name="nickname" 
										class="input-xlarge" maxlength="16" data-validation-required-message="请填写昵称"   required>
									<p class="help-block"></p>
								</div>
							</div>
						</fieldset>
						<div class="problem_submit_operate">
							<ul>
								<li>
									<button class="btn btn-success" onClick="register()">
										注册
									</button>
								</li>
								<li>
									<button class="btn btn-warning">
										重置
									</button>
								</li>
							</ul>
						</div>

					</form>

					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<%@ include file="WEB-INF/common/footer.jsp"%>
		</div>
	</body>

</html>
