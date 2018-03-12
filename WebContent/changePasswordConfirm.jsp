<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>変更内容確認画面</h3><br>
<p>変更内容が正しければ下記の完了ボタンで再設定を完了します。</p>


<s:form action="ChangePasswordCompleteAction">

		 <tr>
		<td><span id="pro">変更後のパスワード:</span></td>
		 <td><s:property value="newpass" escape="false"/>
		 	<s:hidden name="newpass" value="%{newpass}" />
			<s:hidden name="userid" value="%{userid}" />
			<s:hidden name="answer" value="%{answer}"/>
		</td>
</tr><br>

<tr>
				<td><s:submit value="完了" /></td>
				</tr>
			</s:form>



</body>
</html>