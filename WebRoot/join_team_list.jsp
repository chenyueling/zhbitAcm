<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
	<base href="<%=basePath%>"> 
		<title>参赛队伍</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<%@ include file="WEB-INF/common/commonInclude.jsp"%>
	</head>

	<body >
		<div class="swap" >
			

			<div class="main_swap" style="width:690px;">
				<div class="main" style="width:690px;" >
					<div class="bs-problem bs-join-team-list" style="width:650px;">
                <table class="table table-striped">
                    <tr>
                        <th width="10%">序号</th>
                        <!-- <th width="10%">封面图片</th> -->
                        <th width="20%">队伍名称</th>
                        <th width="20%">英文名</th>
                        <th width="20%" style="text-align:center;">报名时间</th>
                        <th width="10%" style="text-align:center;">队长</th>
                        <th width="15%" style="text-align:center;">审核状态</th>
                    </tr>
                    
					<s:iterator value="#request.groups" status="stat">
	                    <tr>
	                        <td>	<s:property value="#stat.index+1" /> </td>
	                        <!-- <td><img src="images/xw-small.png"></td> -->
	                        <td><a href='user/group_getVoGroup?o=o&id=<s:property value="#request.id"/>'><s:property value="#request.name"/></a></td>
	                        <td><a href='user/group_getVoGroup?o=o&id=<s:property value="#request.id"/>'><s:property value="#request.engName"/></a></td>
	                        <td style="text-align:center; font-weight:bold;"><s:property value="#request.createTime"/></td>
	                        <td style="text-align:center; font-weight:bold;"><s:property value="#request.captain"/></td>
	                        <td style="text-align:center; font-weight:bold;"><s:property value="#request.status"/></td>
	                    </tr>
					</s:iterator>
                </table>
            </div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</body>

</html>
