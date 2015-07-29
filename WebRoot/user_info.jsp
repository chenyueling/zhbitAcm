<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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
		<SCRIPT type="text/javascript">
		$(function(){
			
		//init
		/*var totalPage = <s:property value="#request.totalPage"/>;
		var totalRecords = <s:property value="#request.totalRecords"/>;
		var pageNo = <s:property value="#request.pageNo"/>;
		var storeId = "<s:property value="#request.store.id"/>";*/

		var totalPage = 10;
		var totalRecords = 100;
		var pageNo = 2;
		var storeId = 0;
		//发送ajax异步请求获取商品列表
		
			var url = "storeshowlist?id=" + storeId + "&page=1";
			//$.get(url, function (data, textStatus){
				//$('#list').html(data);
	    	//});

			if(!pageNo){
			pageNo = 1;
			}

		
		
		//生成分页
		//有些参数是可选的，比如lang，若不传有默认值
		kkpager.generPageHtml({
			pagerid : 'aaaa',
			pno : pageNo,
			//模式
			mode : 'click',
			//总页码
			total : totalPage,
			//总数据条数
			totalRecords : totalRecords,
			//链接前部
			click :function(n){
			var url = "?id=" + storeId + "&page="+n;
			$.get(url, function (data, textStatus){
				$('#list').html(data);
	    	});
			 this.selectPage(n);
			},
			getHref : function(n){
		        return '#';
		    },
			lang : {
				firstPageText : '|<',
				lastPageText : '>|',
				prePageText : '<',
				nextPageText : '>',
				totalPageBeforeText : '共',
				totalPageAfterText : '页',
				totalRecordsAfterText : '条数据',
				gopageBeforeText : '转到',
				gopageButtonOkText : '确定',
				gopageAfterText : '页',
				buttonTipBeforeText : '第',
				buttonTipAfterText : '页'
			}
			//,
			//mode : 'click',//默认值是link，可选link或者click
			//click : function(n){
			//	this.selectPage(n);
			//  return false;
			//}
			});


		kkpager.generPageHtml({
			pagerid : 'aaaa',
			pno : pageNo,
			//模式
			mode : 'click',
			//总页码
			total : totalPage,
			//总数据条数
			totalRecords : totalRecords,
			//链接前部
			click :function(n){
			var url = "?id=" + storeId + "&page="+n;
			$.get(url, function (data, textStatus){
				$('#list').html(data);
	    	});
			 this.selectPage(n);
			},
			getHref : function(n){
		        return '#';
		    },
			lang : {
				firstPageText : '|<',
				lastPageText : '>|',
				prePageText : '<',
				nextPageText : '>',
				totalPageBeforeText : '共',
				totalPageAfterText : '页',
				totalRecordsAfterText : '条数据',
				gopageBeforeText : '转到',
				gopageButtonOkText : '确定',
				gopageAfterText : '页',
				buttonTipBeforeText : '第',
				buttonTipAfterText : '页'
			}
			//,
			//mode : 'click',//默认值是link，可选link或者click
			//click : function(n){
			//	this.selectPage(n);
			//  return false;
			//}
			});
		});
				
		
		</SCRIPT>
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
								<s:property value="#request.user.userIntro" />
							</p>
						</div>
						<hr>
						<ul>
							<li>
								<laber>
								昵称
								</laber>
								<b><s:property value="#request.user.username" /></b>
							</li>
							<li>
								<laber>
								学校
								</laber>
								<b><s:property value="#request.school" /></b>
							</li>
							<li>
								<laber>
								学院
								</laber>
								<b><s:property value="#request.user.college" /></b>
							</li>
							<li>
								<laber>
								专业
								</laber>
								<b><s:property value="#request.user.major" /></b>
							</li>
							<li>
								<laber>
								年级
								</laber>
								<b><s:property value="#request.user.grade" /></b>
							</li>
							<li>
								<laber>
								Email
								</laber>
								<b><s:property value="#request.user.email" /></b>
							</li>
							<li>
								<laber>
								头衔
								</laber>
								<b><s:property value="#request.user.level" /></b>
							</li>
							 <li><laber style="width:85px;margin-left:20px;"><a class="btn  btn-info" href="user/update_user_password.jsp" type="button">修改密码</a></laber><b style="margin-left:25px;" ><a  class="btn btn-primary" type="button" href="user/user_beforeupdate" >完善资料</a></b></li>
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
								<!-- 参赛列表 -->
								 <table class="table table-striped">
                            <tr>
                                <th width="10%">序号</th>
                                <th width="30%">比赛</th>
                                <th width="15%" style="text-align:center">比赛时间</th>
                                <th width="15%" >比赛类型</th>
                                <th width="15%" >比赛状态</th>
                            </tr>
                            <tr>
                                <td>0001</td>
                                <td><a href="#">第四届双北杯</a></td>
                                <td><a href="#">2014/5/29</a></td>
                                <td><a href="#">北师邀请赛</a></td>
                                <td style="">完成参赛</td>
                            </tr>
                          <tr>
                                <td>0001</td>
                                <td><a href="#">第四届双北杯</a></td>
                                <td><a href="#">2014/5/29</a></td>
                                <td><a href="#">北师邀请赛</a></td>
                                <td style="">完成参赛</td>
                            </tr>
                           <tr>
                                <td>0001</td>
                                <td><a href="#">第四届双北杯</a></td>
                                <td><a href="#">2014/5/29</a></td>
                                <td><a href="#">北师邀请赛</a></td>
                                <td style="">完成参赛</td>
                            </tr>
                            <tr>
                                <td>0001</td>
                                <td><a href="#">第四届双北杯</a></td>
                                <td><a href="#">2014/5/29</a></td>
                                <td><a href="#">北师邀请赛</a></td>
                                <td style="">完成参赛</td>
                            </tr>
                            <tr>
                                <td>0001</td>
                                <td><a href="#">第四届双北杯</a></td>
                                <td><a href="#">2014/5/29</a></td>
                                <td><a href="#">北师邀请赛</a></td>
                                <td style="">完成参赛</td>
                            </tr>
                           <tr>
                                <td>0001</td>
                                <td><a href="#">第四届双北杯</a></td>
                                <td><a href="#">2014/5/29</a></td>
                                <td><a href="#">北师邀请赛</a></td>
                                <td style="">完成参赛</td>
                            </tr>
                            <tr>
                                <td>0001</td>
                                <td><a href="#">第四届双北杯</a></td>
                                <td><a href="#">2014/5/29</a></td>
                                <td><a href="#">北师邀请赛</a></td>
                                <td style="">完成参赛</td>
                            </tr>
                           <tr>
                                <td>0001</td>
                                <td><a href="#">第四届双北杯</a></td>
                                <td><a href="#">2014/5/29</a></td>
                                <td><a href="#">北师邀请赛</a></td>
                                <td style="">完成参赛</td>
                            </tr>
                           <tr>
                                <td>0001</td>
                                <td><a href="#">第四届双北杯</a></td>
                                <td><a href="#">2014/5/29</a></td>
                                <td><a href="#">北师邀请赛</a></td>
                                <td style="">完成参赛</td>
                            </tr>
                        </table>
								<div class="pagination">
									<ul style="float:right;">
                                <li><a href="#">Prev</a></li>
                                <li class="active"><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                                <li><a href="#">Next</a></li>
                            </ul>
								</div>
							</div>
							<div>
								<!-- 获奖列表 -->
								 <table class="table table-striped">
                            <tr>
                                <th width="10%">Pro.ID</th>
                                <th width="60%">Problem Title</th>
                                <th width="15%" style="text-align:center">Ac/Submit</th>
                            </tr>
                            <tr>
                                <td>0001</td>
                                <td><a href="#">Constructing Roads In JGShining's Kingdom</a></td>
                                <td style="text-align:center;color:red; font-weight:bold">1 / 7</td>
                            </tr>
                            <tr>
                                <td>0001</td>
                                <td><a href="#">Constructing Roads In JGShining's Kingdom</a></td>
                                <td style="text-align:center;color:red; font-weight:bold">1 / 7</td>
                            </tr>
                            <tr>
                                <td>0001</td>
                                <td><a href="#">Constructing Roads In JGShining's Kingdom</a></td>
                                <td style="text-align:center;color:red; font-weight:bold">1 / 7</td>
                            </tr>
                            <tr>
                                <td>0001</td>
                                <td><a href="#">Constructing Roads In JGShining's Kingdom</a></td>
                                <td style="text-align:center;color:red; font-weight:bold">1 / 7</td>
                            </tr>
                            <tr>
                                <td>0001</td>
                                <td><a href="#">Constructing Roads In JGShining's Kingdom</a></td>
                                <td style="text-align:center;color:red; font-weight:bold">1 / 7</td>
                            </tr>
                            <tr>
                                <td>0001</td>
                                <td><a href="#">Constructing Roads In JGShining's Kingdom</a></td>
                                <td style="text-align:center;color:red; font-weight:bold">1 / 7</td>
                            </tr>
                            <tr>
                                <td>0001</td>
                                <td><a href="#">Constructing Roads In JGShining's Kingdom</a></td>
                                <td style="text-align:center;color:red; font-weight:bold">1 / 7</td>
                            </tr>
                            <tr>
                                <td>0001</td>
                                <td><a href="#">Constructing Roads In JGShining's Kingdom</a></td>
                                <td style="text-align:center;color:red; font-weight:bold">1 / 7</td>
                            </tr>
                            <tr>
                                <td>0001</td>
                                <td><a href="#">Constructing Roads In JGShining's Kingdom</a></td>
                                <td style="text-align:center;color:red; font-weight:bold">1 / 7</td>
                            </tr>
                        </table>
								<div class="pagination">
									<ul style="float:right;">
                                <li><a href="#">Prev</a></li>
                                <li class="active"><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                                <li><a href="#">Next</a></li>
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
