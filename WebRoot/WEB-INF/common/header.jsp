<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript">
	function getCookie(objName) {//获取指定名称的cookie的值
		var arrStr = document.cookie.split("; ");
		for ( var i = 0; i < arrStr.length; i++) {
			var temp = arrStr[i].split("=");
			if (temp[0] == objName)
				return decodeURI(temp[1]);
		}
	}

	$(function() {
		//cookie
		var username = getCookie('zhbitacm_username');
		if (!username == false && username.length > 0 && username != "\"\"") {
			$('.unLoginItem').hide();
			$('.loginItem').show();
			$('#username').html(username);
		} else {
			$('.unLoginItem').show();
			$('.loginItem').hide();
			$.acm.doGet( {
				url : 'validateLogin',
				success : function(json) {
					var username = json.data;
					if (!username == false && username.length > 0
							&& username != "\"\"") {
						$('.unLoginItem').hide();
						$('.loginItem').show();
						$('#username').html(username);
					} else {
						$('.unLoginItem').show();
						$('.loginItem').hide();
					}
				},
				error : function() {
				}
			});
		}
	});
</script>



<div class="header">
	<div class="head_swap">
		<div class="logo">
			<h1>
				<a href="index.html">Zhbit ACM</a>
			</h1>
		</div>
		<form class="form-search search">
			<div class="input-append">
				<input type="text" class="span2 search-query">
				<button class="btn" style="border-radius: 0px 14px 14px 0px;">
					新闻
				</button>

			</div>
		</form>

		<ul class="menu unLoginItem">
			<li>
				<a href="index.jsp" class="btn btn-link">首页</a>
			</li>
			<li>
				<a href="login.jsp" class="btn btn-link">登录</a>
			</li>
			<li>
				<a href="user/competition_getContests" class="btn btn-link">比赛</a>
			</li>
			<li>
				<a href="about.html" class="btn btn-link">关于</a>
			</li>
		</ul>

		<ul class="menu loginItem">
			<li>
				<a href="index.jsp" class="btn btn-link">首页</a>
			</li>
			<li>
				<span>当前用户:</span><a href="user/user_getuserinfo" id="username"  class="btn btn-link">用户名</a>
			</li>
			<li>
				<a href="user/user_logout" class="btn btn-link">退出</a>
			</li>
			<li>
				<a href="user/competition_getContests" class="btn btn-link">比赛</a>
			</li>
			<li>
				<a href="about.html" class="btn btn-link">关于</a>
			</li>
		</ul>
	</div>
</div>



