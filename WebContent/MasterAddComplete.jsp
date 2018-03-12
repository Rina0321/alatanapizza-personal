<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/reset.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
		rel="stylesheet">
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/mt_style.css">
	<link rel="shortcut icon"
		href="${pageContext.request.contextPath}/img/favicon.ico">
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/ochiai_style.css">
	<title>新商品追加完了</title>
</head>
<body>

商品の追加が完了しました。

	<div id="main">
		<div id="top">
		</div>

		<div>

			<h3>新商品の追加が完了しました</h3>

		</div>
	</div>

<a href='<s:url action="MasterChangeAction"/>'>管理者HOME画面へ戻る</a>



</body>
</html>