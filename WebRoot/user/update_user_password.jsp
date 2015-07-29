<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
  <title>Zhbit ACM</title>
  <base href="<%=basePath%>"> 
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <%@ include file="../WEB-INF/common/commonInclude.jsp"%>
  
  <script type="text/javascript">
  	$(function() {
	$.acm.initForm({
		form:'#updateForm',
		url:'user/user_updatepassword',
		success:function(json) {
			alert(json.data);
			window.location.href = "<%=basePath%>index.jsp";
		}
	});
				//验证表单
	 $("input,select,textarea").not("[type=submit]").jqBootstrapValidation(); 
    $("input,select,textarea").jqBootstrapValidation({sniffHtml: false});//;
	})
  </script>
  </head>

<body>
  <div class="swap">
    <%@ include file="../WEB-INF/common/header.jsp"%>
    <%@ include file="../WEB-INF/common/banner.jsp"%>
  <div class="main_swap">
    <div class="main">

      <form class="form-horizontal" id="updateForm" method="post">
        <fieldset>
          <div id="legend" class="">
            <legend class="">修改密码</legend>
          </div>
          <div class="control-group">

            <!-- Text input-->
            <label class="control-label" for="input01">旧密码</label>
            <div class="controls">
              <input type="password" placeholder="oldpassword" name="oldPassword" maxlength="16" minlength="6" class="input-xlarge" required>
              <p class="help-block"></p>
            </div>
          </div>


          <div class="control-group">
            <label class="control-label" for="input01">新密码</label>
            <div class="controls">
              <input type="password" placeholder="password" name="password" maxlength="16" minlength="6" class="input-xlarge" required>
              <p class="help-block"></p>
            </div>
          </div>

          <div class="control-group">

            <!-- Text input-->
            <label class="control-label" for="input01">确认密码</label>
            <div class="controls">
              <input type="password" placeholder="confirm password" name="password2" maxlength="16" minlength="6" class="input-xlarge" required>
              <p class="help-block"></p>
            </div>
          </div>
          
        <div class="problem_submit_operate">
          <ul>
            <li>
              <button class="btn btn-success">更新</button>
            </li>
            <li>
              <button class="btn btn-warning">重置</button>
            </li>
          </ul>
        </div>
      </form>
      <div class="clearfix"></div>
    </div>
  </div>
  <div class="footer text-center">
    <small>
      Beijing Institute of Technology University Online Judge Bete 1.0
    </<small>
    <br/>
    <small>Copyright © 2013 Zhbit ACM Team. All Rights Reserved.</small>
    <br/>
    <small>Designer & Developer : null</small>
  </div>
</div>
<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript">$('.datepicker').datepicker({
    format: 'yyyy-mm-dd',
    startDate: '-3d'
})</script>
</body>

</html>