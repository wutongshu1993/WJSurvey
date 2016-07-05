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
<input type="text" class="form-control" name="name" id="name" style="width:60%"/>
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
<span class="danxuan">
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
<br>
<%--  <s:radio list="#item.options"  listValue="value" listKey="value" value="value"></s:radio> --%> 
 </s:if> 
 
 <s:if test="#item.problem.type==2"> <!-- 多选 -->
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
 </s:if> 
 
  <s:if test="#item.problem.type==3"> <!-- 填空 -->
<%-- <s:iterator value="#item.options" var="option">
<s:hidden name="ids" value="id"/>
<s:property value="#option.value"/> --%>
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
 </s:if> 
 
 <s:if test="#item.problem.type==4"> <!-- 多文本框 -->
<textarea type="" value="" optionId=<s:property value="#option.id"/>  class="remarkForE form-control"></textarea>
&nbsp&nbsp
<br>
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
<script type="text/javascript">
$(document).on("blur",".remarkForR", function(){
var remark = $(this).val();
//alert(remark);
		var opId = $(this).attr("optionId");//获取选项的编号
		//alert(opId);
		//并让该选项选中
		//$(this).prev().attr("checked",true);
		/* alert(999);
		document.getElementById(opId).checked=true;
		alert(000); */
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
//alert(remark);
		var opId = $(this).attr("optionId");//获取选项的编号
		//alert(opId);
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
//alert(remark);
		var opId = $(this).attr("problemId");//获取问题的编号
		//alert(opId);
		params = {
				"remark":remark,
				"optionId":opId
		}
		 //alert(checked); 
		
		 $.ajax({
			url : 'survey_saveRemarkForE',
			type : 'post',
			dataType : 'json',
			data : params,
			traditional : true,
			success : checkChangeCallback
		});  
	})
$(document).on("click",".checkOption", function(){


	//alert(checked);
	var opId = $(this).attr("optionId");//获取选项的编号
	//alert(opId);
	var checked = $(this).is(':checked');
	//document.getElementById(opId).disabled = true;
	if(document.getElementById(opId)!=null){
		//alert(111);
		//alert(checked);
		if(checked==true){
				document.getElementById(opId).disabled = false;
			}
		else if(checked==false){
			//$(this).removeAttr("checked");
			document.getElementById(opId).disabled = true;
			document.getElementById(opId).value="";
			//alert(22);
		}
		
	}
	
	params = {
			"checked":checked,
			"optionId":opId
	}
	 //alert(checked); 
	// document.getElementById(opId).disabled = false;
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
//实现选择了以后让备注框里面的内容清除
var parent = $(this).parent(".danxuan");
var ra = parent.children(".remarkForR");//得到的是一个数组
if(ra.length>0){
ra[0].disabled = true;
ra.val(" ");
}


var opId = $(this).attr("optionId");//获取选项的编号
	
 if(document.getElementById(opId)!=null){
	//alert(111);
	if($(this).attr("flag")==1){
		$(this).attr("flag","0");
		$(this).removeAttr("checked");
		document.getElementById(opId).disabled = true;
		document.getElementById(opId).value="";
	}
	else{
		$(this).attr("flag","0");
		$(this).attr("checked","checked");
		document.getElementById(opId).disabled = false;
	}
} 
	
var checked = $(this).is(':checked');
	//alert(checked);
	
	//alert(opId);
	 if(opId==71){//"否",直接跳到第17题
		 if(checked==true){
			 var w = document.getElementsByClassName("fou"); 
			 for(var i=0;i<w.length;i++){
					w[i].disabled = true;
					w[i].checked = false;
					w[i].value = "";
				}
				var x = document.getElementsByClassName("fou remarkForE");
				for(var i=0;i<x.length;i++){
					x[i].disabled = true;
					// x[i].checked = false;
					x[i].value = ""; 
				}
				 }
		//document.getElementById(73).value="";
		/*  var x = document.getElementsByName("36");
		 for(var i=0;i<x.length;i++){
			 x[i].disabled = true;
			 x[i].checked = false;
			 x[i].value = "";
			
		 }
		 var x = document.getElementsByName("37");
		 for(var i=0;i<x.length;i++){
			 x[i].disabled = true;
			 x[i].checked = false;
			 x[i].value = "";
		 }
		 var x = document.getElementsByName("38");
		 for(var i=0;i<x.length;i++){
			 x[i].disabled = true;
			 x[i].checked = false;
		 }
		 var x = document.getElementsByName("39");
		 for(var i=0;i<x.length;i++){
			 x[i].disabled = true;
			 x[i].checked = false;
		 }
		 var x = document.getElementsByName("40");
		 for(var i=0;i<x.length;i++){
			 x[i].disabled = true;
			 x[i].checked = false;
			 x[i].value = "";
		 }
		 var x = document.getElementsByName("41");
		 for(var i=0;i<x.length;i++){
			 x[i].disabled = true;
			 x[i].checked = false;
		 }
		 var x = document.getElementsByName("42");
		 for(var i=0;i<x.length;i++){
			 x[i].disabled = true;
			 x[i].checked = false;
		 } */
		
		 
	} 
	 if(opId==70){
		 if(checked==true){
			 var w = document.getElementsByClassName("fou radioOption");
				for(var i=0;i<w.length;i++){
					w[i].disabled = false;
				}
				/* //单选题输入框不生效，且清空 */
				 var v = document.getElementsByClassName("fou remarkForR");
					for(var i=0;i<v.length;i++){
						v[i].disabled = true;
						v[i].value = "";
					} 
			 /* var x = document.getElementsByName("36");
			 for(var i=0;i<x.length;i++){
				 x[i].disabled = false;
			 }
			 var x = document.getElementsByName("37");
			 for(var i=0;i<x.length;i++){
				 x[i].disabled = false;
			 }
			 var x = document.getElementsByName("38");
			 for(var i=0;i<x.length;i++){
				 x[i].disabled = false;
			 }
			 var x = document.getElementsByName("39");
			 for(var i=0;i<x.length;i++){
				 x[i].disabled = false;
			 }
			 var x = document.getElementsByName("40");
			 for(var i=0;i<x.length;i++){
				 x[i].disabled = false;
			 }
			 var x = document.getElementsByName("41");
			 for(var i=0;i<x.length;i++){
				 x[i].disabled = false;
			 }
			 var x = document.getElementsByName("42");
			 for(var i=0;i<x.length;i++){
				 x[i].disabled = false;
			 } */
			 }
	 }
	 if(opId==181){//"未就诊，直接跳到30题"
		 if(checked==true){
		//document.getElementById(73).value="";
		var w = document.getElementsByClassName("weijiuzhen");
		for(var i=0;i<w.length;i++){
			w[i].disabled = true;
			w[i].checked = false;
			w[i].value = "";
		}
		var x = document.getElementsByClassName("weijiuzhen remarkForE");
		for(var i=0;i<x.length;i++){
			x[i].disabled = true;
			// x[i].checked = false;
			x[i].value = ""; 
		}
		 }
	 }
	
	 if(opId>=176 && opId<=180){
		 var w = document.getElementsByClassName("weijiuzhen radioOption");
				for(var i=0;i<w.length;i++){
					w[i].disabled = false;
				}
				/* //单选题输入框不生效，且清空 */
				 var v = document.getElementsByClassName("weijiuzhen remarkForR");
					for(var i=0;i<v.length;i++){
						v[i].disabled = true;
						v[i].value = "";
					} 
					//填空题生效
					var x = document.getElementsByClassName("weijiuzhen remarkForE");
					for(var i=0;i<x.length;i++){
						x[i].disabled = false;
						x[i].value = "";
					}
					
					//多选题生效
					var y = document.getElementsByClassName("weijiuzhen checkOption");
					for(var i=0;i<y.length;i++){
						y[i].disabled = false;
					}
					//多选题输入框 不生效，且清空
					var z = document.getElementsByClassName("weijiuzhen remarkForC");
					for(var i=0;i<z.length;i++){
						z[i].disabled = true;
						z[i].value = "";
					} 
			 }
	
	//alert(111);
	//alert($(this).attr("flag"));
	
	 /* if(checked){
		document.getElementById(opId).disabled = false;
	}
	 if(checked==false){
		 document.getElementById(opId).disabled = true;
	 }  */
	//$("#"+opId).disabled=true;
	
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
			/* alert(data.status); */
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
	//alert(name+hTel+tel+quX+xiangJ+cun+zuD+hao);
	var reg = /^[0-9]*$/;
	if(!reg.test(hTel)){
		alert("请输入数字");
		$("#homeTel").focus();
		return false;
	}
	if(!reg.test(tel)){
		alert("请输入数字");
		$("#tel").focus();
		return false;
	}
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
			window.location.reload();
		}
		
		/* function checkNumberp(){
			console.log($(this));
			console.log($(this.val()));
			var num =$(this).val();
			
			var pid = $(this).attr("class");
			alert(pid);
			var num = $("#"+pid).val();
			//var num = document.getElementById(id).val();
			alert(num);
			var reg = /^[0-9]*$/;
			if(!reg.test(num)){
				alert("请输入数字");
				return false;
			}
			else return true;
		} */
		$(document).on("blur",".numberRemark", function(){
			//var num =$(this).val();
			var pid = $(this).attr("problemId");
			//alert(pid);
			//var num = $("#"+pid).val();
			var num = $("[problemId="+pid+"]").val();
			//var num = document.getElementById(id).val();
			//alert(num);
			var reg = /^[0-9]*$/;
			if(!reg.test(num)){
				alert("请输入数字");
				$(this).focus();
				return false;
			}
			else return true;
		});
</script>
</body>
</html>