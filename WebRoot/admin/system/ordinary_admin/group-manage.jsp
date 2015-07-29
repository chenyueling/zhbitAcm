<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<%@ include file="../../common/iclude_file.jsp"%>

		<script src="admin/js/adminUI.js" type="text/javascript"></script>

	</head>

	<body>
		<!-- 表格 -->
		<table id="xdatagrid">
		</table>
		<!-- 工具栏 -->
		<!-- 新增表单 -->
		<div id="xtoolbar">
			<div style="padding: 3px;">
				<input id="ss" class="easyui-searchbox" searcher="doSearch"
					prompt="请输入关键字" menu="#xsearchSelect" style="width: 300px"></input>
				<div id="xsearchSelect" style="width: 120px">
					<div name="engName">
						队伍名
					</div>
					<div name="contestname">
						比赛名
					</div>
				</div>
			</div>
			<div style="padding: 3px; padding-top: 0px;">
				<div style="float: left;">
				</div>
				<div style="float: right; text-align: right;">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-edit" plain="true" onclick="doEdit()">添加获奖情况</a>
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-cancel" plain="true" onclick="doDelete()">删除</a>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!-- 右击菜单 -->
		<div id="xClickMenu" class="easyui-menu" style="width: 120px;">
			<div data-options="iconCls:'icon-edit'" onclick="clickEdit()">
				添加获奖情况
			</div>
			<div data-options="iconCls:'icon-edit'" onclick="checking(clickData.id)">
				审核中
			</div>
			<div data-options="iconCls:'icon-edit'" onclick="pass(clickData.id)">
				审核通过
			</div>
			<div data-options="iconCls:'icon-edit'" onclick="unpass(clickData.id)">
				审核未通过
			</div>
			<div data-options="iconCls:'icon-edit'" onclick="finish(clickData.id)">
				完成比赛
			</div>
			<div data-options="iconCls:'icon-edit'" onclick="abort(clickData.id)">
				放弃比赛
			</div>
			<div data-options="iconCls:'icon-cancel'" onclick="clickDelete()">
				删除
			</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-reload'" onclick="doReload()">
				刷新
			</div>
		</div>
		<style type="text/css">
.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.flabel {
	font-size: 12px;
	text-align: right;;
}

.finput {
	
}
</style>


		<!-- 编辑表单 -->
		<div id="editDlg" class="easyui-dialog" style="padding: 10px 20px">
			<div class="ftitle">
				添加获奖情况
			</div>
			<form id="editForm" method="post">
				<input type="hidden" name="id" />
				<table width="100%">
					<tr>
						<td class="flabel">
							队伍名:
						</td>
						<td class="finpur">
							<input type="text" name="engName" style="width: 200px;" 
								class="easyui-validatebox" validType="length[2, 15]"
								required="true" />
						</td>
					</tr>
					
					</tr>
					<tr>
						<td class="flabel">
							获奖情况:
						</td>
						<td class="finpur">
							<input type="text" name="rewards" style="width: 200px;"
								class="easyui-validatebox"  />
						</td>
					</tr>
				</table>
			</form>
			<div id="editButtons">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-ok" onclick="javascript:$('#editForm').submit()">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-cancel"
					onclick="javascript:$('#editDlg').dialog('close')">关闭</a>
			</div>
		</div>


	</body>
<script type="text/javascript">
	var dgTitle = "比赛队伍管理";
	var dgDeleteMsg = "确定要删除该队伍?";
	var dgBaseUrl = "ordinaryadmin/group_";
	var dgDateUrl = dgBaseUrl + "list";
	var dgAddUrl = dgBaseUrl + "save";
	var dgUpdateUrl = dgBaseUrl + "addRewards";
	var dgDeleteUrl = dgBaseUrl + "delete";
	var dgIdField = "id";
	var dgParams = {};
	var dgSortName = "createTime";
	var dgSortOrder = "desc";
	var Row = null;
		var dgColumns = [ [
			{
				field : 'id',
				formatter : function(value, rowData, rowIndex) {
					return " <input class='xid' type='checkbox' value='" + value +"'/>";
				}
			},
			{
				title : '比赛名',
				field : 'contestname',
				sortable : false,
				resizable : true,
				width : 160
			},
			{
				title : '英文名',
				field : 'engName',
				sortable : false,
				resizable : true,
				width : 115
			},
			{
				title : '中文名',
				field : 'name',
				sortable : false,
				resizable : true,
				width : 105
			},
			{
				title : '获奖情况',
				field : 'rewards',
				sortable : false,
				resizable : true,
				width : 85
			},
			{
				title : '组队码',
				field : 'keyCode',
				sortable : true,
				resizable : true,
				width : 150
			},
			{
				title : '队长',
				field : 'captain',
				sortable : true,
				resizable : true,
				width : 60
			},
			{
				title : '队员1',
				field : 'player_1',
				sortable : true,
				resizable : true,
				width : 60
			},
			{
				title : '队员2',
				field : 'player_2',
				sortable : true,
				resizable : true,
				width : 60
			},
			{
				title : '审核状态',
				field : 'status',
				sortable : true,
				resizable : true,
				width : 80
			},
			{
				title : '创建时间',
				field : 'createTime',
				sortable : true,
				resizable : true,
				width : 100
			},
			{
				title : '最后修改时间',
				field : 'editTime',
				sortable : true,
				resizable : true,
				width : 100
			}, ] ];

	//往edit表单load数据
	function loadDialog(row) {
	$('#editForm').form('load', {
		id:row.id,
		engName:row.engName,
        rewards:row.rewards
	    });
		$("#editDlg").dialog('open');
	}

	$(function() {

	});


	
	function activeItem(id) {
		var url = dgBaseUrl + 'active';
		doConfirmPost(url, {
			id : id
		}, '确定要激活该比赛?');
	}

	function cancelItem(id) {
		var url = dgBaseUrl + 'cancel';
		doConfirmPost(url, {
			id : id
		}, '确定要注销该比赛?');
	}
	
	function checking(id) {
		var url = dgBaseUrl + 'editStatus?status=CHECKING';
		doConfirmPost(url, {
			id : id
		}, '确定更改为审核中?');
	}
	
	function pass(id) {
		var url = dgBaseUrl + 'editStatus?status=PASS';
		doConfirmPost(url, {
			id : id
		}, '确定更改为审核通过?');
	}
	
		function unpass(id) {
		var url = dgBaseUrl + 'editStatus?status=UNPASS';
		doConfirmPost(url, {
			id : id
		}, '确定更改为审核未通过?');
	}
		function finish(id) {
		var url = dgBaseUrl + 'editStatus?status=FINISH';
		doConfirmPost(url, {
			id : id
		}, '确定更改为完成比赛?');
	}
		function abort(id) {
		var url = dgBaseUrl + 'editStatus?status=ABORT';
		doConfirmPost(url, {
			id : id
		}, '确定更改为放弃比赛?');
	}

	

	
</script>

</html>