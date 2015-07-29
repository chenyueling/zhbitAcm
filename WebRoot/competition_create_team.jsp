<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>创建新队伍</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<%@ include file="../WEB-INF/common/commonInclude.jsp"%>


		<script type="text/javascript">
		$(function() {
			$.acm.initForm({
			form:'#createForm',
			url:'user/group_saveTeam',
			success:function(json) {
				alert("创建成功！");
				window.location.href = "user/group_getVoGroup?keyCode="+json.data;
			}
		}); 
		
		console.log("1111");
		 $("input,select,textarea").not("[type=submit]").jqBootstrapValidation(); 
	      $("input,select,textarea").jqBootstrapValidation({sniffHtml: false});//;
	});
	 
	</script>
	</head>

	<body>
		<div class="swap">
			<%@ include file="WEB-INF/common/header.jsp"%>

			<%@ include file="WEB-INF/common/banner.jsp"%>
			<div class="main_swap">
				<div class="main">
					<div class="competition-detail-title">
						<h1>
							创建新队伍
						</h1>
					</div>
					<div class="bs-problem bs-team-info competition-detail">
						<form id="createForm"  method="post">
							<input type="hidden" name="competitionId" value='<s:property value="#request.competitionId"/>' />
							<input type="hidden" name="userId" value='<s:property value="#request.userId"/>' />
							<table class="table table-striped">
								<tr class="success">
									<td style="text-align: center;">
										队伍名称（中文）
									</td>
									<td>
										<input type="text" name="name" data-validation-email-message="length:1~100" minlength="1" maxlength="100" required/>
									</td>
								</tr>
								<tr class="info">
									<td style="text-align: center;">
										队伍名称（英文）
									</td>
									<td>
										<input type="text" name="engName" data-validation-email-message="length:1~100" minlength="1" maxlength="100" required/>
									</td>
								</tr>
							</table>
					</div>
					<div class="bs-problem bs-captain-info competition-detail">
						<table class="table table-striped">
							<tr>
								<td style="text-align: center; width: 159px;">
									姓名
								</td>
								<td>
									<input type="text" name="username" value='<s:property value="#request.username"/>' 
									data-validation-email-message="length:1~100" minlength="1" maxlength="100" required/>
								</td>
							</tr>
							<tr>
								<td style="text-align: center;">
									学号
								</td>
								<td>
									<input type="text" name="stuId" value='<s:property value="#request.stuId"/>' 
									data-validation-email-message="length:12" minlength="12"  maxlength="12"  required/>
								</td>
							</tr>
							<tr>
								<td style="text-align: center;">
									电话
								</td>
								<td>
									<input type="text" name="phone" value='<s:property value="#request.phone"/>' 
									 minlength="4" maxlength="12"
									 data-validation-email-message="请填写正确号码，便于通知您比赛事宜" required/>
								</td>
							</tr>
							<tr>
								<td style="text-align: center;">
									邮箱
								</td>
								<td>
									<input type="email" name="email" data-validation-email-message="Not a valid email address" value='<s:property value="#request.email"/>' required/>
								</td>
							</tr>
						</table>
						<div class="jion_competition_submit_operate">
							<ul>
								<li>
									<button class="btn btn-success">
										提交
									</button>
								</li>
								<li>
									<button class="btn btn-warning">
										重置
									</button>
								</li>
							</ul>
						</div>
						</form>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<%@ include file="WEB-INF/common/footer.jsp"%>
		</div>
	</body>


</html>
