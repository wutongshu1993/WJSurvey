<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>问卷详情</title>
<!-- Bootstrap core CSS -->
<link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="./bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
	<div class="container theme-showcase" role="main">
		<div align="right">
		问卷完成时间：<s:property value="time"/><br>
		问卷编号：<s:property value="surveyId"/>
		</div>
		<table class=" table table-bordered table-striped ">
			<thead>
				<tr>
					<th>题号</th>
					<th>题干</th>
					<th>答案</th>
					<th>备注</th>
					

				</tr>
			</thead>

			<s:iterator value="details" var="detail" status="i">
				<tbody>
					<tr class="success">

						<td><s:property value="#detail.pid" /></td>
						<td><s:property value="#detail.title" /></td>
						<td><s:property value="#detail.answer" /></td>
						<td><s:property value="#detail.remark" /></td>
						
					</tr>
				</tbody>
			</s:iterator>
		</table>
		<a href="excel?surveyId=<s:property value="surveyId"/>">导出到excel</a>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="./jquery/jquery.min.js"></script>
	<script src="./bootstrap/3.3.5/bootstrap.min.js"></script>

</body>
</html>