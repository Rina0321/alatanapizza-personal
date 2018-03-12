<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="Content-Style-Type" content="text/css"/>
<meta http-equiv="Content-Script-Type" content="text/javascript"/>
<meta http-equiv="imagetoolbar" content="no"/>
<meta name="description" content="">
<meta name="keywords" content=""/>
<title>alatanapizza 宛先情報登録画面</title>
<style type="text/css">
/*========TAG LAYOUT========*/
/*========ID LAYOUT========*/
</style>
</head>
<body>
	<div id="main">
		<div id="top">
		</div>
		<div>
			<h3>新規宛先情報入力</h3>
			<h3>下記に必要事項を記入してください</h3>
			<s:form action="DestConfirmAction">
				<span>姓</span>
				<s:textfield name="userFamilyName"/>
				<span>名</span>
				<s:textfield name="userFirstName"/>
				<span>姓ふりがな</span>
				<s:textfield name="userFamilyNameKana"/>
				<span>名ふりがな</span>
				<s:textfield name="userFilstNameKana"/>
				<span>メールアドレス</span>
				<s:textfield name="mail"/>
				<span>電話番号</span>
				<s:textfield name="telNumber"/>
				<span>住所</span>
				<s:textfield name="userAddress"/>
				<s:submit value="登録情報確認画面へ"/>
			</s:form>
		</div>


	</div>

</body>
</html>