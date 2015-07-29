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
		<title>比赛详情</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<%@ include file="../WEB-INF/common/commonInclude.jsp"%>

	</head>

	<body>
		<div class="swap">
			<%@ include file="WEB-INF/common/header.jsp"%>

			<%@ include file="WEB-INF/common/banner.jsp"%>
			<div class="main_swap">
				<div class="main">
					<div class="competition-detail-title">
						<h1>
							<s:property value="#request.competition.name"/>
						</h1>
					</div>
					<div class="bs-problem bs-competition-detail competition-detail">
						<table class="table table-striped">
							<tr>
								<td style="text-align: center;">
									比赛日期
								</td>
								<th>
									<s:property value="#request.competition.date"/>
								</th>
							</tr>
							<tr>
								<td style="text-align: center;">
									开始时间
								</td>
								<th>
									<s:property value="#request.competition.start"/>
								</th>
							</tr>
							<tr>
								<td style="text-align: center;">
									结束时间
								</td>
								<th>
									<s:property value="#request.competition.end"/>
								</th>
							</tr>
							<tr>
								<td style="text-align: center;">
									地点
								</td>
								<th>
									<s:property value="#request.competition.address"/>
								</th>
							</tr>
							<tr>
								<td style="text-align: center;">
									比赛形式
								</td>
								<th>
									<s:property value="#request.competition.maxPlayer"/>
								</th>
							</tr>
							<tr>
								<td style="text-align: center;">
									查看参赛队伍
								</td>
								<th>
									<a href="javascript:void(0)" itemId='222222' onclick='join("1234")';role="button" class="btn btn-info" data-toggle="modal">点击查看</a>
								</th>
							</tr>
						</table>
					</div>
					<div class="bs-problem bs-competition-info competition-info">
						<s:property value="#request.competition.info" escape="false"/>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<%@ include file="WEB-INF/common/footer.jsp"%>
			<script src="js/jquery-1.8.2.js"></script>
			<script src="js/bootstrap.min.js"></script>
			<script src="js/unslider.js"></script>

			<!-- Button to trigger modal -->


			<!-- Modal -->
			<div id="myModal" style="width:760px;" class="modal hide fade"
				tabindex="-1" data-remote="user/group_getGroups?competitionId=<s:property value='#request.competition.id'/>" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">
						×
					</button>
					<h3 id="myModalLabel">
						参赛列表
					</h3>
				</div>
				<div class="modal-body">
					<!-- join_team_list 页面 -->
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
				$(this).find("#createGroup").attr('href', 'createGroup' + id);
				$(this).find("#joinGroup").attr('href', 'jionGroup' + id);
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
