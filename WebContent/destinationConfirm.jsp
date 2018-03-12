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
<title>alatanapizza 宛先情報確認画面</title>
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
			<h3></h3>
			<h3>以下の内容で登録します</h3>
			<table>
			<s:form action="DestCompleteAction">
				<span>姓</span>
				<s:property value="userFamilyName" escape="false"/>
				<span>名</span>
				<s:property value="userFirstName" escape="false"/>
				<span>姓ふりがな</span>
				<s:property value="userFamilyNameKana" escape="false"/>
				<span>名ふりがな</span>
				<s:property value="userFilstNameKana" escape="false"/>
				<span>メールアドレス</span>
				<s:property value="mail" escape="false"/>
				<span>電話番号</span>
				<s:property value="telNumber" escape="false"/>
				<span>住所</span>
				<s:property value="userAddress" escape="false"/>
				<s:submit value="登録情報確定画面へ"/>
			</s:form>
			</table>
		</div>

		<span>修正する場合は</span>
		<a href='<s:url action="DestAction"/>'>こちら</a>


	</div>

</body>
</html>