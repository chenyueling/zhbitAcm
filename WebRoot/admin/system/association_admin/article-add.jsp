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
			float:left;
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
	<div id="p" class="easyui-panel" title="添加文章" style="padding:10px;">
		<form id="wg_form" class="wg_form">
			<div class="wg_item">
				<label for="name">文章类型</label>
				<div class="wg_input">
					<select class="easyui-combobox" editable="false" name="type" id=type" style="width:200px;">
						<option value="1">景点观光</option>
						<option value="2">购物天堂</option>
						<option value="3">澳门任我行</option>
						<option value="4">节日盛世</option>
						<option value="5">期刊搜索</option>
						<option value="6">休闲娱乐</option>
						<option value="7">特色美食</option>
						<option value="8">认识澳门</option>
					</select>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">文章标题</label>
				<div class="wg_input">
					<input name="title" type="text" style="width: 400px;" class="easyui-validatebox" validType="length[1, 50]" required="true"/>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">文章标题<span style="color: red;">(英文)</span></label>
				<div class="wg_input">
					<input name="title_US" type="text" style="width: 400px;" class="easyui-validatebox" validType="length[1, 50]" required="true"/>
				</div>
			</div>
			
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">文章小标题</label>
				<div class="wg_input">
					<input name="smallTitle" type="text" style="width: 200px;" class="easyui-validatebox" validType="length[1, 50]" required="true"/>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">文章小标题<span style="color: red;">(英文)</span></label>
				<div class="wg_input">
					<input name="smallTitle_US" type="text" style="width: 200px;" class="easyui-validatebox" validType="length[1, 50]" required="true"/>
				</div>
			</div>
			
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">文章作者</label>
				<div class="wg_input">
					<input name="author" type="text" style="width: 200px;" class="easyui-validatebox" validType="length[1, 50]" required="true"/>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">文章作者<span style="color: red;">(英文)</span></label>
				<div class="wg_input">
					<input name="author_US" type="text" style="width: 200px;" class="easyui-validatebox" validType="length[1, 50]" required="true"/>
				</div>
			</div>
			
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">文章来源</label>
				<div class="wg_input">
					<input name="source" type="text" style="width: 200px;" class="easyui-validatebox" validType="length[1, 50]" required="true"/>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">文章来源<span style="color: red;">(英文)</span></label>
				<div class="wg_input">
					<input name="source_US" type="text" style="width: 200px;" class="easyui-validatebox" validType="length[1, 50]" required="true"/>
				</div>
			</div>
			
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">文章正文</label>
				<div class="wg_input">
					<script type="text/plain" id="content" name="content">
    					这里输入中文文章
					</script>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">文章正文<span style="color: red;">(英文)</span></label>
				<div class="wg_input">
					<script type="text/plain" id="content_US" name="content_US">
    					这里输入英文文章
					</script>
				</div>
			</div>
			
			
			<div class="clear"></div>
			
			<div class="wg_item">
				<div class="wg_input" style="margin-left:110px;margin-top: 10px;">
					<a href="javascript:void(0)" onclick="$('#wg_form').submit()" class="easyui-linkbutton">发布文章</a>
				</div>
			</div>
			<div class="clear"></div>

		</form>
	</div>

	
	
<script type="text/javascript">
	
$(function() {
	//编辑器初始化
	var editorOption = {
		initialFrameWidth:670,
		initialFrameHeight:200,
		toolbars:[["insertimage","emotion","bold","italic","underline","forecolor","fontsize","fontfamily","insertunorderedlist","insertorderedlist","autotypeset"]]
	};
	var content = new UE.ui.Editor(editorOption);
	content.render("content");
	var content_us = new UE.ui.Editor(editorOption);
	content_us.render("content_US");
	
	//表单初始化
	initForm('#wg_form', 'ordinaryadmin/article_save', function() {
		$.messager.alert('文章发布', '发布成功', '', function() {
			var type = $("input[name='type']").val();
			var url = '<%=basePath%>admin/system/ordinary_admin/article-manage.jsp?type=' + type;
			loadInIframe(url);
		});
	});
});

	
</script>

	
</body>
</html>
