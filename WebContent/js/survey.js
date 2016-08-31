/**
 * 
 */
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
	if(name=="" || hTel=="" ||tel=="" || quX=="" || xiangJ=="" ){
		alert("请完善基本信息");
		return false;
	}
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
	if(!checkText()){
		//alert("填空题未做完");
		return false;
	}
	if(!checkAllOption()){
//		alert("weizuowan");
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
		//检查所有的单选题目，看是否有没选择的 
		function checkAllOption(){
			var options = $(".xuanze");
			var flag = 1;
			$.each(options,function(i,item){
				if(!item.querySelector("input[disabled][type='radio'],input[disabled][type='checkbox']")){
				if(!item.querySelectorAll("input:checked").length){
					var num = item.getAttribute("num");
					alert("第"+num+"题没做");
					item.focus();
					flag = 0;
					return false;
				}
				}
			})
			if(flag==1){
				return true;
			}
	/*		var radios = $(".danxuan");
			$.each(radios,function(i,item){
				if(!item.querySelector("input[disabled][type='radio']")){
				if(!item.querySelectorAll("input:checked").length){
					var num = item.getAttribute("num");
					alert("第"+num+"题没做");
					item.focus();
					return false;
				}
				}
			})
			//检查所有的多选题目，看是否有没选择的 
			var checks = $(".duoxuan");
			$.each(checks,function(i,item){
				if(!item.querySelector("input[disabled][type='checkbox']")){
				if(!item.querySelectorAll("input:checked").length){
					var num = item.getAttribute("num");
					alert("第"+num+"题没做");
					item.focus();
					return false;
				}
				}
			})*/
			
			//检查所有的多选题目，看是否有没选择的 
			/*var texts = $(".tiankong");
			$.each(texts,function(i,item){
				if(!item.querySelector("input[disabled][type='text']")){
				if(!item.value.length){
					var num = item.getAttribute("num");
					alert("第"+num+"题没做");
					item.focus();
					return false;
				}
				}
			})*/
		}
		/**
		 * 检验文本框是否填有值
		 */
		function checkText(){
			var texts = $(".information");
			var flag = 1;
			$.each(texts,function(i,item){
				var text = item.querySelector("input[type='text']");
				if(!text.querySelector("input[disabled]")){
				if(text.value==""){
					var num = item.getAttribute("num");
					alert("第"+num+"题没做");
					item.focus();
					flag = 0;
					return false;
				}
				}
			})
			if(flag == 1){
				return true;
			}
		}
		function matchesSelector(element, selector){
			if(element.matchesSelector){
				return element.matchesSelector(selector);
			}
			else if(element.msMatchesSelector){
				return element.msMatchesSelector(selector);
			}
			else if(element.mozMatchesSelector){
				return element.mozMatchesSelector(selector);
			}
			else if(element.webkitMatchesSelector){
				var f = element.webkitMatchesSelector(selector);
				return element.webkitMatchesSelector(selector);
			}
			else {
				throw new Error("not supported");
			}
		}