<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
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
		}
		.wg_input a {
			color:break;
		}	

		.wg_label {
			float:left;
			margin-right: 10px;
		}
		.image_show img {
	
		}
		.ftitle{  
            font-size:14px;  
            font-weight:bold;  
            padding:5px 0;  
            margin-bottom:10px;  
            border-bottom:1px solid #ccc;  
        }  
        .fitem{  
            margin-bottom:5px;  
        }  
        .fitem label{  
            display:inline-block;  
            width:80px;  
        } 
        #type_name {
        	font-weight: normal;
        	margin-left: 20px;
        }
        .wg_box1 {
        	width: 400px;
        	float: left;
        	padding-left: 40px;
        	padding-top: 20px;
        }
         .wg_box2 {
        	width: 200px;
        	float: left;
        	
        }
        .wg_logo {
        	width: 200px;
        	text-align: center;
        	margin: 20px;
        }
        .wg_logo img {
        	height: 200px;
        	width: 200px;
        	border: 2px solid silver;;
        	padding: 2px;
        	margin-bottom: 10px;
        }
		
	</style>
  </head>
  
<body bgcolor="#F8F8F8">
	<div id="p" class="easyui-panel" title="学生店信息" style="padding:10px;">
		<div class="wg_box2">
			<div class="wg_logo">
				<img id="logo" src="<s:property value="#request.store.logoPath"/>"/>
			
				<a href="javascript:void(0)" id="submit" onclick="javascript:$('#img_manager').dialog('open')" class="easyui-linkbutton">上传Logo</a>
			</div>
		</div>
		<div class="wg_box1">
		
			<div class="wg_item">
				<label for="name">店名</label>
				<div class="wg_input">
					<input type="hidden" name="id" value="<s:property value="#request.store.id"/>" />
					<input type="text" name="storename" value="<s:property value="#request.store.storename"/>"/>
					<input type="hidden" name="storenameTmp" value="<s:property value="#request.store.storename"/>" />
					<a id="updateStorename" onclick="javascript:void(0)" class="easyui-linkbutton">更新</a>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">店主</label>
				<div class="wg_input">
					<b><s:property value="#request.store.ownername"/></b>
				</div>
			</div>
			<div class="clear"></div>

			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">商品数量</label>
				<div class="wg_input">
					<b><s:property value="#request.store.goodsCount"/> / <s:property value="#request.store.totalGoodsCount"/></b>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">商品顶置次数</label>
				<div class="wg_input">
					<b><s:property value="#request.store.upTopCount"/> / <s:property value="#request.store.totalUpTopCount"/></b>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">商店顶置次数</label>
				<div class="wg_input">
					<b><s:property value="#request.store.upTopStoreCount"/> / <s:property value="#request.store.totalUpTopStoreCount"/></b>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">创建时间</label>
				<div class="wg_input">
					<b><s:date name="#request.store.createTime" format="yyyy-MM-dd HH:mm:ss"/></b>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">状态</label>
				<div class="wg_input">
					<b><s:property value="#request.store.status"/></b>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name"><s:property value="#request.store.status"/>时间</label>
				<div class="wg_input">
					<b><s:date name="#request.store.statusTime" format="yyyy-MM-dd HH:mm:ss"/></b>
				</div>
			</div>
			<div class="clear"></div>

		</div>
		
	</div>
	
<!-- 上传组件  -->
<div id="img_manager" class="easyui-dialog"
			title="图片上传" 
    		style="width:630px;"  
            closed="true" 
            buttons="#img-buttons" 
            data-options="iconCls:'icon-save',resizable:false,modal:true">
	<div id="img_tabs" class="easyui-tabs" style="height:420px">
		<div title="本地上传" data-options="href:'admin/common/image_upload.jsp'" style="padding:10px"></div>
		<div title="在线管理" data-options="href:'admin/common/image_list.jsp'" style="padding:10px"></div>
	</div>
</div>
<div id="img-buttons">  
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="insertImage()">插入</a>  
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#img_manager').dialog('close')">取消</a>  
</div>

	
	
<script type="text/javascript">
	
$(function() {

		//表单初始化
		$('#wg_form').ajaxForm({
			type : "post",
			url : "student/student_update",
			dataType : 'json',
			success : function(json) {
				var tip = json.tip ;
				if (tip.message != "") {
					$.messager.alert('学生个人管理', tip.message);
					return;
				} else {
					$.messager.alert('学生个人管理', '更新个人信息成功');
				}
			}
		});	

		$("#submit").bind("click", function() {
			$('#wg_form').submit();		
			return false;
		});

		$("#updateStorename").bind("click", function() {
			var storename = $("input[name='storename']").val();
			var id = $("input[name='id']").val();
			$.ajax({
				type: "POST",
				url: "student/studentStore_updateStorename",
				data: {id:id, storename:storename},
				dataType: "json",
				success: function(json){
					var isStorenameExist = json.isStorenameExist;
					var tip = json.tip;
					if (isStorenameExist == true) {
						$("input[name='storename']").val($("input[name='storenameTmp']").val());
					}
					if (tip.message != "") {
						$.messager.alert('学生店管理', tip.message);
					} else {
						$.messager.alert('学生店管理', '更新成功');
						$("input[name='storenameTmp']").val(storename);
					}
				}
			});
		});
	
});

function insertImage() {
	var tab = $('#img_tabs').tabs('getSelected');
	var index = $('#img_tabs').tabs('getTabIndex',tab);
	var imgId;
	var imgPath;
	if (index == 0) {
		imgId = $('#u-image-id').val();
		imgPath = $('#u-image').attr('src');
		if (imgId == null) {
			$.messager.alert('图片管理', '未上传图片');
			return false;
		}
	} else {
		imgId = $('.u_select').find('img').attr('id');
		imgPath = $('.u_select').find('img').attr('src');

		if (imgId == null) {
			$.messager.alert('图片管理', '未选中图片');
			return false;
		}
	}
	$('#img_manager').dialog('close');

	//对已经获取的id与path做操作	
	var id = $("input[name='id']").val();
	$.getJSON('student/studentStore_updateLogo', {id:id, logoId:imgId}, function(json) {
		var tip = json.tip;
		if (tip.message != "") {
			$.messager.alert('学生店管理', tip.message);
		} else {
			$.messager.alert('学生店管理', '更新Logo成功');
			$('#logo').attr('src', imgPath);
		}
	});
	
	
	
}

	
</script>

	
</body>
</html>
