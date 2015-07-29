<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
	<head>
		<base href="<%=basePath%>">
		<title>学生后台</title>
		<%@ include file="../../common/iclude_file.jsp"%>
		
		<script type="text/javascript">
			$(function() {
				initMenu();
			});
		
		</script>
	</head>
	<body class="easyui-layout">
		<div data-options="region:'north',border:false" style="">
		<div class="header">
	        <div class="hgroup">
	        	<b style="font-size: 30px">学生后台</b>
	        </div>
	        <div class="clear"></div>
	        <div class="secondary_bar">
	            <div class="user user_noshadow">
	            	<p><s:property value="#session.loginStatus.nickname"/> (<a href="#">4 条站内信</a>)</p>  
	            </div>
	        </div>
	    </div>
	    <div class="clear"></div>
		</div>
		<div data-options="region:'west', split:'false'" style="width:240px;">
			<div class="easyui-accordion" data-options="fit:false,border:false">
				<div title="闲置管理">
					<ul class="wg_menu">
						<li><a href="#">出售闲置</a></li>
						<li><a href="#">闲置管理</a></li>
					</ul>
				</div>
				<s:if test="#session.loginStatus.hasStore == true">  
 					<div title="学生店管理">
						<ul class="wg_menu">
							<li><a href="admin/student/typical_admin/storeGoods_upload.jsp">上传商品</a></li>
							<li><a href="admin/student/typical_admin/storeGoods_manage.jsp">商品管理</a></li>
						</ul>
					</div>
     			</s:if>  
				
				<div title="个人管理">
					<ul class="wg_menu">
						<li><a href="student/student_edit">帐户信息</a></li>
						<s:if  test="#session.loginStatus.hasStore == false">
							<li><a href="student/student_storeApplyForm">升级为学生店</a></li>
						</s:if>
						<s:if  test="#session.loginStatus.hasStore == true">
							<li><a href="student/student_storeDetail">学生店信息</a></li>
						</s:if>
						<li><a href="admin/student/typical_admin/password_edit.jsp">密码修改</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div data-options="region:'center'">
		<iframe src="admin/student/typical_admin/main.jsp" id="wg_main" frameborder="0" style="width: 100%; height: 100%">
        </iframe>
		</div>
	</body>
</html>