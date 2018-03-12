<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta http-equiv="Content-Style-Type" content="text/css"/>
  <meta http-equiv="Content-Script-Type" content="text/javascript"/>

  <link href="css/craftdenki.css" rel="stylesheet">

  <title>ユーザー情報入力画面</title>
</head>

<body>
  <div id="header"> </div>

  <div id="main">
    <div id="top">
      <h3>ユーザー情報入力画面</h3>
      <br>
      <p>下記情報を入力してください</p>
    </div>

    <div>
      <s:if test="errorMessage!=' '">
        <s:property value="errorMessage" escape="false"/>
      </s:if>

      <table>
      <s:form action="UserCreateConfirmAction">

        <tr>
          <td>ユーザーID : </td>

          <td>
            <input type="text" name="loginUserId" value='<s:property value="loginUserId"/>' placeholder="半角英数字 8文字以下"/>
                <s:iterator value="errMsgListId">
                </s:iterator>
          </td>
        </tr>

        <tr>
          <td>パスワード : </td>

          <td>
            <input type="password" name="loginPassword" value='<s:property value="loginPassword"/>' placeholder="半角英数字 16文字以下"/>
          </td>
        </tr>

        <tr>
          <td>姓 : </td>

          <td>
            <input type="text" name="familyName" value='<s:property value="familyName"/>' placeholder="16文字以下"/>
          </td>
        </tr>

        <tr>
          <td>名 : </td>

          <td>
            <input type="text" name="fiestName" value='<s:property value="firstName"/>'/>
          </td>
        </tr>

        <tr>
          <td>姓（かな） : </td>

          <td>
            <input type="text" name="familyNameKana" value='<s:property value="familyName"/>' placeholder="ひらがな 16文字以下"/>
          </td>
        </tr>

        <tr>
          <td>名（かな） : </td>

          <td>
            <input type="text" name="firstNameKana" value='<s:property value="familyName"/>'/>
          </td>
        </tr>

        <tr>
          <td>性別 : </td>

          <td>
            <input type="radio" name="sex" value='<s:property value="sex"/>'>男
            <input type="radio" name="sex" value='<s:property value="sex"/>'>女
          </td>
        </tr>

        <tr>
          <td>メールアドレス : </td>

          <td>
            <input type="text" name="mail" value='<s:property value="mail"/>' placeholder="14文字以上32文字以下"/>
          </td>
        </tr>

        <tr>
          <td>秘密の質問 : </td>

          <td>
           <s:if test="secretQuestion==1">
            <select name="secretQuestion">
              <option value="">選択してください</option>
              <option value="1" selected>好きな食べ物</option>
              <option value="2">嫌いな食べ物</option>
            </select>
           </s:if>

           <s:elseif test="secretQuestion==2">
            <select name="secretQuestion" id="secretQuestion">
              <option value="">選択してください</option>
              <option value="1">好きな食べ物</option>
              <option value="2" selected>嫌いな食べ物</option>
            </select>
           </s:elseif>
          </td>
        </tr>

        <tr>
          <td>答え : </td>

          <td>
            <input type="text" name="secretAnswer" value='<s:property value="secretAnswer"/>'/>
          </td>
        </tr>


      </s:form>
      </table>


      <br>
      <div>
        <span>確認画面へ進む</span>
        <a href='<s:url action="UserCreateConfirmAction"/>'> → </a>
      </div>


      <br>
      <div>
        <span>前画面に戻る</span>
        <a href='<s:url action="HomeAction"/>'> ← </a>
      </div>

    </div>
   </div>

  <div id="footer"> </div>


</body>
</html>