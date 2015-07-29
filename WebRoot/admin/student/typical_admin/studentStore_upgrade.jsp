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
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@ include file="../../common/iclude_file.jsp" %>
	<%@ include file="../../common/include_bootstrap.jsp" %>
	<style type="text/css">
		body, html {
			background: none;
			background: #F8F8F8;
		}
		.bs-box-studentStore:after {
		  content: "升级为学生店";
		}
		input[type="text"],input[type="password"] {
			height:30px;
		}
		input[type="checkbox"],input[type="radio"] {
			width:30px;
		}
		.form_p {
			line-height: 30px;
			padding-left: 20px;
		}
		.form_b {
			padding-right: 10px;
			padding-left: 10px;
			font-weight: normal;
		}
		.form_b_red {
			padding-right: 10px;
			padding-left: 10px;
			font-weight: normal;
			color:red;
		}
		
	</style>

  </head>
  
<body bgcolor="#F8F8F8">
<div class="bs-box bs-box-studentStore">
<div class="container-fluid">
	<div class="row-fluid">
			<form action="student/student_applyStudentStore" class="form-horizontal" method="post">
				<div class="control-group">
					 <label class="control-label" for="inputEmail">店主</label>
					<div class="controls">
						<!-- <input id="inputEmail" type="text" />  -->
						<p class="form_p"><s:property value="#request.student.username"/> / <s:property value="#request.student.nickname"/></p>
					</div>
				</div>
				<div class="control-group">
					 <label class="control-label" for="storename"><b class="form_b_red">*</b>店名</label>
					<div class="controls">
						<input id="storename" name="storename" type="text" />
					</div>
				</div>

				<div class="control-group">
					 <label class="control-label" for="email"><b class="form_b_red">*</b>邮箱</label>
					<div class="controls">
						<p class="form_p"><s:property value="#request.student.email"/></p>
					</div>
				</div>
				<div class="control-group">
					 <label class="control-label" for="QQ"><b class="form_b_red">*</b>QQ</label>
					<div class="controls">
						<p class="form_p"><s:property value="#request.student.QQ"/></p>
					</div>
				</div>
				<div class="control-group">
					 <label class="control-label" for="phone"><b class="form_b_red">*</b>手机号码</label>
					<div class="controls">
						<p class="form_p"><s:property value="#request.student.phone"/></p>
					</div>
				</div>
				<div class="control-group">
					 <label class="control-label" for="shortPhone">短号</label>
					<div class="controls">
						<p class="form_p"><s:property value="#request.student.shortPhone"/></p>
					</div>
				</div>
				<div class="control-group">
					 <label class="control-label" for="studentCode" >学号</label>
					<div class="controls">
						<p class="form_p"><s:property value="#request.student.studentCode"/></p>
					</div>
				</div>
				<div class="control-group">
					 <label class="control-label" for="realname"><b class="form_b_red">*</b>真实姓名</label>
					<div class="controls">
						<p class="form_p"><s:property value="#request.student.realname"/></p>
					</div>
				</div>
				<div class="control-group">
					 <label class="control-label" for="IDNumber"><b class="form_b_red">*</b>身份证号码</label>
					<div class="controls">
						<p class="form_p"><s:property value="#request.student.IDNumber"/></p>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						 <label class="checkbox"><input type="checkbox" /> 同意用户条例</label> 
						 <button type="submit" id="submit" class="btn">申请学生店</button>
					</div>
				</div>
				
			</form>
		
	</div>
</div>	
</div>
</body>
</html>
