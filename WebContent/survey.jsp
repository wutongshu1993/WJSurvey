<%@page import="java.security.cert.PKIXRevocationChecker.Option"%>
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
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/survey.css" rel="stylesheet" type="text/css" media="screen"/>
</head>
<body>

<div class="top"></div>
<div class="container theme-showcase main" role="main">
<div align="right">
<a href="${pageContext.request.contextPath}/login.jsp" >问卷统计</a>
</div>
<div class="">
<h2 align="center" class="header">调查问卷</h2>

</div>
<p class="info">尊敬的先生/女士：
您好！感谢您参加我们的调查！此次调查主要通过问卷方式了解地震后重灾区灾民受灾状况以及获得医学救援的反应性和可及性，为国家“防灾、减灾”建设提供重要依据。所调查的内容仅用于相关的分析，我们将按照国家《统计法》的要求，对您回答的问题加以保密。希望您能够如实、客观、完整地回答以下的所有问题，再次
感谢您的合作！
</p>

<form action="" class="form-inline">

<div class="row title">

<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 form-group ">
姓名：
<input type="text" class="form-control " name="name" id="name" style="width:60%"/>
</div>
<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
家庭电话：<input type="text" class="form-control " name="homeTel" id="homeTel" style="width:60%"/>
</div>
<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
手机：<input type="text" class="form-control " name="tel" id="tel" style="width:60%"/>
</div>
</div>
<br>
<div class="row title" align="left">

<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 form-group ">
家庭住址：
<input type="text" class="form-control" name="quX" id="quX" style="width:60%"/>
区/县
</div>
<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2 form-group">
<input type="text" class="form-control" name="xiangJ"  id="xiangJ"style="width:60%"/>
乡/街道
</div>
<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2 form-group">
<input type="text" class="form-control" name="cun" id="cun"  style="width:60%"/>
村
</div>
<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2 form-group">
<input type="text" class="form-control" name="zuD" id="zuD" style="width:60%"/>
组/队
</div>
<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2 form-group">
<input type="text" class="form-control" name="hao" id="hao" style="width:60%"/>
号
</div>
</div>

<s:iterator value="bulks" var="bulk">
<s:if test="#bulk.pid==1 ">
<b>一.基本情况</b>
</s:if>
<s:if test="#bulk.pid==13 ">
<b>二.地震相关情况</b>
</s:if>
<s:if test="#bulk.pid==20 ">
<b>三.地震受伤情况</b>
</s:if>
<s:if test="#bulk.pid==30 ">
<b>四.地震受灾情况</b>
</s:if>
<s:if test="#bulk.pid==44">
<b>五.地震救援组织情况</b>
</s:if>
<s:if test="#bulk.pid==56 ">
<b>六.抗震救灾认知调查</b>
</s:if>
<br>
<s:property value="#bulk.pid"/>
<s:iterator value="#bulk.items" var="item">
<font class="title">
<s:property value="#item.problem.title" />
</font>
<s:if test="#item.problem.img!=null">
<br>
<img alt="" src="<s:property value="#item.problem.img"/>">
</s:if>
<br>
<s:if test="#item.problem.type==1"> <!--  单选-->
<span class="xuanze" num="<s:property value="#bulk.pid"/>">
<span class="danxuan" num="<s:property value="#bulk.pid"/>">
<s:iterator value="#item.options" var="option">
<%-- <s:hidden name="ids" value="id"/> --%>
<s:if test="#item.problem.id>=59 && #item.problem.id<=74 ">
<input type="radio" name=<s:property value="#option.problem.id"/> value="<s:property value="#option.value"/>" class="radioOption form-control weijiuzhen" 
optionId=<s:property value="#option.id"/> flag="0">
<s:property value="#option.value"/></input>
<s:if test="#option.edit==1">
<input type="text"  value="" name=<s:property value="#option.problem.id"/> optionId=<s:property value="#option.id"/>  class="form-control remarkForR weijiuzhen" id=<s:property value="#option.id"/> disabled="disabled"></input>
</s:if>
</s:if>
<s:elseif test="#item.problem.id>=36 && #item.problem.id<=42"><!--14题选否，直接跳到第17题  -->
<input type="radio" name=<s:property value="#option.problem.id"/> value="<s:property value="#option.value"/>" class="radioOption form-control fou" 
optionId=<s:property value="#option.id"/> flag="0">
<s:property value="#option.value"/></input>
<s:if test="#option.edit==1">
<input type="text"  value=""  optionId=<s:property value="#option.id"/>  class="form-control remarkForR fou" id=<s:property value="#option.id"/> disabled="disabled"></input>
</s:if>
</s:elseif>
<s:else>
<input type="radio" name=<s:property value="#option.problem.id"/> value="<s:property value="#option.value"/>" class="radioOption form-control" 
optionId=<s:property value="#option.id"/> flag="0">
<s:property value="#option.value"/></input>
<s:if test="#option.edit==1">
<input type="text"  value=""  optionId=<s:property value="#option.id"/>  class="form-control remarkForR" id=<s:property value="#option.id"/> disabled="disabled"></input>
</s:if>
</s:else>

&nbsp&nbsp
</s:iterator>
</span>
</span>
<br>
<%--  <s:radio list="#item.options"  listValue="value" listKey="value" value="value"></s:radio> --%> 
 </s:if> 
 
 <s:if test="#item.problem.type==2"> <!-- 多选 -->
 <span class="xuanze" num="<s:property value="#bulk.pid"/>">
 <span class="duoxuan" num="<s:property value="#bulk.pid"/>">
<s:iterator value="#item.options" var="option">
<%-- <s:hidden name="ids" value="id"/> --%>
<s:if test="#item.problem.id>=59 && #item.problem.id<=74 ">
<input type="checkbox"  value="<s:property value="#option.value"/>" class="checkOption form-control weijiuzhen" 
optionId=<s:property value="#option.id"/> ><%-- edit=<s:property value="#option.edit"/> --%>
<s:property value="#option.value"/></input>

<s:if test="#option.edit==1">
<input type="text"   value="" optionId=<s:property value="#option.id"/>  class="remarkForC form-control weijiuzhen" id=<s:property value="#option.id"/> disabled="disabled"></input>
</s:if>
</s:if>
<s:else>
<input type="checkbox"  value="<s:property value="#option.value"/>" class="checkOption form-control " 
optionId=<s:property value="#option.id"/> ><%-- edit=<s:property value="#option.edit"/> --%>
<s:property value="#option.value"/></input>

<s:if test="#option.edit==1">
<input type="text"   value="" optionId=<s:property value="#option.id"/>  class="remarkForC form-control" id=<s:property value="#option.id"/> disabled="disabled"></input>
</s:if>
</s:else>
&nbsp&nbsp&nbsp
</s:iterator>
<br>
</span>
 </span>
 </s:if> 

  <s:if test="#item.problem.type==3"> <!-- 填空 -->
<%-- <s:iterator value="#item.options" var="option">
<s:hidden name="ids" value="id"/>
<s:property value="#option.value"/> --%>
<span class="information" num="<s:property value="#bulk.pid"/>">

<s:if test="#item.problem.id==17||#item.problem.id>=22 && #item.problem.id<=29 || #item.problem.id>=47 && #item.problem.id<=49 ||  #item.problem.id>=67 && #item.problem.id<=68 || #item.problem.id>=84 && #item.problem.id<=87">
<input type="text"  value="" problemId=<s:property value="#item.problem.id"/> id=<s:property value="#item.problem.id"/> class="remarkForE numberRemark form-control"></input>
</s:if>
<s:elseif test="#item.problem.id>=59 && #item.problem.id<=74">
<input type="text"  value="" id=<s:property value="#item.problem.id"/> problemId=<s:property value="#item.problem.id"/> optionId=<s:property value="#option.id"/>  class="remarkForE form-control weijiuzhen"></input>
</s:elseif>
<s:else>
<input type="text"  value="" id=<s:property value="#item.problem.id"/> problemId=<s:property value="#item.problem.id"/> optionId=<s:property value="#option.id"/>  class="remarkForE form-control"></input>
</s:else>

&nbsp&nbsp
<br>
<%-- </s:iterator> --%>
</span>

 </s:if> 
 
 <s:if test="#item.problem.type==4"> <!-- 多文本框 -->
<span class="tiankong" num="<s:property value="#bulk.pid"/>">
<textarea type="" value="" optionId=<s:property value="#option.id"/>  class="remarkForE form-control"></textarea>
&nbsp&nbsp
<br>
</span>
 </s:if> 
</s:iterator>
<s:if test="#bulk.pid==20"> 
如果您地震中受伤，请回答 21-29题；如果您未受伤，请代替您家中受伤最严
重伤者回答21-29题；如果家中无人受伤，请直接跳至  30题

</s:if>
<s:if test="#bulk.pid==75"> 
<br><br>
<b>对于以下问题，请根据您的状况选择适合的选项，“1”代表非常不符合您的想法，“6”代
表非常符合，以此类推。</b>

</s:if>
<br> 


</s:iterator>




</form>
<button type="button" class="btn btn-lg btn-primary" id="surveySubmit">提交问卷</button>

<hr>
<footer>版权所有 &copy;中国第二军医大大学</footer>
</div>

<%-- <s:debug></s:debug> --%>
<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/util.js"></script>
	<script src="${pageContext.request.contextPath}/js/survey.js"></script>
</body>
</html>