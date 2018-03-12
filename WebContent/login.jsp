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
<title>alatanapizza Login画面</title>
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
			<h3>会員の方のログイン</h3>
			<s:form action="LoginAction">
				<s:textfield name="loginUserId"/>
				<s:password name="loginPassword"/>
				<s:submit value="ログイン"/>
			</s:form>
		</div>
		<br/>
		<div>
			<span>ホーム画面は
					<a href='<s:url action="HomeAction"/>'>こちら</a>
			</span>
		</div>
		<br/>
	  	<div>
		<span>パスワードを忘れた方は
					<a href='<s:url action="ResetUserPassAction"/>'>こちら</a>
			</span>
		</div>
		<br/>
		<div>
			<span>新規ユーザー登録は
				<a href='<s:url action="RegisterUserInfoAction"/>'>こちら</a>
			</span>
		</div>
	</div>

</body>
</html>