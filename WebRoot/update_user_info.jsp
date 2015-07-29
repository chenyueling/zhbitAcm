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
		url:'user/user_update',
		success:function(json) {
			alert(json.data);
			window.location.href = "user/user_getuserinfo";
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
    <%@ include file="WEB-INF/common/header.jsp"%>
    <%@ include file="WEB-INF/common/banner.jsp"%>
  <div class="main_swap">
    <div class="main">

      <form class="form-horizontal" id="updateForm" method="post">
        <fieldset>
          <div id="legend" class="">
            <legend class="">修改个人信息</legend>
          </div>
          <div class="control-group">

            <!-- Text input-->
            <label class="control-label" for="input01">姓名</label>
            <div class="controls">
              <input type="text" placeholder="name" name="name" maxlength="16" minlength="6" class="input-xlarge" required value=<s:property value="#request.user.name" /> >
              <p class="help-block"></p>
            </div>
          </div>

          <div class="control-group">
            <label class="control-label" for="input01">学校</label>
            <div class="controls">
              <select required name="school" >
              <option value=<s:property value="#request.schoolmark" />> <s:property value="#request.school"/> </option>
              	<s:iterator value="#request.schoollist">
                <option value=<s:property value="mark" />><s:property value="name" /></option>
                </s:iterator>      
              </select>
              <p class="help-block"></p>
            </div>
          </div>
			
		 <div class="control-group">
            <label class="control-label" for="input01">学院</label>
            <div class="controls">
              <select required name="college" >
              	<option>   <s:property value="#request.user.college" /> </option>
                <option>计算机学院</option>
                <option>信息学院</option>
                <option>数理学院</option>
                <option>外国语学院</option>
                <option>机车学院</option>
                <option>商学院</option>
              </select>
              <p class="help-block"></p>
            </div>
          </div>
          
          <div class="control-group">
            <label class="control-label" for="input01">专业</label>
            <div class="controls">
              <select required name="major" >
              	<option>   <s:property value="#request.user.major" /> </option>
                <option>计算机科学与技术</option>
                <option>软件工程</option>
                <option>数字媒体</option>
                <option>信息工程</option>
                <option>车辆工程</option>
                <option>土木工程</option>
                <option>日语</option>
                <option>英语</option>
                <option>化工与材料</option>
                <option>机械与自动化</option>
              </select>
              <p class="help-block"></p>
            </div>
          </div>

 		<div class="control-group">
            <label class="control-label" for="input01">年级</label>
            <div class="controls">
              <select required name="grade" >
           		<option>   <s:property value="#request.user.grade" /> </option>
                <option>2004</option>
                <option>2005</option>
                <option>2006</option>
                <option>2007</option>
                <option>2008</option>
                <option>2009</option>
                <option>2010</option>
                <option>2011</option>
                <option>2012</option>
                <option>2013</option>
                <option>2014</option>
                <option>2015</option>
                <option>2016</option>
                <option>2017</option>
                <option>2018</option>
                <option>2019</option>
                <option>2020</option>
              </select>
              <p class="help-block"></p>
            </div>
          </div>
          <div class="control-group">
            <label class="control-label" for="input01">学号</label>
            <div class="controls">
              <input type="text" placeholder="stuId" name="stuId" class="input-xlarge" value=<s:property value="#request.user.stuId"/>>
              <p class="help-block"></p>
            </div>
          </div>

          <div class="control-group">

            <!-- Text input-->
            <label class="control-label" for="input01">班级</label>
            <div class="controls">
              <input type="text" placeholder="class" name="clazz" class="input-xlarge" value=<s:property value="#request.user.clazz"/>>
              <p class="help-block"></p>
            </div>
          </div>

          <div class="control-group">

            <label class="control-label" for="input01">生日</label>
            <div class="controls">
              <div class="input-append date datepicker" id="dp3" data-date="2014-06-10" >
                <input class="span2" size="16" type="text" name="birthday" value=<s:property value="#request.user.birthDay.toLocaleString()" default="2014-06-10"/>>
                <span class="add-on"> <i class="icon-th"></i>
                </span>
              </div>
              <p class="help-block"></p>
            </div>
          </div>
		
          <div class="control-group">
            <label class="control-label" for="input01">联系电话</label>
            <div class="controls">
              <input type="text" placeholder="phone" name="phone" class="input-xlarge" minlength="11" maxlength="11" value=<s:property value="#request.user.phone" />>
              <p class="help-block"></p>
            </div>
          </div>
          

          <div class="control-group">

            <!-- Text input-->
            <label class="control-label" for="input01">QQ</label>
            <div class="controls">
              <input type="text" placeholder="QQ" name="qq" class="input-xlarge" minlength="5" maxlength="11"value=<s:property value="#request.user.qq" />>
              <p class="help-block"></p>
            </div>
          </div>

          <div class="control-group">

            <!-- Text input-->
            <label class="control-label" for="input01">Email</label>
            <div class="controls">
              <input type="email" placeholder="Email" class="input-xlarge" name="email" required value=<s:property value="#request.user.email" />>
              <p class="help-block"></p>
            </div>
          </div>

          <div class="control-group">

            <!-- Text input-->
            <label class="control-label" for="input01">昵称</label>
            <div class="controls">
              <input type="text" placeholder="Nickname" class="input-xlarge" name="nickname" required value=<s:property value="#request.user.nickname" />>
              <p class="help-block"></p>
            </div>
          </div>

          <div class="control-group">
            <label class="control-label" for="input01">个人简介</label>
            <div class="controls">
                <textarea rows="10" cols="50" style="width:450px;" name="userIntro"><s:property value="#request.user.userIntro" />
                </textarea>
              <p class="help-block"></p>
            </div>
          </div>
        </fieldset>
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