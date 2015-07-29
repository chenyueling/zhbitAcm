<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--My CSS-->
<style type="text/css">  
.image_cell_min {
	width:130px;
	height:130px;
	overflow: hidden;
	border:1px solid silver;
	float: left;
	margin: 8px;
}
.image_cell_min:hover {
	border:3px solid #2F80DC;
	height:126px;
	width:126px;

}
.u_select {
	border:3px solid #2F80DC;
	height:126px;
	width:126px;
}
.image_body_min {
	height:110px;
	width: 100%;
	overflow: hidden;
}
.image_body_min a{
	display: block;
	height: 100%;
	width: 100%;
}
.image_body_min img{	
	display:block;
	margin: auto auto;
	
}
.image_info_min {
	overflow:hidden;
	text-align:center;
	border-top:1px solid silver;
}

</style>
<script type="text/javascript">
$(function() {
	$('#pagination').pagination({  
	    total:2000,  
	    pageSize:10,
	    showPageList: false, 
	    onSelectPage:function(pageNumber, pageSize){
			$(this).pagination('loading');
			$.getJSON("upload/userImageManage_getImages", {page:pageNumber, rows:8}, function(json) {
				var tip = json.tip;
				if (tip.message != "") {
					$.messager.alert("图片管理", tip.message);
					return;
				}
				
				$('#pagination').pagination({
					total: json.total,
					pageNumber: pageNumber
				});
				
				loadDate(json.rows);
			});
			$(this).pagination('loaded');
		}
	});


	$.getJSON("upload/userImageManage_getImages", {page:1, rows:8}, function(json) {
		var tip = json.tip;
		if (tip.message != "") {
			$.messager.alert("图片管理", tip.message);
			return;
		}
		
		$('#pagination').pagination({
			total: json.total,
			pageNumber: 1,
			pageSize: 8
		});
		
		loadDate(json.rows);
	});

	function loadDate(rows) {
		$(".image_list_min").html("");
		for (var i=0; i<rows.length; i++) {
			var img = rows[i];
			var html = "<div class='image_cell_min'><div class='image_body_min'><a href='javascript:void(0)'>"
				+ "<img src='" + img.path + "' id='" + img.id + "' /></a></div><div class='image_info_min'><p>"
				+ img.info + "</p></div></div>";
			$(".image_list_min").append(html).find('img:last').load(function() {
				var img = $(this);
				var imgHeight = img.height();
				var imgWidth = img.width();		
				var m_left = 0;
				var m_top = 0;
				if (imgHeight > 110 || imgWidth >130) {
					var rank;
					if (imgWidth > imgHeight) {
						rank = imgWidth/130;
						imgHeight = imgHeight/rank;
						m_top = (108-imgHeight)/2;
						imgWidth = 130;
					} else {
						rank = imgHeight/110;
						imgWidth = imgWidth/rank;
						m_left = (128-imgWidth)/2;
						imgHeight = 110;
					}	
				} else {
					m_top = (108-imgHeight)/2;
					m_left = (128-imgWidth)/2; 
				}
				$(img).css({
					height:imgHeight + 'px',
					width:imgWidth + 'px',
					marginTop: m_top + 'px '
				});	
				$('.image_cell_min').click(function() {
					$('.image_cell_min').removeClass('u_select');
					$(this).addClass('u_select');
				});
			});
		}
	}
	
	
});

</script>
<div id="pagination" style="width: 100%"></div>
<div class="clear"></div>
<div class="image_list_min">
</div>
<!-- 
	<div class="image_cell_min">
	  <div class='image_body_min'>
		  <a href="javascript:void(0)">
		  	<img src="admin/images_test_for_gallery/portfolio3/featured_full8.jpg" id="123" />
		  </a>
	  </div>
	  <div class="image_info_min">
	      <p>商品图片介绍</p>
	  </div>
	</div>

 -->



