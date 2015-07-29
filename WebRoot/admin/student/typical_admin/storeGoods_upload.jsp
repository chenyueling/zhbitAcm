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
		
	</style>
  </head>
  
<body bgcolor="#F8F8F8">
	<div id="p" class="easyui-panel" title="上传学生店商品" style="padding:10px;">
		<form id="wg_form" class="wg_form">
			<div class="wg_item">
				<label>新旧程度</label>
				<div class="wg_input">
					<input type="radio" name="type" value="NEW"> 全新
					<input type="radio" name="type" value="SECOND_HAND"> 二手
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label>类别</label>
				<div class="wg_input">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('open')">选择类别</a>
					<b id="type_name"></b>
					<input type="hidden" name="goodsType"/>
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="name">商品名称</label>
				<div class="wg_input">
					<input id="name" name="name" type="text" style="width: 400px;" />
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="marketPrice">市场价</label>
				<div class="wg_input">
					<input id="marketPrice" name="marketPrice" type="text" />
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="price">价格</label>
				<div class="wg_input">
					<input id="price" name="price" type="text" />
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="count">数量</label>
				<div class="wg_input">
					<input id="count" name="count" type="text" />
				</div>
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label for="inputEmail">商品图片</label>
				<div class="wg_input">
					<a href="#" onclick="javascript:$('#img_manager').dialog('open');return false" class="easyui-linkbutton">上传商品图片</a>
				</div>
			</div>
			<div class="clear"></div>
			<div class="image_show" style="padding-left:110px;">
				<!-- 这里插入图片 -->
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<label>商品描述</label>
				<div class="wg_input" style="margin-left:110px;">
					<script type="text/plain" id="content" name="info">
    					在这里输入商品描述
					</script>
				</div>
				
			</div>
			<div class="clear"></div>
			<div class="wg_item">
				<div class="wg_input" style="margin-left:110px;margin-top: 10px;">
					<a href="#" id="submit" onclick="javascript:void(0)" class="easyui-linkbutton">发布商品</a>
				</div>
			</div>
			<div class="clear"></div>

		</form>
	</div>

<!-- 类型选择窗口 -->
<div id="dlg" class="easyui-dialog"
		title="选择类别" 
		style="width:400px;height:200px;padding:10px 20px"  
        closed="true" 
        buttons="#dlg-buttons" 
        data-options="iconCls:'icon-save',resizable:false,modal:true"> 
         
    <div class="ftitle">选择商品类别</div>  
        <div class="fitem">
        	<p style="margin-right:20px;display: inline;">商品类别</p>  
            <select id="firstLevel">
            </select>
            <select id="secondLevel" name="secondLevelType" style="display: none;">
            </select>
        </div>
    </form>  
</div>
<div id="dlg-buttons"> 
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onclick="insertType()">确定</a> 
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">关闭</a>  
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
	var isFirst = true;
	//编辑器初始化
	var editorOption = {
		initialFrameWidth:670,
		initialFrameHeight:200,
		toolbars:[["insertimage","emotion","bold","italic","underline","forecolor","fontsize","fontfamily","insertunorderedlist","insertorderedlist","autotypeset"]]
	};
	var editor = new UE.ui.Editor(editorOption);
	editor.render("content");

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
		
		var html = "";
		if (isFirst) {
			isFirst = false;
			html = "<div class='image_cell'><div class='image_body'><a href='javascript:void(0)'><img src='"
				+ imgPath + "'/></a></div><div class='image_info'><p><p>商品封面</p></p></div><div class='cover'>" 
				+ "<a href='javascript:void(0)' class='delete'>删除</a>" 
				+ "</div><input type='hidden' name='frontImage' value='"
				+ imgId + "'/></div>"
		} else {
			html = "<div class='image_cell'><div class='image_body'><a href='javascript:void(0)'><img src='"
				+ imgPath + "'/></a></div><div class='image_info'><p><p>商品图片</p></p></div><div class='cover'>" 
				+ "<a href='javascript:void(0)' class='delete'>删除</a><a href='javascript:void(0)' class='setFornt'>设为封面</a>" 
				+ "</div><input type='hidden' name='images' value='"
				+ imgId + "'/></div>"
		}
			

		$(".image_show").append(html);
		$(".image_show").find('img').fixParent({width:150, height:150});
		//工具类浮现效果
		$('.image_cell').hover(function(){
			$(".cover", this).stop().animate({top:'-25px'},{queue:false,duration:300});
		}, function() {
			$(".cover", this).stop().animate({top:'0px'},{queue:false,duration:300});	
		});

		$('.delete').bind('click', function() {
			$(this).parent().parent().remove();
		});

		$('.setFornt').bind('click', function() {
			setFornt(this);
		});
		
	}

	//将类型插入页面
	function insertType() {
		var id = $('#firstLevel').val();
		var str = "option[value='" + id + "']";
		var first = $(str).text();
		id = $('#secondLevel').val();
		str = "option[value='" + id + "']";
		var second = $(str).text();
		var ans = first + "-" + second;
		$('#type_name').html(ans);
		$("input[name='goodsType']").val(id);
		$('#dlg').dialog('close');
	}

	//设置封面图片
	function setFornt(oj) {
		for (var i=0; i<$('.image_info').length; i++) {
			var info = $('.image_info')[i];
			$(info).html("<p>商品图片</p>");
		}
		$("input[name='imageId']").removeClass('frontImage');
		$("input[name='frontImage']").attr('name', 'images')
		$(".setFornt").show();
		
		$(oj).parent().find('.setFornt').hide();
		$(oj).parent().prev().html("<p>商品封面</p>");
		$(oj).parent().next().addClass('frontImage');
		$(oj).parent().next().attr('name', 'frontImage');
	}

$(function() {
		
		//载入一级分类
		$.getJSON('common/firstLevelType_selectList?listType=STUDENT', function(json) {
			var select = json.select;
			for (var i=0; i<select.length; i++) {
				var html = "<option value='" + select[i].key + "'>" + select[i].value +"</>";
				$('#firstLevel').append(html);
			}
		});

		//二级分类
		$('#firstLevel').bind('change', function() {
			var id = $('#firstLevel').val();
			$('#secondLevel').html("");
			$.getJSON('common/secendLevelType_selectList', {id:id}, function(json) {
				var select = json.select;
				for (var i=0; i<select.length; i++) {
					var html = "<option value='" + select[i].key + "'>" + select[i].value +"</>";
					$('#secondLevel').append(html);
				}
				$('#secondLevel').show();
			});
		});

		//表单初始化
		$('#wg_form').ajaxForm({
			type : "post",
			url : "student/storeGoods_save",
			dataType : 'json',
			success : function(json) {
				var tip = json.tip ;
				if (tip.message != "") {
					$.messager.alert('学生店商品管理', tip.message);
					return;
				} else {
					$.messager.alert('学生店商品管理', '上传成功');
				}
			}
		});	

		$("#submit").bind("click", function() {
			editor.sync();
			$('#wg_form').submit();
			return false;
		});

		

		
});

	
</script>

	
</body>
</html>
