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
<title>新商品追加画面</title>
</head>
<body>



	<div class="titleName">
		<h2>新商品追加画面</h2>
	</div>

	<div class="instruction">
		<h3>追加したい商品情報を入力してください。</h3>
	</div>

	<div class="main">
			<table>
				<s:form action="MasterAddAction"  method="post" enctype="multipart/form-data">
					<tr>
						<td>
							<label>商品名:</label>
						</td>
						<td>
							<input type="text" name="productName" value="" />
						</td>
					</tr>

					<tr>
						<td>
							<label>商品名(ひらがな):</label>
						</td>
						<td>
							<input type="text" name="productKanaName" value="" />
						</td>
					</tr>

					<tr>
						<td>
							<label>価格:</label>
						</td>
						<td>
							<input type="text" name="price" value="" />円
						</td>
					</tr>
					<tr>
						<td>
							<label>個数:</label>
						</td>
						<td>
							<select name="quantity">
								<option value="1" selected="selected">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>

							</select>
						</td>
					</tr>

					<!-- 商品画像 -->
					<tr>
						<td>画像</td>
						<td width="22%">
							<div align="right"></div>
						</td>
					</tr>
					<tr>
						<td width="78%"><input type="file" name="image"/></td>
					</tr>

					<!-- 登録ボタン -->
					<tr>
						<td><s:submit value="登録"/></td>
					</tr>

				</s:form>
			</table>
		</div>




</body>
</html>