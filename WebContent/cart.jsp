<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>カート画面</title>

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mt_style.css">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico">

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cart_style.css">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script><script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
</head>
<body>

<div class="main">

	<h1>カート</h1>
	<s:property value="errMsg"/>
		<s:if test="cartList.isEmpty()">
			<p class="message">カートの中は空です</p>
		</s:if>

		<s:else>
			<p class="message">カートには以下の商品が入っています</p>

		<s:iterator value="cartList">

			<div class="border"></div>
<!-- 画像を表示させる -->
			<div class="main_content clearfix_bon">
			<div class="pro_img">
			<s:url id="url" action="ProDetailAction"><s:param name="id" value="productId" /></s:url>
			<s:a href="%{url}">
			  <img src='<s:property value="image_file_name"/>' alt="画像なし"/>
			</s:a>
			</div>

<!-- テキストデータを表示させる -->
			<div class="pro_text">
			<div class="name">
			<s:url id="url" action="ProDetailAction"><s:param name="id value="productId" /></s:url>
			<s:a href="%{url}">

<!-- ふりがな表示 -->
			<div class="kana">
			<s:property value="product_name_kana"/>
			</div>

<!-- 商品名表示 -->
			<div class="pro_name">
			商品名：<s:property value="product_name" />
			</div>
			</s:a>
			</div>

<!-- 値段表示 -->
			<div class="price_count"></div>
			<div class="price">
			価格:\<fmt:formatNumber value="${price}" />
			</div>

<!-- 個数表示 -->
			<div class="count">
			（購入数：<s:property value="productCount" />点）
			</div>

<!-- 発売会社 -->
			<div class="company">
			発売会社：<s:property value="release_company" />
			</div>

<!-- 年月日 -->
			<div class="release_date">
			発売日：<fmt:parseDate var="date2" value="${release_date}" pattern="yyyy-MM-dd HH:mm:ss.SS" />
					<fmt:formatDate value="${date2}" pattern="yyyy年M月d日" />
			</div>
		</div>

<!-- 削除ボタン -->
			<div class="deletebutton">
			<s:form action="CartDeleteAction">
				<a href='<s:url action="CartDeleteAction">
				<s:param name="productId" value="productId"></s:param></s:url>'>削除</a>
				</s:form>
			</div>

		</s:iterator>
		<div class="border"></div>
		</s:else>
		</div>

<!-- 合計金額の表示 -->
			<div class="totalprice">
			合計金額:\
			<fmt;formatNumber value="${totalPrice}" />
			</div>

<!-- 決済に移行する -->
			<s:if test="! cartList.isEmpty()">
			<div class="settlement_btn">
			 <a href='<s:url action ="PurchaseInfoAction" />'>決済画面へ</a>
			</div>
			</s:if>
<!-- F5キー（画面の更新）とBackspaceとDeleteの無効化 -->
			<script>
			$(function(){
				$(document).keydown(function(event){
				//クリックされたキーコードを取得する
				//KeyCodeはネットで調べる
				var keyCode = event.keyCode;

				//F5の場合はfalseをリターン
				if(keyCode == 116){
					console.log("F5");
					return false;
				}
				//バックスペースキーを制御する
				if(keyCode == 8){
					console.log("Backspace");
					return false;
				}
				//デリートキーを制限する
				if(keyCode == 46){
					console.log("Delete");
					return false;
				}
				})
			});

			</script>
</body>
</html>