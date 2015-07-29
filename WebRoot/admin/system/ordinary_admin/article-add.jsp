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
	<div id="p" class="easyui-panel" title="新闻发布" style="padding:10px;">
		<form id="wg_form" class="wg_form">
			<div class="clear"></div>
			
			<div class="wg_item">
				<label for="name">文章标题</label>
				<div class="wg_input">
					<input name="title" type="text" style="width: 400px;" class="easyui-validatebox" validType="length[1, 50]" required="true"/>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">文章封面</label>
				<div class="wg_input">
					<input name="image" type="file"/><br/>
					<span style="color:red">图片建议大小为16:9</span>
				</div>
			</div>
		   <div class="clear"></div>
			<div class="wg_item">
				<label for="name">文章简述</label>
				<div class="wg_input">
					<textarea style="width: 400px; height: 100px;" name="smallTitle" class="easyui-validatebox" validType="length[1, 200]"></textarea>
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
				<label for="name">文章来源</label>
				<div class="wg_input">
					<input name="source" type="text" style="width: 200px;" class="easyui-validatebox" validType="length[1, 50]" required="true"/>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">文章正文</label>
				<div class="wg_input">
					<script style="padding: 0px;" type="text/plain" id="content" name="content"></script>
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
	window.UEDITOR_CONFIG.imageUrl = "<%=basePath%>ordinaryadmin/articleimage_save";
	window.UEDITOR_CONFIG.imageManagerUrl = "<%=basePath%>ordinaryadmin/articleimage_list";
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
	var content = new UE.ui.Editor(editorOption);

	content.addListener('uploadCompleteCallback', function(evt, data) {
		 var html = "<input type='hidden' value='" + data.id + "' name='articleImage'/>";
		 $('#wg_form').append(html);
	});
	
	content.render("content");
	
	//表单初始化
	initForm('#wg_form', 'ordinaryadmin/news_save', function() {
		$.messager.alert('文章发布', '发布成功', '', function() {
			var url = '<%=basePath%>admin/system/ordinary_admin/article-manage.jsp';
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
