<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<%@ include file="../../common/include_bootstrap.jsp" %>
  </head>
  
  <body style="padding-left: 100px;padding-top: 150px;">
    <h2>Zhbit科技协会-协会管理员后台</h2> <br>
  </body>
</html>
