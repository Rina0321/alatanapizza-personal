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
	<title>新商品追加確認画面</title>
</head>
<body>


	<div class="titleName">
		<h2>新商品追加確認画面</h2>
	</div>

	<div class="confirm">
		<h3>以下の商品を新しく追加します。よろしいですか？</h3>
	</div>

	<div class="main">//商品
					<s:form action="XXX">
							<br>商品名:
							<s:property value="productName" />
							<br>
							<br>
							<br>ふりがな:
							<s:property value="productKanaName"/>
							<br>
							<br>
							<br>個数:
							<s:property value="quantity"/>
							<br>
							<br>価格:
							<s:property value="price"/>
							<br>
						</s:form>

						<div class="back">
							<a href="/alatanapizza/MasterAdd.jsp">戻 る</a>
						</div>

	</div>>




</body>
</html>