<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
	<base href="<%=basePath%>"> 
		<title>新闻列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<%@ include file="../common/commonInclude.jsp"%>
		
		<!-- 分页插件 -->
		
  	<script>
  	$(function(){
  
  		var totalPage = <s:property value="#request.totalPage"/>;
		var totalRecords = <s:property value="#request.totalRecords"/>;
		var pageNo = <s:property value="#request.pageNo"/>;
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
			hrefFormer : 'news_list',
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
			<%@ include file="../common/header.jsp"%>

			<%@ include file="../common/banner.jsp"%>
			<div class="main_swap">
				<div class="main">
					<div class="nowposition"><span >当前位置 : </span><a href="#">首页</a><span style="color:rgb(109,166,61);">&gt新闻列表</span></div>
           
            <div class="bs-problem bs-new-list">
                <table class="table table-striped">
                    <tr>
                        <th width="5%">序号</th>
                        <!-- <th width="10%">封面图片</th> -->
                        <th width="50%">标题</th>
                        <th width="30%" style="text-align:center;">发布时间</th>
                        <th width="10%" style="text-align:center;">阅读量</th>
                    </tr>
                    <s:iterator value="#request.voArticles">
                    <tr>
                        <td>001</td>
                        <td><a href="<s:property value="staticPage"/>"><s:property value="title"/></a></td>
                        <td style="text-align:center; font-weight:bold;"><s:property value="publishTime"/></td>
                        <td style="text-align:center; font-weight:bold"><s:property value="pageView"/></td>
                    </tr>
               		</s:iterator>
                    
                    
                </table>
                <div class="pagination">
                <div class="clearfix"></div>
                <div id="kkpager" ></div>
                </div>
            </div>
            <div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</body>

</html>
