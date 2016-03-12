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
    <link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="./bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="./css/survey.css" rel="stylesheet" type="text/css" media="screen"/>
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
<form action="" class="form-inline">
<div class="row">

<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 form-group">
姓名：
<input type="text" class="form-control" name="name" id="name" style="width:60%"/>
</div>
<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
家庭电话：<input type="text" class="form-control" name="homeTel" id="homeTel" style="width:60%"/>
</div>
<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
手机：<input type="text" class="form-control" name="tel" id="tel" style="width:60%"/>
</div>
</div>
<br>
<div class="row" align="left">

<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 form-group">
家庭住址：
<input type="text" class="form-control" name="quX" id="quX" style="width:60%"/>
区县
</div>
<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2 form-group">
<input type="text" class="form-control" name="xiangJ"  id="xiangJ"style="width:60%"/>
乡(街道)
</div>
<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2 form-group">
<input type="text" class="form-control" name="cun" id="cun"  style="width:60%"/>
村
</div>
<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2 form-group">
<input type="text" class="form-control" name="zuD" id="zuD" style="width:60%"/>
组（队）
</div>
<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2 form-group">
<input type="text" class="form-control" name="hao" id="hao" style="width:60%"/>
号
</div>
</div>
<s:iterator value="bulks" var="bulk">
<s:property value="#bulk.pid"/>
<s:iterator value="#bulk.items" var="item">
<s:property value="#item.problem.title"/><br>

<s:if test="#item.problem.type==1"> 
<s:iterator value="#item.options" var="option">
<%-- <s:hidden name="ids" value="id"/> --%>
<input type="radio" name="rds" value="<s:property value="#option.value"/>" class="radioOption" 
optionId=<s:property value="#option.id"/>>
<s:property value="#option.value"/></input>
<s:if test="#option.edit==1">
<input type="text" name="radioRem" value="" optionId=<s:property value="#option.id"/>  class="remarkForR"></input>
</s:if>
</s:iterator>
<br>
<%--  <s:radio list="#item.options"  listValue="value" listKey="value" value="value"></s:radio> --%> 
 </s:if> 
 
 <s:if test="#item.problem.type==2"> 
<s:iterator value="#item.options" var="option">
<%-- <s:hidden name="ids" value="id"/> --%>
<input type="checkbox" name="cks" value="<s:property value="#option.value"/>" class="checkOption" 
optionId=<s:property value="#option.id"/> edit=<s:property value="#option.edit"/>>
<s:property value="#option.value"/></input>

<s:if test="#option.edit==1">
<input type="text" name="checkRem" value="" optionId=<s:property value="#option.id"/>  class="remarkForC"></input>
</s:if>
</s:iterator>
<br>
 </s:if> 
 
  <s:if test="#item.problem.type==3"> 
<s:iterator value="#item.options" var="option">
<%-- <s:hidden name="ids" value="id"/> --%>
<s:property value="#option.value"/>
<input type="text" name="edit" value="" optionId=<s:property value="#option.id"/>  class="remarkForE"></input>

</s:iterator>
 </s:if> 
</s:iterator>
<br> 
</s:iterator>




</form>

</div>
<button type="button" class="btn btn-lg btn-primary" id="surveySubmit">提交问卷</button>
<s:debug></s:debug>
<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="./jquery/jquery.min.js"></script>
    <script src="./bootstrap/3.3.5/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).on("blur",".remarkForR", function(){
var remark = $(this).val();
alert(remark);
		var opId = $(this).attr("optionId");//获取选项的编号
		alert(opId);
		params = {
				"remark":remark,
				"optionId":opId
		}
		 //alert(checked); 
		
		 $.ajax({
			url : 'survey_saveRemarkForR',
			type : 'post',
			dataType : 'json',
			data : params,
			traditional : true,
			success : checkChangeCallback
		});  
	})

$(document).on("blur",".remarkForC", function(){
var remark = $(this).val();
alert(remark);
		var opId = $(this).attr("optionId");//获取选项的编号
		alert(opId);
		params = {
				"remark":remark,
				"optionId":opId
		}
		 //alert(checked); 
		
		 $.ajax({
			url : 'survey_saveRemarkForC',
			type : 'post',
			dataType : 'json',
			data : params,
			traditional : true,
			success : checkChangeCallback
		});  
	})
$(document).on("blur",".remarkForE", function(){
var remark = $(this).val();
alert(remark);
		var opId = $(this).attr("optionId");//获取选项的编号
		alert(opId);
		params = {
				"remark":remark,
				"optionId":opId
		}
		 //alert(checked); 
		
		 $.ajax({
			url : 'survey_saveRemarkForR',
			type : 'post',
			dataType : 'json',
			data : params,
			traditional : true,
			success : checkChangeCallback
		});  
	})
$(document).on("click",".checkOption", function(){

var checked = $(this).is(':checked');
	alert(checked);
	var opId = $(this).attr("optionId");//获取选项的编号
	alert(opId);
	params = {
			"checked":checked,
			"optionId":opId
	}
	 //alert(checked); 
	
	 $.ajax({
		url : 'survey_checkChangeForC',
		type : 'post',
		dataType : 'json',
		data : params,
		traditional : true,
		success : checkChangeCallback
	});   
})


$(document).on("click",".radioOption", function(){
//	var checked = $(this).parent().children("input").eq(0).checked;

/* var val = $('input:radio[name="rds"]:checked').val(); //获取选项值

alert(val);
var checked;
if(val==null){
	checked=false;
}
else checked=true;
*/
var checked = $(this).is(':checked');
	alert(checked);
	var opId = $(this).attr("optionId");//获取选项的编号
	alert(opId);
	params = {
			"checked":checked,
			/* "newNum": Number($("#exCont").attr("newNum")),
			"titleId":Number( $(this).parents("[titleId]").attr("titleId")), */
			"optionId":opId
	}
	 //alert(checked); 
	
	 $.ajax({
		url : 'survey_checkChangeForR',
		type : 'post',
		dataType : 'json',
		data : params,
		traditional : true,
		success : checkChangeCallback
	});  
})


function checkChangeCallback(data)
		{
			alert(data.status);
		}
$(document).on("click","#surveySubmit", function(){
	var name= $("#name").val();	
	var hTel= $("#homeTel").val();	
	var tel= $("#tel").val();	
	var quX= $("#quX").val();	
	var xiangJ= $("#xiangJ").val();	
	var cun= $("#cun").val();	
	var zuD= $("#zuD").val();	
	var hao= $("#hao").val();	
	alert(name+hTel+tel+quX+xiangJ+cun+zuD+hao);
	
	var params = {
				"name":name,
				"hTel":hTel,
				"tel":tel,
				"quX":quX,
				"xiangJ":xiangJ,
				"cun":cun,
				"zuD":zuD,
				"hao":hao
			}
			$.ajax({
				url : 'survey_submit',
				type : 'post',
				dataType : 'json',
				data : params,
				traditional : true,
				success : submitCallback
			});
		})
		function submitCallback(data)
		{
			/* alert("您答对了：" + data.score + "个题目" + "\n" + data.status);
			window.location.reload(); */
			alert("提交成功，感谢您的配合");
		}
</script>
</body>
</html>