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
		<div id="xtoolbar">
			<div style="padding: 3px;">
				<input id="ss" class="easyui-searchbox" searcher="doSearch"
					prompt="请输入关键字" menu="#xsearchSelect" style="width: 300px"></input>
				<div id="xsearchSelect" style="width: 120px">
					<div name="name">
						比赛名
					</div>

				</div>
			</div>
			<div style="padding: 3px; padding-top: 0px;">
				<div style="float: left;">
				</div>
				<div style="float: right; text-align: right;">
					<a href="admin/system/ordinary_admin/competition-add.jsp"
						class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-edit" plain="true" onclick="doEdit()">修改</a>
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-cancel" plain="true" onclick="doDelete()">删除</a>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<!-- 右击菜单 -->
		<div id="xClickMenu" class="easyui-menu" style="width: 120px;">
			<div data-options="iconCls:'icon-edit'" onclick="clickEdit()">
				修改
			</div>
			<div data-options="iconCls:'icon-edit'" onclick="startApply(clickData.id)">
				开始报名		
			</div>
			<div data-options="iconCls:'icon-edit'" onclick="endApply(clickData.id)">
				结束报名
			</div>
			<div data-options="iconCls:'icon-edit'" onclick="startContest(clickData.id)">
				开始比赛
			</div>
			<div data-options="iconCls:'icon-edit'" onclick="endContest(clickData.id)">
				结束比赛
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

	</body>
	<script type="text/javascript">
	var dgTitle = "比赛管理";
	var dgDeleteMsg = "确定要删除该比赛?";
	var dgBaseUrl = "ordinaryadmin/competition_";
	var dgDateUrl = dgBaseUrl + "list";
	var dgAddUrl = dgBaseUrl + "save";
	var dgUpdateUrl = dgBaseUrl + "update";
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
				field : 'name',
				sortable : false,
				resizable : true,
				width : 125
			},
			{
				title : '比赛地址',
				field : 'address',
				sortable : false,
				resizable : true,
				width : 125
			},
			{
				title : '开始时间',
				field : 'startTime',
				sortable : true,
				resizable : true,
				width : 80
			},
			{
				title : '结束时间',
				field : 'endTime',
				sortable : true,
				resizable : true,
				width : 80
			},
			{
				title : '比赛形式',
				field : 'maxPlayer',
				sortable : true,
				resizable : true,
				width : 60
			},
			{
				title : '比赛类别',
				field : 'type',
				sortable : true,
				resizable : true,
				width : 60
			},
			{
				title : '允许报名',
				field : 'registerContest',
				sortable : true,
				resizable : true,
				width : 60
			},
			{
				title : '比赛状态',
				field : 'status',
				sortable : true,
				resizable : true,
				width : 60
			},
			{
				title : '创建时间',
				field : 'createTime',
				sortable : true,
				resizable : true,
				width : 80
			},
			{
				title : '最后修改时间',
				field : 'editTime',
				sortable : true,
				resizable : true,
				width : 100
			},
			{
				title : '状态',
				field : 'isActive',
				sortable : false,
				resizable : true,
				width : 50
			},
			{
				title : '其他操作',
				field : 'operate',
				sortable : false,
				resizable : true,
				width : 70,
				formatter : function(value, rowData, rowIndex) {
					var html;
					if (rowData.isActive == '注销') {
						var html = "<a href='javascript:void(0)' onclick='activeItem(&quot;"
								+ rowData.id + "&quot;)'>激活</a>";
					} else {
						var html = "<a href='javascript:void(0)' onclick='cancelItem(&quot;"
								+ rowData.id + "&quot;)'>注销</a>";
					}
					return html;
				}
			} ] ];

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

	function startApply(id) {
		
		var row = $("#xdatagrid").datagrid('getSelected');
		var url = dgBaseUrl + 'startApply';
		doConfirmPost(url, {
			id : id
		}, '确定要开始该场比赛报名?');
	}

	function endApply(id) {
		var url = dgBaseUrl + 'endApply';
		doConfirmPost(url, {
			id : id
		}, '确定要结束该场比赛报名?');
	}

	function startContest(id) {
		var url = dgBaseUrl + 'startContest';
		doConfirmPost(url, {
			id : id
		}, '确定要开始该场比赛?');
	}

	function endContest(id) {
		var url = dgBaseUrl + 'endContest';
		doConfirmPost(url, {
			id : id
		}, '确定要结束该场比赛?');
	}
</script>

</html>
