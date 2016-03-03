<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>问卷调查</title>
 <!-- Bootstrap core CSS -->
    <link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="./bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
<div class="container theme-showcase" role="main">
<div class="jumbotron">
<h2 align="center">调查问卷</h2>
<br>

</div>
<p>尊敬的先生/女士：
您好！感谢您参加我们的调查！此次调查主要通过问卷方式了解
芦山地震后重灾区灾民受灾状况以及获得医学救援的反应性和可及
性，为国家“防灾、减灾”建设提供重要依据。所调查的内容仅用于
相关的分析，我们将按照国家《统计法》的要求，对您回答的问题加
以保密。希望您能够如实、客观、完整地回答以下的所有问题，再次
感谢您的合作！
</p>
<p>一. 基本情况</p>
<form action="">
<s:iterator value="bulks" var="bulk">
<s:property value="#bulk.pid"/>
<s:iterator value="#bulk.items" var="item">
<s:property value="#item.problem.title"/><br>
<s:if test="#item.problem.type==1"> 
<s:iterator value="#item.options" var="option">
<%-- <s:hidden name="ids" value="id"/> --%>
<input type="radio" name="rds" value="#option.value"><s:property value="#option.value"/></input>

</s:iterator>
<br>
<%--  <s:radio list="#item.options"  listValue="value" listKey="value" value="value"></s:radio> --%> 
 </s:if> 
 
 <s:if test="#item.problem.type==2"> 
<s:iterator value="#item.options" var="option">
<%-- <s:hidden name="ids" value="id"/> --%>
<input type="checkbox" name="rds" value="#option.value">
<s:property value="#option.value"/></input>
<s:if test="#option.edit==1">
<input type="text" name="rds" value=""></input>
</s:if>
</s:iterator>
 </s:if> 
 
  <s:if test="#item.problem.type==3"> 
<s:iterator value="#item.options" var="option">
<%-- <s:hidden name="ids" value="id"/> --%>
<s:property value="#option.value"/><input type="text" name="rds" value=""></input>

</s:iterator>
 </s:if> 
</s:iterator>
<br> 
</s:iterator>


<%-- <s:iterator value="items" var="item" status="i">
<s:property value="#item.problem.title"/>
<br/>
<s:if test="#item.problem.type==1"> 
<s:property value="#item.options"/>
<s:iterator value="#item.options" var="option">
<s:hidden name="ids" value="id"/>
<!-- <input type="radio" name="rds" value="1">1</input> -->
<input type="radio" name="rds" value="#option.value"><s:property value="#option.value"/></input>

</s:iterator>
 <s:radio list="#item.options"  listValue="value" listKey="value" value="value"></s:radio> 

 </s:if> 


</s:iterator> --%>
<s:radio name="a" label="请选择您喜欢的图书" labelposition="top"
	list="{'疯狂Java讲义','轻量级Java EE企业应用实战',
		'经典Java EE企业应用实战'}"/>
</form>

</div>
<button type="button" class="btn btn-lg btn-primary">Primary</button>
<s:debug></s:debug>
<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="./jquery/jquery.min.js"></script>
    <script src="./bootstrap/3.3.5/bootstrap.min.js"></script>
</body>
</html>