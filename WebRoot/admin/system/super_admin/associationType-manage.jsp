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
			    <div name="associationName">协会名称</div>  
			    <div name="associationType">协会简称</div>    
			 </div>
		</div>
		<div style="padding: 3px;padding-top: 0px;">
			<div style="float: left;">
			</div>
			<div style="float: right; text-align: right;" >
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="doAdd()">新增</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="doEdit()">修改</a>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<!-- 右击菜单 -->
	<div id="xClickMenu" class="easyui-menu" style="width:120px;">
		<div data-options="iconCls:'icon-edit'" onclick="clickEdit()">修改</div>
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
	<!-- 新增表单   2013/10/1 增加协会类型-->
    <div id="addDlg" class="easyui-dialog" style="padding:10px 20px"> 
        <div class="ftitle">添加协会</div>  
        <form id="addForm" method="post"> 
            <table width="100%">
            	<tr>
            		<td class="flabel">协会名称:</td>
            		<td class="finpur">
            			<input type="text" name="associationName" style="width: 200px;" class="easyui-validatebox" validType="length[2, 15]" required="true"/>
            		</td>
            	</tr>
            	<tr>
            		<td class="flabel">协会类型:</td>
            		<td class="finpur">
            			<input type="text" name="associationType" style="width: 200px;" class="easyui-validatebox" validType="length[2, 20]" required="true"/>
            		</td>
            	</tr>
            	<tr>
            		<td class="flabel">识别码:</td>
            		<td class="finpur">
            			<input type="text" name="mark" style="width: 200px;" class="easyui-validatebox" validType="length[10, 50]" required="true"/>
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
            		<td class="flabel">协会名称:</td>
            		<td class="finpur">
            			<input type="text" name="associationName" style="width: 200px;" class="easyui-validatebox" validType="length[2, 15]" />
            		</td>
            	</tr>
            	<tr>
            		<td class="flabel">协会类型:</td>
            		<td class="finpur">
            			<input type="text" name="associationType" style="width: 200px;" class="easyui-validatebox" validType="length[2, 20]" />
            		</td>
            	</tr>
            	<tr>
            		<td class="flabel">识别码:</td>
            		<td class="finpur">
            			<input type="text" name="mark" style="width: 200px;" class="easyui-validatebox" validType="length[10, 50]" />
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
var dgTitle = "协会管理";
var dgDeleteMsg = "确定要删除该协会?";
var dgBaseUrl = "superadmin/associationType_";
var dgDateUrl = dgBaseUrl + "list";
var dgAddUrl = dgBaseUrl + "save"
var dgUpdateUrl = dgBaseUrl + "update";
var dgDeleteUrl = dgBaseUrl + "delete";
var dgIdField = "id";
var dgParams = {};
var dgSortName = "associationName";
var dgSortOrder = "desc";
var dgColumns = [[
		{ field: 'id', 
			formatter:function(value, rowData, rowIndex){
				return " <input class='xid' type='checkbox' value='" + value +"'/>";
	  		}
	  	},
		{ title: '协会名称', field: 'associationName',sortable: false, resizable: true, width:125},
		{ title: '协会简称', field: 'associationType',sortable: false, resizable: true, width:125},
		{ title: '识别码', field: 'mark',sortable: false, resizable: true, width:125},
		{ title: '创建时间', field: 'createDate',sortable: true, resizable: true, width:125},
		{ title: '状态', field: 'status',sortable: false, resizable: true, width:50},
		{ title: '其他操作', field: 'operate',sortable: false, resizable: true, width:70,
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
	$('#editForm').form('load', {
		id:row.id,
        realname:row.realname,
        username:row.username
    });
	$("#editDlg").dialog('open');
}


$(function() {
	
});


function activeItem(id) {
	var url = dgBaseUrl + 'active';
	doConfirmPost(url, {id:id}, '确定要激活该协会?');
}


function cancelItem(id) {
	var url = dgBaseUrl + 'cancel';
	doConfirmPost(url, {id:id}, '确定要注销该协会?');
}

</script>
  
</html>
