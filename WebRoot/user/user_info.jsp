<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>"> 
		<title>用户资料</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<%@ include file="../WEB-INF/common/commonInclude.jsp"%>
	</head>
	<body>
		<div class="swap">
			<%@ include file="../WEB-INF/common/header.jsp"%>

			<%@ include file="../WEB-INF/common/banner.jsp"%>
			<div class="main_swap">
				<div class="main">
					<div class="bs-problem bs-problem-userinfo user_info">
						<div>
							<image class="img-circle"
								style="display:block; height:150px; width:150px; margin:0 auto;"
								src="images/head.png">
							<!--
                    <image class="img-circle" style="display:block; height:150px; width:150px; margin:0 auto;" src="images/default.png">
                    -->
						</div>
						<div>
							<h5>
								个人简介
							</h5>
							<p class="user_motto">
								程序猿是一种高傲的动物，自嘲码农，同行称码畜。处于被剥削层。
							</p>
						</div>
						<hr>
						<ul>
							<li>
								<laber>
								昵称
								</laber>
								<b>单曲循环</b>
							</li>
							<li>
								<laber>
								学院
								</laber>
								<b>计算机学院</b>
							</li>
							<li>
								<laber>
								专业
								</laber>
								<b>软件工程</b>
							</li>
							<li>
								<laber>
								年级
								</laber>
								<b>2011级</b>
							</li>
							<li>
								<laber>
								Email
								</laber>
								<b>chen_yueling@163.com</b>
							</li>
							<li>
								<laber>
								头衔
								</laber>
								<b>14-程基会员</b>
							</li>
						</ul>
					</div>

					<div class="problem_solve">
						<ul class="nav nav-tabs" data-trigger="tab" data-easing="slide">
							<li class="">
								<a href="#tab1">参赛列表</a>
							</li>
							<li class="">
								<a href="#tab2">获奖列表</a>
							</li>
						</ul>
						<div class="pane-wrapper slide">
							<div class="active">
								<table class="table table-striped">
									<tr>
										<th width="10%">
											序号
										</th>
										<th width="30%">
											比赛
										</th>
										<th width="15%" style="text-align: center">
											比赛时间
										</th>
										<th width="15%">
											比赛类型
										</th>
										<th width="15%">
											比赛状态
										</th>
									</tr>
									<tr>
										<td>
											0001
										</td>
										<td>
											<a href="#">第四届双北杯</a>
										</td>
										<td>
											<a href="#">2014/5/29</a>
										</td>
										<td>
											<a href="#">北师邀请赛</a>
										</td>
										<td style="">
											完成参赛
										</td>
									</tr>
									<tr>
										<td>
											0001
										</td>
										<td>
											<a href="#">第四届双北杯</a>
										</td>
										<td>
											<a href="#">2014/5/29</a>
										</td>
										<td>
											<a href="#">北师邀请赛</a>
										</td>
										<td style="">
											完成参赛
										</td>
									</tr>
									<tr>
										<td>
											0001
										</td>
										<td>
											<a href="#">第四届双北杯</a>
										</td>
										<td>
											<a href="#">2014/5/29</a>
										</td>
										<td>
											<a href="#">北师邀请赛</a>
										</td>
										<td style="">
											完成参赛
										</td>
									</tr>
									<tr>
										<td>
											0001
										</td>
										<td>
											<a href="#">第四届双北杯</a>
										</td>
										<td>
											<a href="#">2014/5/29</a>
										</td>
										<td>
											<a href="#">北师邀请赛</a>
										</td>
										<td style="">
											完成参赛
										</td>
									</tr>
									<tr>
										<td>
											0001
										</td>
										<td>
											<a href="#">第四届双北杯</a>
										</td>
										<td>
											<a href="#">2014/5/29</a>
										</td>
										<td>
											<a href="#">北师邀请赛</a>
										</td>
										<td style="">
											完成参赛
										</td>
									</tr>
									<tr>
										<td>
											0001
										</td>
										<td>
											<a href="#">第四届双北杯</a>
										</td>
										<td>
											<a href="#">2014/5/29</a>
										</td>
										<td>
											<a href="#">北师邀请赛</a>
										</td>
										<td style="">
											完成参赛
										</td>
									</tr>
									<tr>
										<td>
											0001
										</td>
										<td>
											<a href="#">第四届双北杯</a>
										</td>
										<td>
											<a href="#">2014/5/29</a>
										</td>
										<td>
											<a href="#">北师邀请赛</a>
										</td>
										<td style="">
											完成参赛
										</td>
									</tr>
									<tr>
										<td>
											0001
										</td>
										<td>
											<a href="#">第四届双北杯</a>
										</td>
										<td>
											<a href="#">2014/5/29</a>
										</td>
										<td>
											<a href="#">北师邀请赛</a>
										</td>
										<td style="">
											完成参赛
										</td>
									</tr>
									<tr>
										<td>
											0001
										</td>
										<td>
											<a href="#">第四届双北杯</a>
										</td>
										<td>
											<a href="#">2014/5/29</a>
										</td>
										<td>
											<a href="#">北师邀请赛</a>
										</td>
										<td style="">
											完成参赛
										</td>
									</tr>
								</table>

								<div class="pagination">
									<ul style="float: right;">
										<li>
											<a href="#">Prev</a>
										</li>
										<li class="active">
											<a href="#">1</a>
										</li>
										<li>
											<a href="#">2</a>
										</li>
										<li>
											<a href="#">3</a>
										</li>
										<li>
											<a href="#">4</a>
										</li>
										<li>
											<a href="#">5</a>
										</li>
										<li>
											<a href="#">Next</a>
										</li>
									</ul>
								</div>
							</div>
							<div>
								<table class="table table-striped">
									<tr>
										<th width="10%">
											Pro.ID
										</th>
										<th width="60%">
											Problem Title
										</th>
										<th width="15%" style="text-align: center">
											Ac/Submit
										</th>
									</tr>
									<tr>
										<td>
											0001
										</td>
										<td>
											<a href="#">Constructing Roads In JGShining's Kingdom</a>
										</td>
										<td style="text-align: center; color: red; font-weight: bold">
											1 / 7
										</td>
									</tr>
									<tr>
										<td>
											0001
										</td>
										<td>
											<a href="#">Constructing Roads In JGShining's Kingdom</a>
										</td>
										<td style="text-align: center; color: red; font-weight: bold">
											1 / 7
										</td>
									</tr>
									<tr>
										<td>
											0001
										</td>
										<td>
											<a href="#">Constructing Roads In JGShining's Kingdom</a>
										</td>
										<td style="text-align: center; color: red; font-weight: bold">
											1 / 7
										</td>
									</tr>
									<tr>
										<td>
											0001
										</td>
										<td>
											<a href="#">Constructing Roads In JGShining's Kingdom</a>
										</td>
										<td style="text-align: center; color: red; font-weight: bold">
											1 / 7
										</td>
									</tr>
									<tr>
										<td>
											0001
										</td>
										<td>
											<a href="#">Constructing Roads In JGShining's Kingdom</a>
										</td>
										<td style="text-align: center; color: red; font-weight: bold">
											1 / 7
										</td>
									</tr>
									<tr>
										<td>
											0001
										</td>
										<td>
											<a href="#">Constructing Roads In JGShining's Kingdom</a>
										</td>
										<td style="text-align: center; color: red; font-weight: bold">
											1 / 7
										</td>
									</tr>
									<tr>
										<td>
											0001
										</td>
										<td>
											<a href="#">Constructing Roads In JGShining's Kingdom</a>
										</td>
										<td style="text-align: center; color: red; font-weight: bold">
											1 / 7
										</td>
									</tr>
									<tr>
										<td>
											0001
										</td>
										<td>
											<a href="#">Constructing Roads In JGShining's Kingdom</a>
										</td>
										<td style="text-align: center; color: red; font-weight: bold">
											1 / 7
										</td>
									</tr>
									<tr>
										<td>
											0001
										</td>
										<td>
											<a href="#">Constructing Roads In JGShining's Kingdom</a>
										</td>
										<td style="text-align: center; color: red; font-weight: bold">
											1 / 7
										</td>
									</tr>
								</table>

								<div class="pagination">
									<ul style="float: right;">
										<li>
											<a href="#">Prev</a>
										</li>
										<li class="active">
											<a href="#">1</a>
										</li>
										<li>
											<a href="#">2</a>
										</li>
										<li>
											<a href="#">3</a>
										</li>
										<li>
											<a href="#">4</a>
										</li>
										<li>
											<a href="#">5</a>
										</li>
										<li>
											<a href="#">Next</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="clearfix"></div>

				</div>
				<div class="clearfix"></div>
			</div>

		</div>
		<%@ include file="../WEB-INF/common/footer.jsp"%>

	</body>

</html>
