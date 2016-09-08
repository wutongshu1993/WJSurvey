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
<title>首页</title>
<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap theme -->
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/survey.css"
	rel="stylesheet" type="text/css" media="screen" />
<link type="text/css" rel="stylesheet" href="./css/carousel.css">
<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/carousel.js"></script>
</head>
<body>
<p style="height: 200px;"></p>
	<div class="container theme-showcase" role="main">
		<div class="jumbotron">
			<p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp尊敬的先生/女士：
				您好！感谢您参加我们的调查！此次调查主要通过问卷方式了解 地震后重灾区灾民受灾状况以及获得医学救援的反应性和可及
				性，为国家“防灾、减灾”建设提供重要依据。所调查的内容仅用于 相关的分析，我们将按照国家《统计法》的要求，对您回答的问题加
				以保密。希望您能够如实、客观、完整地回答以下的所有问题，再次 感谢您的合作！</p>
			<br> <br>
			<center>
			<a href="survey" class="btn btn-lg btn-primary .btn-block">参与问卷</a>
		</center>
		</div>
		
		
		
	</div>
</body>
</html>
