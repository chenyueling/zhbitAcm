<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
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
	<div id="p" class="easyui-panel" title="更新个人信息" style="padding:10px;">
		<form id="wg_form" class="wg_form">
			
			<div class="wg_item">
				<label for="name">用户名</label>
				<div class="wg_input">
					<b><s:property value="#request.student.username"/></b>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">用户类型</label>
				<div class="wg_input">
					<b><s:property value="#request.student.type"/></b>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">昵称</label>
				<div class="wg_input">
					<input id="nicekname" name="nickname" type="text" value="<s:property value="#request.student.nickname"/>"/>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">性别</label>
				<div class="wg_input">
					<div class="controls">
						<input type="radio" name="sex" id="MAN" value="MAN" style="width: 30px"><b class="form_b">男</b>
						<input type="radio" name="sex" id="WOMAN" value="WOMAN" style="width: 30px"><b class="form_b">女</b>
						<input type="radio" name="sex" id="SECRET" value="SECRET" style="width: 30px"><b class="form_b">保密</b>
					</div>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">邮箱</label>
				<div class="wg_input">
					<input id="email" name="email"  type="text" value="<s:property value="#request.student.email"/>"/>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">QQ</label>
				<div class="wg_input">
					<input id="QQ" name="QQ" type="text" value="<s:property value="#request.student.QQ"/>"/>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">手机号码</label>
				<div class="wg_input">
					<input id="phone" name="phone" type="text" value="<s:property value="#request.student.phone"/>"/>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">短号</label>
				<div class="wg_input">
					<input id="shortPhone" name="shortPhone" type="text" value="<s:property value="#request.student.shortPhone"/>"/>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">真实姓名</label>
				<div class="wg_input">
					<input id="realname" name="realname" type="text" value="<s:property value="#request.student.realname"/>"/>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">学号</label>
				<div class="wg_input">
					<input id="studentCode" name="studentCode" type="text" value="<s:property value="#request.student.studentCode"/>"/>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">身份证号码</label>
				<div class="wg_input">
					<input id="IDNumber" name="IDNumber" type="text" value="<s:property value="#request.student.IDNumber"/>"/>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">最后登录时间</label>
				<div class="wg_input">
					<b><s:date name="#request.student.lastLoginTime" format="yyyy-MM-dd HH:mm:ss"/></b>
				</div>
			</div>
			<div class="clear"></div>

			<div class="wg_item">
				<div class="wg_input" style="margin-left:110px;margin-top: 10px;">
					<a href="#" id="submit" onclick="javascript:void(0)" class="easyui-linkbutton">更新个人信息</a>
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
			url : "student/student_update",
			dataType : 'json',
			success : function(json) {
				var tip = json.tip ;
				if (tip.message != "") {
					$.messager.alert('学生个人管理', tip.message);
					return;
				} else {
					$.messager.alert('学生个人管理', '更新个人信息成功');
				}
			}
		});	

		$("#submit").bind("click", function() {
			$('#wg_form').submit();		
			return false;
		});
		
		//sex 单选框 选定
		var sex = "<s:property value="#request.student.sex"/>";
		sex = "#" + sex;
		$(sex).attr("checked", "checked");

		

		
});

	
</script>

	
</body>
</html>
