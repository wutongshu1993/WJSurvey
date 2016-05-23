<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>问卷结果</title>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">

 <link href="${pageContext.request.contextPath}/css/survey.css" rel="stylesheet" type="text/css" media="screen"/>
</head>
<body>
	<div class="container theme-showcase" >
	<div align="right">
	<a href="excelDetail" >导出所有问卷</a>
	</div>
		<table class=" table table-bordered table-striped ">
			<thead>
				<tr>
					<th>序号</th>
					<th>姓名</th>
					<th>家庭电话</th>
					<th>电话</th>
					<th>住址</th>
					<th>操作</th>

				</tr>
			</thead>

			<s:iterator value="users" var="user" status="i">
				<tbody>
					<tr class="success">

						<td><s:property value="#i.count" /></td>
						<td><s:property value="#user.name" /></td>
						<td><s:property value="#user.tel" /></td>
						<td><s:property value="#user.hTel" /></td>
						<td><s:property value="#user.address.quX" />区（县）&nbsp<s:property
								value="#user.address.xiangJ" />乡（街道）&nbsp<s:property
								value="#user.address.cun" />村&nbsp<s:property
								value="#user.address.zuD" />组（队）&nbsp<s:property
								value="#user.address.hao" />号</td>
						<td><a
							href="result_detail?surveyId=<s:property value="#user.survey.id"/>"
							class="btn btn-info">问卷详情</a>
							
							</td>
							
					</tr>
				</tbody>
			</s:iterator>
		</table>
		<div align="right">
		<form name="mf"
						action="result_list"
						method="post">
						<p class="STYLE1">
							<span class="STYLE1">[ <s:property value="currentPage"/> /<s:property value="totalPage"/>
								] 当前页</span> <span class="STYLE1">第 <s:property value="currentPage"/>
								页</span> <a href="#" onClick="go(1)" class="page" id="first">首 页</a>&nbsp; <a href="#"
								onClick="go(<s:property value="currentPage"/>-1)"class="page" id="before">上一页</a>&nbsp; <a
								href="#"  onClick="go(<s:property value="currentPage"/>+2)"  class="page" id="next">下一页</a>&nbsp;
							<a href="#" onClick="go(<s:property value="totalPage"/>+1)"class="page" id="last">尾 页</a>&nbsp;
						</p>
						<input type="hidden" id="currentPage" name="currentPage" value=<s:property value="currentPage"/>>
					
					
					</form>
					</div>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
function go(c){//该函数指定要跳转的页面
	  
	document.mf.currentPage.value=c;
	document.mf.submit();//提交表单
}
 
</script>
</body>
</html>