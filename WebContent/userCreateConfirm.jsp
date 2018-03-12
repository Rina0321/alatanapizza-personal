<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset = utf-8"/>
  <meta http-equiv="Content-Style-Type" content="text/css"/>
  <meta http-equiv="Content-Script-Type" content="text/javascript"/>

  <link href="css/craftdenki.css" rel="stylesheet">

  <title>確認画面</title>

</head>

<body>
  <div id="header"> </div>

  <div id="main">
    <div id="top">
      <p>ユーザー登録</p>
    </div>


    <s:form action="UserCreateAction">

      <input type="hidden" name="loginUserId" value="<s:property value="loginUserId"/>"/>
      <input type="hidden" name="loginPassword" value="<s:property value="loginPassword"/>">
      <input type="hidden" name="familyName" value="<s:property value="familyName"/>">
      <input type="hidden" name="firstName" value="<s:property value="firstName"/>">
      <input type="hidden" name="familyNameKana" value="<s:property value="familyNameKana"/>">
      <input type="hidden" name="firstNameKana" value="<s:property value="firstNameKana"/>">
      <input type="hidden" name="sex" value="<s:property value="sex"/>">
      <input type="hidden" name="mail" value="<s:property value="mail"/>">
      <input type="hidden" name="secretQuestion" value="<s:property value="secretQuestion"/>">
      <input type="hidden" name="secretAnswer" value="<s:property value="secretAnswer"/>">
    </s:form>



    <div>登録する内容は以下でよろしいですか？</div>

      <table>

      <tr id="box">
        <td>ユーザーID :</td>
        <td> <s:property value="loginUserId"/> </td>
      </tr>

      <tr id="box">
        <td>ログインPASS :</td>
        <td> <s:property value="loginPassword" /> </td>
      </tr>

      <tr id="box">
        <td>姓 :</td>
        <td> <s:property value="familyName" /> </td>
      </tr>

      <tr id="box">
        <td>名 :</td>
        <td> <s:property value="firstName" /> </td>
      </tr>

      <tr id="box">
        <td>姓（かな） :</td>
        <td> <s:property value="familyNameKana"/> </td>
      </tr>

      <tr id="box">
        <td>名（かな） :</td>
        <td> <s:property value="firstNameKana"/> </td>
      </tr>

      <tr id="box">
        <td>性別 :</td>
          <td> <s:if test="sex==0">男</s:if>
	 		   <s:if test="sex==1">女</s:if>
	 	  </td>
      </tr>

      <tr id="box">
        <td>メールアドレス :</td>
        <td> <s:property value="mail"/> </td>
      </tr>

      <tr id="box">
        <td>秘密の質問 :</td>
          <td> <s:if test="secretQuestion==1">好きな食べ物</s:if>
	 		   <s:if test="secretQuestion==2">嫌いな食べ物</s:if>
	 	  </td>
      </tr>

      <tr id="box">
        <td>答え :</td>
        <td> <s:property value="secretAnswer"/> </td>
      </tr>

<!--  <tr>
        <td>
          <s:form action="UserCreateCompleteAction">
            <s:submit value="登録する"/>
          </s:form>
        </td>
      </tr>  -->



      <tr>
        <td>入力に戻る<a href='<s:url action="UserCreateAction"/>'> ← </a></td>
        <td>登録に進む<a href='<s:url action="UserCreateCompleteAction"/>'> → </a></td>
      </tr>

      </table>
    </div>


  <div id="footer"> </div>

</body>
</html>