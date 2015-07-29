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
	<%@ include file="../../common/include_ueditor.jsp" %>
	
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
		.image_show img {
	
		}
		.ftitle{  
            font-size:14px;  
            font-weight:bold;  
            padding:5px 0;  
            margin-bottom:10px;  
            border-bottom:1px solid #ccc;  
        }  
        .fitem{  
            margin-bottom:5px;  
        }  
        .fitem label{  
            display:inline-block;  
            width:80px;  
        } 
        #type_name {
        	font-weight: normal;
        	margin-left: 20px;
        }
		
	</style>
  </head>
  
<body bgcolor="#F8F8F8">
	<div id="p" class="easyui-panel" title="修改密码" style="padding:10px;">
		<form id="wg_form" class="wg_form">
			
			<div class="wg_item">
				<label for="name">旧密码</label>
				<div class="wg_input">
					<input id="oldPassword" type="password" style="width: 200px;" />
					<input type="hidden" name="oldPassword"/>"
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">新密码</label>
				<div class="wg_input">
					<input id="password" type="password" style="width: 200px;" />
					<input type="hidden" name="password"/>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">重复密码</label>
				<div class="wg_input">
					<input id="passwordRepeat" type="password" style="width: 200px;" />
					<input type="hidden" name="passwordRepeat"/>
				</div>
			</div>
			<div class="clear"></div>
			
			<div class="wg_item">
				<div class="wg_input" style="margin-left:110px;margin-top: 10px;">
					<a href="#" id="submit" onclick="javascript:void(0)" class="easyui-linkbutton">修改密码</a>
				</div>
			</div>
			<div class="clear"></div>

		</form>
	</div>

	
	
<script type="text/javascript">
	
$(function() {

		//表单初始化
		$('#wg_form').ajaxForm({
			type : "post",
			url : "student/student_updatePassword",
			dataType : 'json',
			success : function(json) {
				var tip = json.tip ;
				if (tip.message != "") {
					$.messager.alert('学生个人管理', tip.message);
					return;
				} else {
					$.messager.alert('学生个人管理', '修改密码成功');
				}
			}
		});	

		$("#submit").bind("click", function() {
			
			var password = $('#password').val();
			var oldPassword = $('#oldPassword').val();
			var passwordRepeat = $('#passwordRepeat').val();
			
			var key = getRSAKey();
			password = encryptedString(key, encodeURIComponent(password));
			oldPassword = encryptedString(key, encodeURIComponent(oldPassword));
			passwordRepeat = encryptedString(key, encodeURIComponent(passwordRepeat));

			$("input[name='password']").val(password);
			$("input[name='oldPassword']").val(oldPassword);
			$("input[name='passwordRepeat']").val(passwordRepeat);

			$('#wg_form').submit();
			
			return false;
		});

		

		
});

	
</script>

	
</body>
</html>
