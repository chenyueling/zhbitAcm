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
		<title>队伍信息</title>
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
							<s:property
								value="#request.voCompetitionGroup.contestname" /></h1></div>
        <div class="bs-problem bs-team-info competition-detail">
              <table class="table table-striped">
                     <tr>
                        <td style="text-align:center;width:190px;">比赛日期</td>
                        <th><s:property value="#request.voCompetitionGroup.date"/></th>
                    </tr>
                    <tr>
                        <td style="text-align:center;">比赛时间</td>
                        <th><s:property value="#request.voCompetitionGroup.start"/>----<s:property value="#request.voCompetitionGroup.end"/></th>
                    </tr>
                    <tr>
                        <td style="text-align:center;">地点</td>
                        <th><s:property value="#request.voCompetitionGroup.address"/></th>
                    </tr>
                    <tr>
                        <td style="text-align:center;">比赛形式</td>
                        <th><s:property value="#request.voCompetitionGroup.maxPlayer"/></th>
                        </tr>
                     <tr>
                       <tr>
                        <td style="text-align:center;">队伍名称（中）</td>
                        <th><s:property value="#request.voCompetitionGroup.name"/></th>
                        </tr>
                     <tr>
                       <tr>
                        <td style="text-align:center;">队伍名称（英）</td>
                        <th><s:property value="#request.voCompetitionGroup.engName"/></th>
                        </tr>
                     <tr>
                    <td style="text-align:center;">队伍码</td>
                    <th style="color:red;"><s:property value="#request.voCompetitionGroup.keyCode"/></th>
                    </tr>
                     <tr>
                    <td style="text-align:center;">获奖信息</td>
                    <th style="color:red;"><s:property value="#request.voCompetitionGroup.rewards"/></th>
                    </tr>
                    <tr>
                    	<td style="text-align:center;">审核状态</td>
                   	 	<th style="color:red;"><s:property value="#request.voCompetitionGroup.status"/></th>
                    </tr>
                </table> 
            </div>
      

          <div class="bs-problem bs-competition-teamate competition-detail">
              <table class="table table-striped">
                     <tr>
                        <td style="text-align:center;">队员1</td>
                        <th><s:property value="#request.voCompetitionGroup.captain"/></th>
                    </tr>
                    <tr>
                        <td style="text-align:center;">队员2</td>
                        <th><s:property value="#request.voCompetitionGroup.player_1"/></th>
                    </tr>
                    <tr>
                        <td style="text-align:center;">队员3</td>
                        <th><s:property value="#request.voCompetitionGroup.player_2"/></th>
                    </tr>
                    
                </table> 
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


		</div>
	</body>

</html>
