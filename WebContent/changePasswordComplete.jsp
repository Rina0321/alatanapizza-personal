<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Complete</title>
</head>
<body>
<s:if test="#session.newpass == null">
<span id="error">パスワードの変更に失敗しました。<br>
お手数ですが最初からやり直してください。
</span>
<a href='<s:url action="ChangePasswordAction"/>'>再設定画面へ</a>
</s:if>
<s:else>
<span id="success">再設定が完了しました。</span>
<a href="http://localhost:8080/alatanapizza/">ホーム</a>


</s:else>



</body>
</html>