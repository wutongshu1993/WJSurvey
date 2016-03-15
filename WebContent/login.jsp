<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录页面</title>
<link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="./bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="./css/survey.css" rel="stylesheet" type="text/css" media="screen"/>
<!-- <link rel="stylesheet" href="./bootstrap/css/bootstrap.css">
<script type='text/javascript'  src="./js/jquery-2.1.4.min.js"></script>-->
<script src="./bootstrap/js/bootstrap.js"></script>
</head>
<body style="background: url(a.jpg)">
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>



	<div class="form-group"
		style="margin-left: 35%; margin-right: 35%; padding-top: 3%; padding-bottom: 5%; background-color:">
		<form  action="login" method="post" style="position: relative; left: 20%">



			<h2>调查问卷统计登陆</h2>
			<br>
			<div class="form-group">
				<!-- <label for="username">用户名</label>  -->
				<input style="width: 60%"
					type="text" class="form-control" id="username" name="username" placeholder="用户名">
			</div>

			<div class="form-group">
				<!-- <label for="password">密码</label> --> <input style="width: 60%"
					type="password" class="form-control" id="password" name="password"
					placeholder="密码">
			</div>

			<button type="reset" class="btn btn-primary" id="reset"
				style="margin-left: 5%">重置</button>
			<button type="submit" class="btn btn-primary " id="submit"
				style="margin-left: 5%; margin-right: 5%" >登录</button>
	</div>
	</form>
<script src="./jquery/jquery.min.js"></script>
    <script src="./bootstrap/3.3.5/bootstrap.min.js"></script>
<%-- <script type="text/javascript">

 $(document).on("click","#submit", function(){
	var username= $("#username").val();	
	var password= $("#password").val();	
	alert(username+" "+password);
	var params = {
				"username":username,
				"password":password,
			}
	//alert(0);
			$.ajax({
				url : 'login',
				type : 'post',
				dataType : 'json',
				data : params,
				traditional : true,
				success : submitCallback
			});
			alert(0);
		})
		function submitCallback(data)
		{
			alert(data);
		}
 
</script> --%>
</body>
</html>
