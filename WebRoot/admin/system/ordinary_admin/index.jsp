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
		<title>Zhbit科技协会-普通管理员后台</title>
		<%@ include file="../../common/iclude_file.jsp"%>
		
		<script type="text/javascript">
			$(function() {
				initMenu();
			});
		
		</script>
		
		<script type="text/javascript">
			$(function() {
				initMenu();
				$('#btn').bind('click', function() {
			  	  	$.getJSON('ordinaryadmin/refreshStaticPage_refreshIndex', function(json) {
			  	  		if (json.result != "success") {
			  	        	$.messager.alert('更新主页', json.data); 
			  	            return;  
			  	        } else {
			  	        	$.messager.alert('更新主页', '已经成功刷新首页'); 
			  	            return;  
			  	        }
			  	  	});
		  	  	});
			});
		
		</script>
	</head>
	<body class="easyui-layout">
		<div data-options="region:'north',border:false" style="">
		<div class="header">
	        <div class="hgroup">
	        	<b style="font-size: 30px">ZhbitACM协会-普通管理员后台</b>
	        </div>
	        <div class="clear"></div>
	        <div class="secondary_bar">
	            <div class="user user_noshadow">
	            	<p><s:property value="#session.ordinaryAdmin_realname"/> (上次登录:<s:property value="#session.ordinaryAdmin_lastLoginTime"/>)</p>  
	            </div>
	              <div style="float: right;padding-top: 3px; margin-right: 30px;">
	            	<a id="btn" href="javascript:void(0)"  class="easyui-linkbutton">更新主页</a>
	            </div>
	        </div>
	    </div>
	    <div class="clear"></div>
		</div>
		<div data-options="region:'west', split:'false'" style="width:240px;">
			<div class="easyui-accordion" data-options="fit:false,border:false">
				<div title="文章管理">
					<ul class="wg_menu">
						<li><a href="admin/system/ordinary_admin/article-add.jsp">新闻发布</a></li>
						<li><a href="admin/system/ordinary_admin/article-manage.jsp">新闻管理</a></li>
					</ul>
				</div>
				<div title="广告管理">
					<ul class="wg_menu">
						<li><a href="admin/system/ordinary_admin/banner-manage.jsp">Banner管理</a></li>
					</ul>
				</div>
				
				
				<div title="会员管理">
					<ul class="wg_menu">
						<li><a href="admin/system/ordinary_admin/user-manage.jsp">会员列表</a></li>
						<li><a href="admin/system/ordinary_admin/level-manage.jsp">头衔管理</a></li>
					</ul>
				</div>
				<div title="学校管理">
					<ul class="wg_menu">
						<li><a href="admin/system/ordinary_admin/school-manage.jsp">学校列表</a></li>
					</ul>
				</div>
					
				<div title="比赛管理">
					<ul class="wg_menu">
						<li><a href="admin/system/ordinary_admin/competition-add2.jsp">发布比赛</a></li>
						<li><a href="admin/system/ordinary_admin/competition-manage.jsp">比赛列表</a></li>
						<li><a href="admin/system/ordinary_admin/group-manage.jsp">队伍审核</a></li>
					</ul>
						
				</div>
				
				<div title="个人管理">
					<ul class="wg_menu">
						<li><a href="admin/system/ordinary_admin/password-update.jsp">修改密码</a></li>
						<li><a href="#">查看个人信息</a></li>
						<li><a href="ordinaryadmin/ordinary_loginOut">安全退出</a></li>
					</ul>
				</div>
				
			</div>
		</div>
		<div data-options="region:'center'">
		<iframe src="admin/system/ordinary_admin/main.jsp" id="wg_main" frameborder="0" style="width: 100%; height: 100%">
        </iframe>
		</div>
	</body>
</html>