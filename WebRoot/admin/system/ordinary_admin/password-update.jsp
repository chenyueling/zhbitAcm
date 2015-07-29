<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<%@ include file="../../common/iclude_file.jsp" %>
	
	<style type="text/css">
		
		.wg_form {
			margin-left:50px;
		}
		
		.wg_item {
			display: block;
			margin-top: 10px;
			margin-bottom: 10px;
		}
		.wg_item label {
			width:100px;
			text-align:right;
			float:left;
			display: block;
			margin-right: 20px;
			line-height: 23px;
		}
		.wg_input {
		}
		.wg_input a {
			color:break;
		}	
		.wg_label {
			float:left;
			margin-right: 10px;
		}
		
	</style>
  </head>
  
<body bgcolor="#F8F8F8">
	<div id="p" class="easyui-panel" title="修改密码" style="padding:10px;">
		<form id="wg_form" class="wg_form">
			
			<div class="wg_item">
				<label for="name">旧密码</label>
				<div class="wg_input">
					<input id="oldPassword" name="oldPassword" type="password" style="width: 200px;" class="easyui-validatebox" validType="length[6, 20]" required="true"/>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">新密码</label>
				<div class="wg_input">
					<input id="password" name="password" type="password" style="width: 200px;" class="easyui-validatebox" validType="length[6, 20]" required="true"/>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">重复密码</label>
				<div class="wg_input">
					<input id="passwordRepeat" name="password2" type="password" style="width: 200px;" class="easyui-validatebox" validType="length[6, 20]" required="true"/>
				</div>
			</div>
			<div class="clear"></div>
			
			<div class="wg_item">
				<div class="wg_input" style="margin-left:110px;margin-top: 10px;">
					<a href="javascript:void(0)" onclick="$('#wg_form').submit()" class="easyui-linkbutton">修改密码</a>
				</div>
			</div>
			<div class="clear"></div>

		</form>
	</div>

	
	
<script type="text/javascript">
	
$(function() {

	//表单初始化
	initForm('#wg_form', 'ordinaryadmin/ordinary_updatePassword', function() {
		$.messager.alert('系统消息', '修改成功，请重新登录');
		top.location.href = "<%=basePath%>admin/system/ordinary_login.jsp";
	});

		
});

	
</script>

	
</body>
</html>
