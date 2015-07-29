<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>Zhbit ACM</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<%@ include file="../WEB-INF/common/commonInclude.jsp"%>

		<style type="text/css">

.banner {
	position: relative;
	width: 100%;
	overflow: auto;
	font-size: 18px;
	line-height: 24px;
	text-align: center;
	color: rgba(255, 255, 255, .6);
	text-shadow: 0 0 1px rgba(0, 0, 0, .05), 0 1px 2px rgba(0, 0, 0, .3);
	background: #5b4d3d;
	box-shadow: 0 1px 2px rgba(0, 0, 0, .25);
}

.banner ul li {
	display: block;
	float: left;
	width: 33%;
	min-height: 280px;
	-o-background-size: 100% 100%;
	-ms-background-size: 100% 100%;
	-moz-background-size: 100% 100%;
	-webkit-background-size: 100% 100%;
	background-size: 100% 100%;
	box-shadow: inset 0 -3px 6px rgba(0, 0, 0, .1);
}

.banner .dots {
	position: absolute;
	left: 0;
	right: 0;
	bottom: 20px;
}

.banner .dots li {
	display: inline-block;
	width: 10px;
	height: 10px;
	margin: 0 4px;
	text-indent: -999em;
	border: 2px solid #fff;
	border-radius: 6px;
	cursor: pointer;
	opacity: .4;
	-webkit-transition: background .5s, opacity .5s;
	-moz-transition: background .5s, opacity .5s;
	transition: background .5s, opacity .5s;
}

.banner .dots li.active {
	background: #fff;
	opacity: 1;
}
</style>
	</head>

	<body>
		<div class="swap">
			<%@ include file="WEB-INF/common/header.jsp"%>
			<div class="banner" >
				<ul>
					<li style="background-image: url('img/wood.jpg');"></li>
					<li style="background-image: url('img/shop.jpg');"></li>
					<li style="background-image: url('img/subway.jpg');"></li>
					<li style="background-image: url('img/sunset.jpg');"></li>
					<li style="background-image: url('images/jl.png');"></li>
				</ul>
			</div>
			<div class="main_swap">
				<div class="main">
					<div class="top">
						<div id="top_1">
							<div id="top_1_1" style="background-image: url('images/1.png');"></div>
							<div id="top_1_2">
								<a href="user/competition_getContests"> <img src="images/bm.png"> </a>
							</div>
						</div>
						<div id="top_2">
							<div id="top_2_1" style="background-image: url('images/2.png');"></div>
							<div id="top_2_2">
								<a href="#"> <img src="images/jl.png"> </a>
							</div>
						</div>
						<div id="top_3" style="background-image: url('images/oj.png');"></div>
					</div>
					<div class="bottom">
						<div id="bottom_1">
							<div id="bottom_1_1"
								style="background-image: url('images/fx.png');"></div>
							<div id="share_art">
								<ul>
									<li>
										<span class="share_art_vertical">|</span>
										<a href="#"> <span class="share_art_title">bootStrap
												入门详解hahhahaha</span> </a>
										<span class="share_art_publishTime">2013-01-20</span>
									</li>
									<li>
										<span class="share_art_vertical">|</span>
										<a href="#"> <span class="share_art_title">bootStrap
												入门详解hahhahaha</span> </a>
										<span class="share_art_publishTime">2013-01-20</span>
									</li>
									<li>
										<span class="share_art_vertical">|</span>
										<a href="#"> <span class="share_art_title">bootStrap
												入门详解hahhahaha</span> </a>
										<span class="share_art_publishTime">2013-01-20</span>
									</li>
								</ul>
							</div>
							<div id="bottom_1_2"
								style="background-image: url('images/fxt.png');"></div>
						</div>
						<div id="bottom_2">

							<div id="bottom_2_1"
								style="background-image: url('images/xw.png');"></div>
							<div id="new_art">
								<ul>

									<li>
										<a class="new_art_title_a" href="#"><p>
												計算機學子在十一屆國際級ACM-ICPC省賽中獲佳績
											</p> </a>
										<div id="new_art_detail">
											<div id="new_art_detail_image">
												<a href=""><img src="images/xw-small.png" alt="">
												</a>
											</div>
											<div id="new_art_detail_content">
												sss計算機學子在十一屆國際級ACM-ICPC省賽中獲佳績計算機學子在十一屆國際
											</div>
										</div>
									</li>
									<li>
										<span class="new_art_vertical">|</span>
										<a class="new_art_title_a" href="#"> <span
											class="new_art_title">bootStrap 入门详解hahhfsdfsdfsdahaha</span>
										</a>

									</li>
									<li>
										<span class="new_art_vertical">|</span>
										<a class="new_art_title_a" href="#"> <span
											class="new_art_title">bootStrap 入门详解hahhafdsfsdfhaha</span> </a>

									</li>
									<li>
										<span class="new_art_vertical">|</span>
										<a class="new_art_title" href="#"> <span
											class="new_art_title">bootStrap 入门详解hahhahahdfdsfsdfa</span>
										</a>

									</li>
								</ul>
							</div>
						</div>
						<div id="bottom_3">
							<div id="bottom_3_1"
								style="background-image: url('images/3.png');"></div>
							<div style="">
								<a href="register.jsp"> <img src="images/join.png"> </a>
							</div>
						</div>

					</div>

				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<%@ include file="WEB-INF/common/footer.jsp"%>
		<script type="text/javascript">
    $(function() {
    var slidey = $('.banner').unslider({
    speed: 500,               // 动画的速度,没有过度效果时为 false  (整型或布尔型)
    delay: 2000,              // 幻灯片之间的延迟，没有自动播放时为false（整数或布尔）
    complete: function() {},  // 播放每张幻灯片后调用的函数
    keys: true,               // 允许键盘左右键控制
    dots: true,               // 显示点导航
    fluid: true              // 支持响应式设计
});

});
  </script>
	</body>

</html>
