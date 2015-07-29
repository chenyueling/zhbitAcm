
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
	<div id="p" class="easyui-panel" title="比赛更新" style="padding:10px;">
		<form id="wg_form" class="wg_form">
			<div class="clear"></div>
			<input type="hidden" name="id" 
				value="<s:property value="#request.competition.id" />" />
			<div class="wg_item">
				<label for="name">比赛名称</label>
				<div class="wg_input">
					<input name="name" type="text" style="width: 400px;" 
					value="<s:property value="#request.competition.name" />"
					class="easyui-validatebox" validType="length[1, 200]" required="true"/>
				</div>
			</div>
			<div class="wg_item">
				<label for="name">比赛地点</label>
				<div class="wg_input">
					<input name="address" type="text" style="width: 400px;" 
					value="<s:property value="#request.competition.address" />"
					class="easyui-validatebox" validType="length[1, 500]" required="true"/>
				</div>
			</div>
			<div class="wg_item">
				<label for="name">开始时间</label>
				<div class="wg_input">
					<input name="startTime" type="text" value="<s:property value="#request.competition.startTime" />" 
					style="width: 400px;" class="easyui-validatebox" validType="length[6, 50]" required="true"/>
				</div>
			
			</div>
			<div class="wg_item">
				<label for="name">结束时间</label>
				<div class="wg_input">
					<input name="endTime" type="text" value="<s:property value="#request.competition.endTime" />"
					 style="width: 400px;" class="easyui-validatebox" validType="length[6, 50]" required="true"/>
				</div>
			</div>
				
		
			<div class="wg_item">
				<label for="name">比赛类别</label>
				<div class="wg_input">
					<input name="type" type="text" 
					value="<s:property value="#request.competition.type" />"
					style="width: 400px;" class="easyui-validatebox" 
					validType="length[1, 200]" required="true"/>
				</div>
			</div>	
			
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">文章正文</label>
				<div class="wg_input">
					<script style="padding: 0px;" type="text/plain" id="info" name="info"><s:property value="#request.competition.info" escape="false"/></script>
				</div>
			</div>
			
			<div class="clear"></div>
			
			<div class="wg_item">
				<div class="wg_input" style="margin-left:110px;margin-top: 10px;">
					<a href="javascript:void(0)" onclick="$('#wg_form').submit()" class="easyui-linkbutton">更新比赛</a>
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
		toolbars:[['fullscreen', 'source', '|', 'undo', 'redo', '|',
	                'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
	                'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
	                'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
	                'directionalityltr', 'directionalityrtl', 'indent', '|',
	                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
	                'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
	                'insertimage', 'emotion', 'insertvideo', 'map', 'gmap', 'insertframe','insertcode', 'webapp', '|',
	                'horizontal', 'date', 'time', 'spechars', '|',
	                'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', '|',
	                'searchreplace']]
	};
	var info = new UE.ui.Editor(editorOption);

	
	info.render("info");
	
	//表单初始化
	initForm('#wg_form', 'ordinaryadmin/competition_update', function() {
		$.messager.alert('比赛更新', '更新成功', '', function() {
			var url = '<%=basePath%>admin/system/ordinary_admin/competition-manage.jsp';
			loadInIframe(url);
		});
	});

		

	
});



/**
 * 根据iframe对象获取iframe的window对象
 * @param frame
 * @returns {Boolean}
 */
function GetFrameWindow(frame){
    return frame && typeof(frame)=='object' && frame.tagName == 'IFRAME' && frame.contentWindow;
}

</script>

	
</body>
</html>
