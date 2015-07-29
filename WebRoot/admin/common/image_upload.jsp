<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script src="admin/js/jquery.form.js" type="text/javascript"></script>
<style type="text/css">
/* 上传专用 */
.u-file-btn {
        position: relative; 
        direction:ltr; 
        height:90px; 
        overflow:hidden;
        line-height: 15px;  
        margin: 0 auto; 
        padding:3px 0; 
        text-align: center;  
        width:230px;
}
.u-file-btn img {
	border:none;
}

.u-file-btn input{
        cursor: pointer; text-align: right; z-index: 10; font-size: 118px; /* font-size: 118px 工作正常 */
        position: absolute; top: 0px; right:0px; opacity: 0; filter: Alpha(opacity:0);
}
.u-image-show {
	margin-left:90px;
	margin-top:5px;
	width:250px;
	height:250px;
	margin: 0 auto;
}
.u-image-show img {
	border:1px silver solid;
	padding:3px;
}

</style>
<div id="dlg" title="请选择文件" style="padding:10px">
	<form id="u-image-upload"  method="post" enctype="multipart/form-data">
		<div class="u-file-c">
			<input type="file" id="u-file" name="image" alt="本地上传"/>
			<a href="#" id="u-upload-img"><img src="admin/images/upload.gif"</a> </a>
		</div>
		<div class="u-image-show">
		</div>
	</form>
</div>
<script type="text/javascript">
$(function() {
	$('.u-file-c').addClass('u-file-btn');
	$('#u-file').hover(function() {
		$('#u-upload-img').find('img').attr("src","admin/images/upload_hover.gif");
	}, function() {
		$('#u-upload-img').find('img').attr("src","admin/images/upload.gif");
	});
	
	$('#u-image-upload').ajaxForm({
		type : "post",
		url : "upload/userImageManage_save",
		dataType : 'json',
		success : function(json) {
			var tip = json.tip;
			var image = json.image;
			if (tip.message != "") {
				alert(tip.message);
				return;
			}
			var imgHtml = "<img id='u-image' src='" + image.path + "'/>"
				+ "<input type='hidden' id='u-image-id' value='" + image.id + "'>";

			$(".u-image-show").html(imgHtml);
			
			$("#u-image").load(function() {
				var imgHeight = $("#u-image").height();
				var imgWidth = $("#u-image").width();
				var m_left = 0;
				var m_top = 0;
				if (imgHeight > 250 || imgWidth >250) {
					var rank;
					if (imgWidth > imgHeight) {
						rank = imgWidth/250;
						imgHeight = imgHeight/rank;
						m_top = (248-imgHeight)/2;
						imgWidth = 250;
					} else {
						rank = imgHeight/250;
						imgWidth = imgWidth/rank;
						m_left = (248-imgWidth)/2;
						imgHeight = 250;
					}	
				} else {
					m_top = (248-imgHeight)/2;
					m_left = (248-imgWidth)/2; 
				}
				
				$('.u-image-show img').css({ 
					width: imgWidth + 'px', 
					height: imgHeight + 'px',
					margin: m_top + 'px ' + m_left + 'px'
				});
			});
			
			
			

		}
	});

	$("#u-file").change(function() {
		$('#u-image-upload').submit();
	});
});

</script>