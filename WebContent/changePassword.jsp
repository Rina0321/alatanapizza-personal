<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Style-Type" content="text/css"/>
<title>Insert title here</title>
<style>
.error{
color:red;}



</style>
</head>
<body>
<div class="error"><s:if test='errorUserid !=""'>
<s:property value="errorUserid"/><br>
</s:if></div>
<div class="error"><s:if test="errorpassword !=''">
<s:property value="errorpassword"/>
</s:if></div>
<s:form action="ChangePasswordConfirmAction" >
<table>
<tr>
<td>ユーザーID;</td>
<td><input type="text" value='<s:property value="userid"/>' name="userid"/></td>
</tr>
<tr>
<td>秘密の質問:</td>
<td><select name="question">
<option value="1">好きな食べ物</option>
<option value="2">嫌いな食べ物</option>
</select>
</td>
</tr>
<tr>
<td>答え:</td>
<td><input type="text" name="answer" value='<s:property value="answer"/>' required/></td>
</tr>
<tr>
<td>新しいパスワード:</td>
<td><input type="password" name="newpass" required/></td>
</tr>
<tr>
<td>確認:</td>
<td><input type="password" name="checkpass" required/></td>
</tr>
<tr>
<td><s:submit value="確認"/></td>
</tr>
</table>


</s:form>




</body>
</html>