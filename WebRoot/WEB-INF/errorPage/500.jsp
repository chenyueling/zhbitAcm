<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>500</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<%@ include file="../common/commonInclude.jsp"%>


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
	})
	</script>

	</head>

	<body>
		<div class="swap">
			<%@ include file="../common/header.jsp"%>
			<div class="main_swap" style="padding-top:0px;">
				<div class="main" >
					<img alt="" src="images/500.png">

					<div class="clearfix"></div>

				</div>
				<div class="clearfix"></div>
			</div>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</body>

</html>
