<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>登录</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		 
		<%@ include file="../WEB-INF/common/commonInclude.jsp"%>


		<script type="text/javascript">
	//异步登录请求		
	$(function() {
	$.acm.initForm({
		form:'#loginForm',
		url:'user/user_login',
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
	
	<script>
 

     $(function () {
     
    } );
    </script>

	</head>

	<body>
		<div class="swap">
			<%@ include file="WEB-INF/common/header.jsp"%>

			<%@ include file="WEB-INF/common/banner.jsp"%>
			<div class="main_swap">
				<div class="main">
					<form class="form-horizontal" id="loginForm" method="post">
						<fieldset>
							<div id="legend" class="">
								<legend class="">
									登录
								</legend>
							</div>
							<div class="control-group">

								<!-- Text input-->
								<label class="control-label" for="input01">
									用户名
								</label>
								<div class="controls">
									<input type="text" placeholder="Username" class="input-xlarge" name="username" maxlength="16" minlength="6" data-validation-required-message="用户名不能为空"  required>
									<p class="help-block"></p>
								</div>
							</div>

							<div class="control-group">

								<!-- Text input-->
								<label class="control-label" for="input01">
									密码
								</label>
								<div class="controls">
									<input type="password" placeholder="Password" maxlength="16" minlength="6" data-validation-required-message="密码不能为空" name="password"
										class="input-xlarge" required>
									<p class="help-block"></p>
								</div>
							</div>

						</fieldset>
						<div class="problem_submit_operate">
							<ul>
								<li>
									<button class="btn btn-success">
										登录
									</button>
								</li>
								<li>
									<a href="register.jsp" class="btn btn-warning">注册</a>
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
