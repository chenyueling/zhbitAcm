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
	
  </head>
  
  <body>
	<!-- 表格 -->
	<table id="dg" title="学生店商品管理" style="height:500px">  
		<div id="toolbar">  	
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="showItem()">查看详情</a> 
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="editItem()">编辑</a>  
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="onSellItem()">上架</a>  
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="downloadSellItem()">下架</a>  
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="deleteItem()">删除</a> 
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="upTopItem()">置顶商品</a> 
	    </div>
	    <thead>  
	        <tr>  
	            <th field="name" width="100px">商品名称</th>  
				<th field="code" width="100px">商品编号</th>
				<th field="type" width="100px">商品类型</th>
				<th field="goodsType" width="100px">商品类别</th>
				<th field="twoPrice" width="100px">市场价/售价</th>
				<th field="status" width="100px">状态</th>
				<th field="onSellTime" width="100px">上架时间</th>
				<th field="upTopTime" width="100px">置顶时间</th>
				<th field="reviseTime" width="100px">最后修改时间</th>
	        </tr>
	    </thead>  
	</table>
    
	
  </body>
  <script type="text/javascript">
   $(function() {
		//表格的初始化
		 $('#dg').datagrid({
		    pageSize: 15,//每页显示的记录条数，默认为10 
		    pageList: [10,15,20],//可以设置每页记录条数的列表 
		    nowrap: true, //True 就会把数据显示在一行里,默认为True
		    striped: true, //True 就把行条纹化。（即奇偶行使用不同背景色）,默认false
		    border: true, 
		    url:'student/storeGoods_list', 
		    remoteSort:false,  //定义是否从服务器给数据排序.
		    idField:'id', //标识字段。
		    singleSelect:true,//是否单选 
		    pagination:true,//分页控件 
		    rownumbers:true,//行号 
		    toolbar:"#toolbar",
		    fitColumns:true,  //True 就会自动扩大或缩小列的尺寸以适应表格的宽度并且防止水平滚动。
		    onLoadSuccess:function(data) {//读取成功后调用
				var tip = data.tip;
				if (tip.message != "") {
					$.messager.alert('学生店管理', tip.message);
				}
		 	}	
		 });

		$(window).resize(function() {
			$("#dg").datagrid("resize");
		});
	});
	var data;
	function approve() {
		
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open');
			$.getJSON("student_admin/studentStoreApplyManage_getApplyDetail", {id:row.id}, function(json) {
				data = json.student;
				completeForm('#fm', data);
			});
		} else {
			$.messager.alert('学生店管理', '请选择项');
		}
		
		
	}

	//上架
	function onSellItem() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('学生店管理', '确定要上架该商品', function(r) {
				if (r) {
					$.getJSON("student/storeGoods_operate?operateType=ON_SELL", {id:row.id}, function(json) {
						var tip = json.tip;
						if (tip.message != "") {
							$.messager.alert('学生店管理', tip.message);
							return;
						} else {
							$.messager.alert('学生店管理', '上架成功');
							$('#dg').datagrid('reload');
						}
					});
				}
			});
			
		} else {
			$.messager.alert('学生店管理', '请选择项');
		}
	}

	//下架
	function downloadSellItem() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('学生店管理', '确定要下架该商品', function(r) {
				if (r) {
					$.getJSON("student/storeGoods_operate?operateType=DOWNLOAD_SELL", {id:row.id}, function(json) {
						var tip = json.tip;
						if (tip.message != "") {
							$.messager.alert('学生店管理', tip.message);
							return;
						} else {
							$.messager.alert('学生店管理', '下架成功');
							$('#dg').datagrid('reload');
						}
					});
				}
			});
		} else {
			$.messager.alert('学生店管理', '请选择项');
		}
	}
	
	//删除
	function deleteItem() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('学生店管理', '确定要删除该商品', function(r) {
				if (r) {
					$.getJSON("student/storeGoods_operate?operateType=DELETE", {id:row.id}, function(json) {
						var tip = json.tip;
						if (tip.message != "") {
							$.messager.alert('学生店管理', tip.message);
							return;
						} else {
							$.messager.alert('学生店管理', '删除成功');
							$('#dg').datagrid('reload');
						}
					});
				}
			});
		} else {
			$.messager.alert('学生店管理', '请选择项');
		}
	}

	
	function showItem() {
		
	}
	
	function editItem() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			var url = "student/storeGoods_edit?id=" + row.id;
			window.location.href = '<%=basePath%>' + url;
		} else {
			$.messager.alert('学生店管理', '请选择项');
		} 
	}

	//置顶
	function upTopItem() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('学生店管理', '确定要置顶该商品', function(r) {
				if (r) {
					$.getJSON("student/storeGoods_upTop", {id:row.id}, function(json) {
						var tip = json.tip;
						var upTopCount = json.upTopCount;
						if (tip.message != "") {
							$.messager.alert('学生店管理', tip.message);
							return;
						} else {
							$.messager.alert('学生店管理', '置顶成功,今日您还剩下 ' + upTopCount + '置顶次数');
							$('#dg').datagrid('reload');
						}
					});
				}
			});
		} else {
			$.messager.alert('学生店管理', '请选择项');
		}
	}
	
   
   

   	

  </script>
  
</html>
