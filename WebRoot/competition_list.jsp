<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>比赛列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<%@ include file="../WEB-INF/common/commonInclude.jsp"%>
		<!-- 分页插件 -->
		
  	<script>
  	$(function(){
  		var totalPage = <s:property value="#request.totalPage"/>;
		var totalRecords = <s:property value="#request.totalRecord"/>;
		var pageNo = <s:property value="#request.pageNo"/>;
		/*var totalPage = 5;
		var totalRecords = 100;
		var pageNo = 1;*/
		if(!pageNo){
			pageNo = 1;
		}
		//生成分页
		//有些参数是可选的，比如lang，若不传有默认值
		kkpager.generPageHtml({
			pno : pageNo,
			//总页码
			total : totalPage,
			//总数据条数
			totalRecords : totalRecords,
			//链接前部
			hrefFormer : 'user/competition_getContests',
			//链接尾部
			hrefLatter : '',
			getLink : function(n){
				return this.hrefFormer + this.hrefLatter + "?page="+n;
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
  	</script>
	</head>

	<body>
		<div class="swap">
			<%@ include file="WEB-INF/common/header.jsp"%>
			<%@ include file="WEB-INF/common/banner.jsp"%>
			<div class="main_swap">
				<div class="main">
					<div class="bs-problem bs-problem-contentlist">
						<table class="table table-striped">
							<tr>
								<th width="5%" style="text-align: center;">
									ID
								</th>
								<th width="30%">
									竞赛
								</th>
								<th width="17%" style="text-align: center;">
									开始时间
								</th>
								<th width="17%" style="text-align: center;">
									结束时间
								</th>
								<th width="8%" style="text-align: center;">
									类型
								</th>
								<th width="8%" style="text-align: center;">
									状态
								</th>
								<th width="15%" style="text-align: center;">
									报名入口
								</th>
							</tr>

							<s:iterator value="#request.contests" status="stat">
								<tr>
								<td style="text-align: center;">
									<s:property value="#stat.index+1" />
								</td>
								<td>
									<a href='user/competition_info?id=<s:property value="id"/>'><s:property value="name"/></a>
								</td>
								<td style="text-align: center;">
									<s:property value="startTime"/>
								</td>
								<td style="text-align: center;">
									<s:property value="endTime"/>
								</td>
								<td style="text-align: center;">
									<span style="color: green;"><s:property value="type"/></span>
								</td>
								<td style="text-align: center;">
									<span style="color: red;"><s:property value="status"/></span>
								</td>
								<td style="text-align: center;">
								<s:if test='#request.registerContest=="Start"'>
									<a href="javascript:void(0)" itemId='222222' onclick='join("<s:property value='#request.id'/>")';role="button" class="btn btn-info"
										data-toggle="modal">点击参加</a>
								</s:if>
								<s:if test='#request.registerContest=="Ended"'>
									<a class="btn btn-danger disabled">报名截止</a>
								</s:if>
								<s:if test='#request.registerContest=="Pending"'>
									<a class="btn btn-danger disabled">报名未开始</a>
								</s:if>
								</td>
								<!-- 
                        <td style=" text-align:center;"><a  class="btn btn-danger disabled" >报名截止</a></td> -->
							</tr>
							</s:iterator> 


						</table>
						<div class="pagination">
							 <div id="kkpager" ></div>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<%@ include file="WEB-INF/common/footer.jsp"%>
			<!-- Button to trigger modal -->


			<!-- Modal -->
			<div id="myModal" style="width: 650px;" class="modal hide fade"
				tabindex="-1"  role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						×
					</button>
					<h3 id="myModalLabel">
						选择参赛方式
					</h3>
				</div>
				<div class="modal-body">
					<p>
						如果还没有队伍，请点击创建组队。
					</p>
					<p>
						<a class="btn btn-danger" id="createGroup">创建组队</a>
						<a class="btn btn-info" id="joinGroup">加入队伍</a>
					</p>
				</div>
				<div class="modal-footer">


					<button class="btn" data-dismiss="modal" aria-hidden="true">
						关闭
					</button>
					<!-- <button class="btn btn-primary">Save changes</button> -->
				</div>
			</div>
			<script type="text/javascript">
	function join(id) {
		console.log(id);
		$('#myModal').on('show', function() {
			// console.log($(this).;
				$(this).find("#createGroup").attr('href', 'user/group_createTeam?competitionId=' + id);
				$(this).find("#joinGroup").attr('href', 'user/group_joinTeam?competitionId=' + id);
			});
		$('#myModal').modal( {
			backdrop : "static",
			keyboard : true
		});

	}
</script>
		</div>
	</body>
</html>
