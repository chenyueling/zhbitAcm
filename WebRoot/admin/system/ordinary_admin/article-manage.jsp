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
	
	<script src="admin/js/adminUI.js" type="text/javascript"></script>
	
  </head>
  
  <body>
	<!-- 表格 -->
	<table id="xdatagrid">  
	</table>
	<!-- 工具栏 -->
	<div id="xtoolbar">  	
		<div style="padding: 3px;">
			<input id="ss" class="easyui-searchbox" searcher="doSearch" prompt="请输入关键字" menu="#xsearchSelect" style="width:300px"></input>  
			<div id="xsearchSelect" style="width:120px">  
			    <div name="title">标题</div>  
			    <div name="publisher.realname">上传管理员</div>
			    <div name="author">作者</div>    
			 </div>
			 <div style="float: right; text-align: right;" >
				<a href="admin/system/ordinary_admin/article-add.jsp" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="doEdit()">修改</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doDelete()">删除</a>
			</div>
		</div>
	</div>
	<!-- 右击菜单 -->
	<div id="xClickMenu" class="easyui-menu" style="width:120px;">
		<div data-options="iconCls:'icon-edit'" onclick="clickEdit()">修改</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'icon-reload'" onclick="doReload()">刷新</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'icon-remove'" onclick="clickDelete()">删除</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'icon-search'" onclick="viewArticle(clickData.id)">查看</div>
	</div>
	<style type="text/css">
		.ftitle {
			font-size:14px;  
            font-weight:bold;  
            padding:5px 0;  
            margin-bottom:10px;  
            border-bottom:1px solid #ccc;  
		}
		.flabel {
			font-size: 12px;
			text-align: right;;
		}
		.finput {
		}
	</style>
   
   </body>
<script type="text/javascript">
var dgTitle = "新闻管理";
var dgDeleteMsg = "确定要删除该文章吗?";
var dgBaseUrl = "ordinaryadmin/news_";
var dgDateUrl = dgBaseUrl + "list";
var dgAddUrl = dgBaseUrl + "save"
var dgUpdateUrl = dgBaseUrl + "update";
var dgDeleteUrl = dgBaseUrl + "delete";
var dgIdField = "id";
var dgParams = {};
var dgSortName = "createTime";
var dgSortOrder = "desc";
var dgColumns = [[
		{ field: 'id', 
			formatter:function(value, rowData, rowIndex){
				return " <input class='xid' type='checkbox' value='" + value +"'/>";
	  		}
	  	},
		{ title: '标题', field: 'title',sortable: false, resizable: true, width:260},
		{ title: '封面', field: 'imageUrl',sortable: false, resizable: true, width:160,
			formatter:function(value, rowData, rowIndex){
			return "<a href='" + value + "' target='_blank'><img style='width:165px; height:92px;' src='" + value + "'/></a>";
	  	}
	},
		{ title: '作者', field: 'author',sortable: true, resizable: true, width:155},
		{ title: '上传者', field: 'publisher',sortable: true, resizable: true, width:50},
		{ title: '发布时间', field: 'publishTime',sortable: false, resizable: true, width:110},
		{ title: '修改者', field: 'editer',sortable: true, resizable: true, width:50},
		{ title: '最后修改时间', field: 'editTime',sortable: false, resizable: true, width:110},
		{ title: '浏览量', field: 'pageView',sortable: false, resizable: true, width:50},
		{ title: '状态', field: 'status',sortable: false, resizable: true, width:50},
		{ title: '其他操作', field: 'operate',sortable: false, resizable: true, width:50,
			formatter:function(value, rowData, rowIndex){
				var html;
				if (rowData.status == '注销') {
					var html = "<a href='javascript:void(0)' onclick='activeItem(&quot;" + rowData.id + "&quot;)'>激活</a>";
				} else {
					var html = "<a href='javascript:void(0)' onclick='cancelItem(&quot;" + rowData.id + "&quot;)'>注销</a>";
				}
				return html;
	  		}
	  	}	
]];

//往edit表单load数据
function loadDialog(row) {
	var url = dgBaseUrl + 'editById?id=' + row.id;
	loadInIframe(url);
	//$.get(url);
}


$(function() {
	
});


function activeItem(id) {
	var url = dgBaseUrl + 'active';
	doConfirmPost(url, {id:id}, '确定要激活该文章?');
}


function cancelItem(id) {
	var url = dgBaseUrl + 'cancel';
	doConfirmPost(url, {id:id}, '确定要注销该文章?');
}

function deleteItem(id) {
	var url = dgBaseUrl + 'delete';
	doConfirmPost(url, {id:id}, '确定要删除该文章?');
}

function addArticle(){
	//var type = $("input[name='type']").val();
	var url ='<%=basePath%>admin/system/ordinary_admin/article-add.jsp';
	loadInIframe(url);
}

function viewArticle(id){
	var id = id;
	var url ="<%=basePath%>public/"+id+".html";
	window.open(url, "_blank"); 
}


</script>
  
</html>
