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
			    <div name="all">类型名</div>  
			    <div name="sports">所属类别</div>  
			 </div>
		</div>
		<div style="padding: 3px;padding-top: 0px;">
			<div style="float: left;">
				<select class="easyui-combobox"  style="width:150px">
					<option value="ALL">全部</option>
					<option value="ON_SELL">学生店用户</option>
					<option value="DOWNLOAD_SELLc">普通用户</option>
				</select>
				<a href="#" class="easyui-linkbutton">确定</a>
			</div>
			<div style="float: right; text-align: right;" >
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="doAdd()">新增</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="doEdit()">修改</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="doDelete()">删除</a>   
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<!-- 右击菜单 -->
	<div id="xClickMenu" class="easyui-menu" style="width:120px;">
		<div data-options="iconCls:'icon-edit'" onclick="clickEdit()">修改</div>
		<div data-options="iconCls:'icon-cancel'" onclick="clickDelete()">删除</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'icon-reload'" onclick="doReload()">刷新</div>
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
	<!-- 新增表单 -->
    <div id="addDlg" class="easyui-dialog" style="padding:10px 20px"> 
        <div class="ftitle">添加Banner</div>  
        <form id="addForm" method="post"> 
            <table width="100%">
            	<tr>
            		<td class="flabel">图片:</td>
            		<td class="finpur">
            			<input type="file" name="file" style="width: 200px;" />
            		</td>
            	</tr>
            	<tr>
            		<td class="flabel">标题:</td>
            		<td class="finpur">
            			<input type="text" name="title" style="width: 200px;" class="easyui-validatebox" required="true"/>
            		</td>
            	</tr>
            	<tr>
            		<td class="flabel">指向的Url:</td>
            		<td class="finpur">
            			<input type="text" name="url" style="width: 200px;" class="easyui-validatebox" required="true"/>
            		</td>
            	</tr>
            	<tr>
            		<td class="flabel">悬浮提示:</td>
            		<td class="finpur">
            			<input type="text" name="info" style="width: 200px;" class="easyui-validatebox" required="true"/>
            		</td>
            	</tr>
            	
            </table>
        </form>
        <div id="addButtons">  
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:$('#addForm').submit()">保存</a>  
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addDlg').dialog('close')">关闭</a>  
		</div>  
    </div>
    
    <!-- 编辑表单 -->
    <div id="editDlg" class="easyui-dialog" style="padding:10px 20px"> 
        <div class="ftitle">添加Banner</div>  
        <form id="editForm" method="post"> 
        	<input type="hidden" name="id"/>
            <table width="100%">
            	<tr>
            		<td class="flabel">图片:</td>
            		<td class="finpur">
            			<input type="file" name="file" style="width: 200px;" />
            		</td>
            	</tr>
            	<tr>
            		<td class="flabel">标题:</td>
            		<td class="finpur">
            			<input type="text" name="title" style="width: 200px;" class="easyui-validatebox" required="true"/>
            		</td>
            	</tr>
            	<tr>
            		<td class="flabel">指向的Url:</td>
            		<td class="finpur">
            			<input type="text" name="url" style="width: 200px;" class="easyui-validatebox" required="true"/>
            		</td>
            	</tr>
            	<tr>
            		<td class="flabel">悬浮提示:</td>
            		<td class="finpur">
            			<input type="text" name="info" style="width: 200px;" class="easyui-validatebox" required="true"/>
            		</td>
            	</tr>
            	
            </table>
        </form>
        <div id="editButtons">  
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:$('#editForm').submit()">保存</a>  
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#editDlg').dialog('close')">关闭</a>  
		</div>  
    </div>
   
    

  </body>
<script type="text/javascript">
var dgTitle = "Banner管理";
var dgDeleteMsg = "确定要删除该Banner?";
var dgBaseUrl = "superadmin/banner_";
var dgDateUrl = dgBaseUrl + "list";
var dgAddUrl = dgBaseUrl + "save"
var dgUpdateUrl = dgBaseUrl + "update";
var dgDeleteUrl = dgBaseUrl + "delete";
var dgIdField = "id";
var dgParams = {};
var dgSortName = "sort";
var dgSortOrder = "desc";
var dgColumns = [[
		{ field: 'id', 
			formatter:function(value, rowData, rowIndex){
				return " <input class='xid' type='checkbox' value='" + value +"'/>";
	  		}
	  	},
		{ title: '标题', field: 'title',sortable: true, resizable: true, width:125},
		{ title: '图片', field: 'imgUrl',sortable: false, resizable: true, width:125,
			formatter:function(value, rowData, rowIndex){
				return "<img style='width:125px; height:75px;' src='" + value + "'/>";
		  	}
		},
		{ title: '悬浮提示', field: 'info',sortable: false, resizable: true, width:125},
		{ title: '指向的URL', field: 'url',sortable: false, resizable: true, width:300},
		{ title: '其他操作', field: 'operate',sortable: false, resizable: true, width:70,
			formatter:function(value, rowData, rowIndex){
				var html = "<a href='javascript:void(0)' onclick='upItem(&quot;" + rowData.id + "&quot;,&quot;" + rowData.index +  "&quot;)'>上移</a><br/>";
				html = html + "<a href='javascript:void(0)' onclick='downItem(&quot;" + rowData.id + "&quot;,&quot;" + rowData.index +  "&quot;)'>下移</a><br/>";
				return html;
	  		}
	  	}	
]];

//往edit表单load数据
function loadDialog(row) {
	$('#editForm').form('load', {
		id:row.id,
        title:row.title,
        url:row.url,
        info:row.info
    });
	$("#editDlg").dialog('open');
}


$(function() {
	
});

//上移
function upItem(id, index) {
	var url = dgBaseUrl + 'operate';
	doConfirmPost(url, {operateType:'UP', id:id, index:index}, '确定要上移该图片?');
}

//下移
function downItem(id, index) {
	var url = dgBaseUrl + 'operate';
	doConfirmPost(url, {operateType:'DOWN', id:id, index:index}, '确定要下移该图片?');
}

</script>
  
</html>
