<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录失败</title>
 <!-- Bootstrap core CSS -->
    <link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="./bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
<div class="container theme-showcase" role="main">
<!-- <div class="row">
	<div class="col-md-offset-3">
		<h1>登录失败, 请重新登录 </h1>
	</div>
	<div class="col-md-offset-3">
		<br/>		
	</div>
	<div class="col-md-offset-3">
		<br/>		
	</div>
	<div class="col-md-offset-4">
		<a class="btn btn-success btn-lg" href="./login.jsp">继续登录</a>
	</div> -->
<table width="300" border="1" bordercolor="#99CCFF"
		style="border-collapse:collapse" cellpadding="0" cellspacing="0">
		<!--DWLayoutTable-->
		<tr bgcolor="#99CCFF">
			<td height="13">&nbsp;</td>
		</tr>
		<tr>
			<td height="60" align="center"><span class="STYLE10">登录失败<span
					class="STYLE3">！</span><span id="ShowDiv" style="color:red"></span>
					秒后跳转到登陆页面 <script language="javascript">
						Load("/login.jsp"); //要跳转到的页面
					</script>
			</td>
		</tr>
	</table>
</div>
</div>
<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="./jquery/jquery.min.js"></script>
    <script src="./bootstrap/3.3.5/bootstrap.min.js"></script>
    <script type="text/javascript">

	var secs = 2;//倒计时5s
	var URL;
	function Load(url) {
		URL = url;
		for ( var i = secs; i >= 0; i--) {
			window.setTimeout('doUpdate(' + i + ')', (secs - i) * 1000);
		}
	}
	function doUpdate(num) {
		document.getElementById('ShowDiv').innerHTML = num;
		if (num == 0) {
			window.location = URL;
		}
	}

</script>
</body>
</html>